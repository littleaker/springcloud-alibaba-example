package com.example.businessserver;

import com.example.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Random;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private static final String SUCCESS = "SUCCESS";

    private static final String FAIL = "FAIL";

    @Autowired
    private   JdbcTemplate jdbcTemplate;

    private Random random;

    @Override
    public String account(String userId, int money) {
        log.info("Account Service ... xid: " + RootContext.getXID());
        random = new Random();
        if (random.nextBoolean()){
            throw new RuntimeException("this is a mock Exception");
        }

        int result = jdbcTemplate.update(
                "update account_tbl set money = money - ? where user_id = ?",
                new Object[] {money, userId}
        );
        log.info("Account Service End ...");
        if (result ==1 ){
            return SUCCESS;
        }
        return FAIL;
    }
}
