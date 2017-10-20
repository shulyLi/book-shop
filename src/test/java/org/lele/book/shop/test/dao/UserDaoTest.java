package org.lele.book.shop.test.dao;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.lele.book.shop.test.BaseTest;
import org.lele.book.shop.dao.UserDao;
import org.lele.book.shop.domain.BookUser;
import org.lele.book.shop.domain.help.UserType;

import javax.annotation.Resource;
import java.util.Map;

/**
 * author  shuly
 * date    17-10-20
 * description:
 */

public class UserDaoTest extends BaseTest {

    @Resource
    private UserDao userDao;

    @Test
    public void insertTest() {
        BookUser user = new BookUser();
        user.email      = "632725532@qq.com";
        user.passWord   = "1234567890";
        user.userName   = "shuly";
        user.userHead   = "nimahi";
        user.userMark   = "userMark";
        user.userType   = UserType.Admin;
        user.isDel      = false;
        user.isBlack    = false;
        userDao.insert(user);
        logger.info(user.toString());
    }
    @Test
    public void query(){
        Map<String, Object> param = Maps.newHashMap();
        param.put("id", 1);
        logger.info(userDao.select(param).toString());
    }
}
