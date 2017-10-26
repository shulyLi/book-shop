package org.lele.book.shop.service;

import org.lele.book.shop.domain.BookGood;
import org.lele.book.shop.domain.BookGoodSummary;

import java.util.List;

/**
 * @author shuly
 * @date 2017/10/22.
 */

public interface GoodService {
    List<BookGoodSummary> buildIndex(String name, String[] tag, int page);

    List<BookGoodSummary> queryAll(String sso);

    BookGood queryBook(Integer id);

    void createBook(String sso, BookGood good);

    void updateBook(String sso, BookGood good);

    void reduceStock(int goodId, int sell);
}
