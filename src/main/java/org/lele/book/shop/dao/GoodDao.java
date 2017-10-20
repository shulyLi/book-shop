package org.lele.book.shop.dao;

import org.apache.ibatis.annotations.Param;
import org.lele.book.shop.domain.BookGood;
import org.lele.book.shop.domain.BookGoodSummary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * author  shuly
 * date    17-10-19
 * description:
 */
@Repository
public interface GoodDao {
    int insert(BookGood good);
    int update(Map map);
    int hadSell(@Param("id") int id);
    BookGood select(@Param("id") int id);
    List<BookGoodSummary> selectIndex(@Param("size") int size, @Param("flag") int flag);
    List<BookGoodSummary> selectList(@Param("size") int size, @Param("flag") int flag);
}
