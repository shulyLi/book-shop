package org.lele.book.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostConstruct
    public void init() {
        logger.info("AOAOAOSS");
        logger.error("aoa");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity outLog(String msg) {
        logger.info(msg);
        return ResponseEntity.noContent().build();
    }
}
