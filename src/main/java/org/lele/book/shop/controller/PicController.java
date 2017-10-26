package org.lele.book.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;


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
    public ResponseEntity uploadBookHead(@RequestParam(value = "picture") final CommonsMultipartFile picture) throws IOException {        logger.info("fileName："+picture.getOriginalFilename());
        URL path = this.getClass().getResource("/");
        String pathStr = path.getFile() + "../../upload/head/";
        String name =  String.valueOf(new Random().nextInt(100)) + System.currentTimeMillis() + picture.getOriginalFilename();
        picture.transferTo(new File(pathStr + name));
        return ResponseEntity.ok("/upload/head/" + name);
    }
}
