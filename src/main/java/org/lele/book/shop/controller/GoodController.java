package org.lele.book.shop.controller;

import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.CookieUtil;
import org.lele.book.shop.commen.JSON;
import org.lele.book.shop.commen.param.good.CreateGoodParam;
import org.lele.book.shop.commen.param.good.IndexParam;
import org.lele.book.shop.commen.param.good.UpdateGoodParam;
import org.lele.book.shop.domain.BookGoodSummary;
import org.lele.book.shop.exception.Errors;
import org.lele.book.shop.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * author  shuly
 * date    17-10-20
 * description:
 */
@Controller
@ResponseBody
@RequestMapping("/good")
public class GoodController {
    private Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Resource
    private GoodService goodService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(CreateGoodParam param,  HttpServletRequest request){
        String sso = CookieUtil.get(request, "user-sso");
        param.checkAndFull();
        goodService.createBook(sso, param);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity update(@PathVariable("id") Integer id, UpdateGoodParam param,  HttpServletRequest request){
        Assert.assertion(id != null, Errors.InvalidArgument, "id is null");
        String sso = CookieUtil.get(request, "user-sso");
        param.id = id;
        goodService.updateBook(sso, param);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getDetail(@PathVariable("id") Integer id){
        Assert.assertion(id != null, Errors.InvalidArgument, "id is null");
        return ResponseEntity.ok(goodService.queryBook(id));
    }

    @RequestMapping(value = "/index/summary", method = RequestMethod.GET)
    public ResponseEntity indexSummary(IndexParam param){
        param.checkAndFull();
        logger.info("index query -> [{}@{}:{}]", param.name, param.page, JSON.safeToJson(param.tags));
        List<BookGoodSummary> summaryList =  goodService.buildIndex(param.name, param.tags, param.page);
        return ResponseEntity.ok(summaryList);
    }

    @RequestMapping(value = "/list/summary", method = RequestMethod.GET)
    public ResponseEntity listSummary( HttpServletRequest request){
        String sso = CookieUtil.get(request, "user-sso");
        logger.info("book manger query");
        List<BookGoodSummary> summaryList = goodService.queryAll(sso);
        return ResponseEntity.ok(summaryList);
    }
}
