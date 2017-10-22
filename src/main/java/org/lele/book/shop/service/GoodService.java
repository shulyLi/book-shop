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
    List<BookGoodSummary> queryAll();

    BookGood queryBook(Integer id);

    void createBook(BookGood good);

    void updateBook(BookGood good);
}
