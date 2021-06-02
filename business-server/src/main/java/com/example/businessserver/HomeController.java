package com.example.businessserver;

import com.example.AccountService;
import com.example.OrderService;
import com.example.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private static final String SUCCESS = "SUCCESS";

    private static final String FAIL = "FAIL";

    private static final String USER_ID = "U100001";

    private static final String COMMODITY_CODE = "C00321";

    private static final int ORDER_COUNT = 2;
    @Autowired
    private  RestTemplate restTemplate;
    @Reference
    private OrderService orderService;

    @Reference
    private StorageService storageService;


    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @RequestMapping(value = "/seata/rest", produces = "application/json", method = RequestMethod.GET)
    public String rest() {

        String result = storageService.storage(COMMODITY_CODE, ORDER_COUNT);

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException();
        }

        result = orderService.order(USER_ID, COMMODITY_CODE,ORDER_COUNT );

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException();
        }

        return SUCCESS;
    }

    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @RequestMapping(value = "/seata/feign", produces = "application/json", method = RequestMethod.GET)
    public String feign() {

        String result = storageService.storage(COMMODITY_CODE, ORDER_COUNT);

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException();
        }

        result = orderService.order(USER_ID, COMMODITY_CODE, ORDER_COUNT);

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException();
        }

        return SUCCESS;

    }
}
