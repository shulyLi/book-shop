package org.lele.book.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * author  shuly
 * date    17-10-26
 * description:
 */

@Controller
@RequestMapping(value = "/upload")
public class PicController {
    private Logger logger = LoggerFactory.getLogger(PicController.class);
    @ResponseBody
    @RequestMapping(value = "/book/head", method = RequestMethod.POST)
    public ResponseEntity uploadBookHead(HttpServletRequest request, MultipartFile picture) throws IOException {
/*        logger.info("fileName："+picture.getOriginalFilename());
        String path = "/";
        picture.transferTo(new File(path));*/
        return ResponseEntity.ok("");
    }
}
