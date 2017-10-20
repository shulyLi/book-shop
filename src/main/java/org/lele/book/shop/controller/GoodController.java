package org.lele.book.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/good")
public class GoodController {
    private Logger logger = LoggerFactory.getLogger(GoodController.class);

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(){
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity update(@PathVariable("id") String id){
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getDetail(@PathVariable("id") String id){
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/index/summary", method = RequestMethod.GET)
    public ResponseEntity indexSummary(){
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/list/summary", method = RequestMethod.GET)
    public ResponseEntity listSummary(){
        return ResponseEntity.noContent().build();
    }
}
