package org.lele.book.shop.service.impl;

import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.JSON;
import org.lele.book.shop.commen.UserCheck;
import org.lele.book.shop.dao.GoodDao;
import org.lele.book.shop.domain.BookGood;
import org.lele.book.shop.domain.BookGoodSummary;
import org.lele.book.shop.domain.BookUser;
import org.lele.book.shop.exception.Errors;
import org.lele.book.shop.service.GoodService;
import org.lele.book.shop.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuly
 * @date 2017/10/22.
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;
    @Resource
    private UserService userService;
    private static int pageSize = 10;

    @Override
    public List<BookGoodSummary> buildIndex(String name, String[] tag, int page) {
        String likeName = "%" + name + "%";
        StringBuilder likeTag  = new StringBuilder().append("%");
        for(String one : tag) {
            likeTag.append(one).append("%");
        }
        return  goodDao.selectIndex(likeName, likeTag.toString(), page * pageSize, pageSize );
    }

    @Override
    public List<BookGoodSummary> queryAll(String sso) {
        BookUser user = userService.getUser(sso);
        Assert.assertion(UserCheck.isAdmin(user), Errors.MethodNotAllowed, "权限不足");
        return goodDao.selectList(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public BookGood queryBook(Integer id) {
        BookGood good =  goodDao.select(id);
        Assert.assertion(good != null, Errors.NoSuchBook, "id :" + id);
        return good;
    }

    @Override
    public void createBook(String sso, BookGood good) {
        BookUser user = userService.getUser(sso);
        Assert.assertion(UserCheck.isAdmin(user), Errors.MethodNotAllowed, "权限不足");
        goodDao.insert(good);
    }

    @Override
    public void updateBook(String sso, BookGood good) {
        BookUser user = userService.getUser(sso);
        Assert.assertion(UserCheck.isAdmin(user), Errors.MethodNotAllowed, "权限不足");
        goodDao.update(good.toMap());
    }
}
