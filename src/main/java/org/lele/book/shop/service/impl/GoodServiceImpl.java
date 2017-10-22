package org.lele.book.shop.service.impl;

import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.JSON;
import org.lele.book.shop.dao.GoodDao;
import org.lele.book.shop.domain.BookGood;
import org.lele.book.shop.domain.BookGoodSummary;
import org.lele.book.shop.exception.Errors;
import org.lele.book.shop.service.GoodService;
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

    private static int pageSize = 10;

    @Override
    public List<BookGoodSummary> buildIndex(String name, String[] tag, int page) {
        return  goodDao.selectIndex(page * pageSize, pageSize );
    }

    @Override
    public List<BookGoodSummary> queryAll() {
        return goodDao.selectList(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public BookGood queryBook(Integer id) {
        BookGood good =  goodDao.select(id);
        Assert.assertion(good != null, Errors.NoSuchBook, "id :" + id);
        return good;
    }

    @Override
    public void createBook(BookGood good) {
        goodDao.insert(good);
    }

    @Override
    public void updateBook(BookGood good) {
        goodDao.update(good.toMap());
    }
}
