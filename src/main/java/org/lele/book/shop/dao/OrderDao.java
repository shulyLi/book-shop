package org.lele.book.shop.dao;

import org.apache.ibatis.annotations.Param;
import org.lele.book.shop.domain.BookOrder;
import org.lele.book.shop.domain.BookOrderHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author  shuly
 * date    17-10-19
 * description:
 */
@Repository
public interface OrderDao {
    int insert(BookOrder bookOrder);
    int insertHistory(BookOrderHistory history);
    int updateOrder(@Param("orderNo") long orderNo, @Param("state") int state);

    List<BookOrder> selectByUserId(@Param("userId") int userId, @Param("size") int size, @Param("flag") int flag);
    List<BookOrder> selectByBookId(@Param("bookId") int bookId, @Param("size") int size, @Param("flag") int flag);
    
    BookOrder select(@Param("orderNo") long orderNo);
    BookOrderHistory selectHistory(@Param("orderNo") long orderNo);
}