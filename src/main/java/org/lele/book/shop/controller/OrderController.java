package org.lele.book.shop.controller;

import com.google.common.collect.Maps;
import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.CookieUtil;
import org.lele.book.shop.commen.param.order.CreateOrderEntity;
import org.lele.book.shop.exception.Errors;
import org.lele.book.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * author  shuly
 * date    17-10-20
 * description:
 */


@Controller
@ResponseBody
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(CreateOrderEntity entity, HttpServletRequest request) {
        String sso = CookieUtil.get(request, "user-sso");
        entity.checkAndFull();
        logger.info("create order [{}->goodId:num[{}:{}]", sso, entity.bookId, entity.cnt);
        orderService.createOrder(sso, entity.bookId, entity.cnt, entity.buyName, entity.buyPhone, entity.createAddress(), entity.detailAddress);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{orderNo}", method = RequestMethod.GET)
    public ResponseEntity orderDetail(@PathVariable("orderNo") Long orderNo, HttpServletRequest request){
        Assert.assertion( orderNo != null, Errors.InvalidArgument, "orderNo is empty");
        String sso = CookieUtil.get(request, "user-sso");
        logger.info("get order ");
        return ResponseEntity.ok( orderService.getOrder(orderNo, sso));
    }

    @RequestMapping(value = "/{orderNo}", method = RequestMethod.POST)
    public ResponseEntity orderStateChange(@PathVariable("orderNo") String orderNo){
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity listOrder(HttpServletRequest request){
        String sso = CookieUtil.get(request, "user-sso");
        logger.info("list order [{}]", sso);
        Map<String, Object> ans = Maps.newHashMap();
        ans.put("data", orderService.listOrder(sso));
        return ResponseEntity.ok(ans);
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public ResponseEntity listBookOrder(@PathVariable("bookId") String bookId){
        return ResponseEntity.noContent().build();
    }
}
