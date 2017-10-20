package org.lele.book.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author  shuly
 * date    17-10-20
 * description:
 */

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register() {
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(){
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(){
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(){
        return ResponseEntity.noContent().build();
    }
}
