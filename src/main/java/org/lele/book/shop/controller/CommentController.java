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
@RequestMapping("/comment")
public class CommentController {
    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping(value = "/{orderNo}", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable("orderNo") String orderNo) {
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/{orderNo}/reply", method = RequestMethod.POST)
    public ResponseEntity reply(@PathVariable("orderNo") String orderNo) {
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{orderNo}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("orderNo") String orderNo) {
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public ResponseEntity listBook(@PathVariable("bookId") String bookId) {
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity listUser(@PathVariable("userId") String userId) {
        return ResponseEntity.noContent().build();
    }

}
