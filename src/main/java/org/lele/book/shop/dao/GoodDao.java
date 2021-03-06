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
    int hadSell(@Param("id") int id, @Param("cnt") int sell);
    BookGood select(@Param("id") int id);
    List<BookGoodSummary> selectIndex(@Param("name") String name, @Param("tag")String tag, @Param("l") int l, @Param("r") int r);
    List<BookGoodSummary> selectList(@Param("size") int size, @Param("flag") int flag);
}
