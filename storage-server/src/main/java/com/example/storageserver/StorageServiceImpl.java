package com.example.storageserver;

import com.example.StorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@org.apache.dubbo.config.annotation.Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    private static final String SUCCESS = "SUCCESS";

    private static final String FAIL = "FAIL";

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @Override
    public String storage(String commodityCode, int count) {
        log.info("Storage Service Begin ... xid: " + RootContext.getXID());

        int result = jdbcTemplate.update(
                "update storage_tbl set count = count - ? where commodity_code = ?",
                new Object[] { count, commodityCode }
        );
        log.info("Storage Service End ... ");
        if (result == 1 ){
            return SUCCESS;
        }
        return FAIL;
    }
}
