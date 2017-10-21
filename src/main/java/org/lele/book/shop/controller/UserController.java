package org.lele.book.shop.controller;

import org.lele.book.shop.commen.param.user.UserLoginEntity;
import org.lele.book.shop.commen.param.user.UserRegisterEntity;
import org.lele.book.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Resource
    private UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(UserRegisterEntity entity) {
        entity.checkAndFull();
        logger.info("user [{}->{}] register", entity.email, entity.userName);

        Long sso = userService.userRegister(entity.password, entity.email, entity.userName);
        return ResponseEntity.ok(sso);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(UserLoginEntity entity,  HttpServletRequest httpRequest, HttpServletResponse response){
        entity.checkAndFull();
        logger.info("user [{}] login", entity.email);
        Long sso = userService.userLogin(entity.password, entity.email);
        return ResponseEntity.ok(sso);
    }

    // 下面的 先不 維護
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(){
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(){
        return ResponseEntity.noContent().build();
    }
}
