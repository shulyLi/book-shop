package org.lele.book.shop.controller;

import com.google.common.collect.Maps;
import org.lele.book.shop.commen.CookieUtil;
import org.lele.book.shop.commen.Sso;
import org.lele.book.shop.commen.param.user.UserLoginEntity;
import org.lele.book.shop.commen.param.user.UserRegisterEntity;
import org.lele.book.shop.domain.BookUser;
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
import java.util.List;
import java.util.Map;

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
        String sso = userService.userRegister(entity.password, entity.email, entity.userName);
        Map<String, Object > ans = Maps.newHashMap();
        ans.put("data", sso);
        return ResponseEntity.ok(ans);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(UserLoginEntity entity, HttpServletRequest httpRequest) {
        entity.checkAndFull();
        String sso = userService.userLogin(entity.password, entity.email);
        logger.info("user [{}->{}] login", entity.email, sso);
        Map<String, Object > ans = Maps.newHashMap();
        ans.put("data", sso);
        return ResponseEntity.ok(ans);
    }

    // 下面的 先不 維護
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update() {
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(HttpServletRequest httpRequest) {
        String sso = CookieUtil.get(httpRequest, "user-sso");
        logger.info("list user -> [sso:{}]", sso);
        List<BookUser> userList = userService.listUser(sso);
        return ResponseEntity.ok(userList);
    }
}
