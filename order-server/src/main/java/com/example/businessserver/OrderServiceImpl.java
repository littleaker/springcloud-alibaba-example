package com.example.businessserver;

import com.example.AccountService;
import com.example.OrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private static final String SUCCESS = "SUCCESS";

    private static final String FAIL = "FAIL";

    private static final String USER_ID = "U100001";

    private static final String COMMODITY_CODE = "C00321";

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    private Random random;
    @Reference
    private AccountService accountService;

    @Override
    public String order(String userId, String commodityCode, int orderCount) {
        random = new Random();
        log.info("Order Service Begin ... xid: " + RootContext.getXID());
        int orderMoney = calculate(commodityCode, orderCount);

        invokerAccountService(orderMoney);

        final Order order = new Order();
        order.userId = userId;
        order.commodityCode = commodityCode;
        order.count = orderCount;
        order.money = orderMoney;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(
                        "insert into order_tbl (user_id, commodity_code, count, money) values (?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );
                pst.setObject(1, order.userId);
                pst.setObject(2, order.commodityCode);
                pst.setObject(3, order.count);
                pst.setObject(4, order.money);
                return pst;
            }
        }, keyHolder);
        order.id = keyHolder.getKey().longValue();

        if (random.nextBoolean()){
            throw new RuntimeException("this is a mockException");
        }

        log.info("Order Service End ... Created " + order);

        if (result == 1){
            return SUCCESS;
        }
        return FAIL;
    }


    private int calculate(String commodityId, int orderCount){
        return 2 * orderCount;
    }

    private void invokerAccountService(int orderMoney){
        accountService.account(USER_ID, orderMoney);
    }
}
