package org.lele.book.shop.dao;

import org.apache.ibatis.annotations.Param;
import org.lele.book.shop.domain.BookComment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author  shuly
 * date    17-10-19
 * description:
 */
@Repository
public interface CommentDao {

    /**
     * @param orderNo 订单号
     * @return 有则结果，　无则null
     */

    BookComment selectByOrderNo(long orderNo);

    /**
     * @param userId    用户Id
     * @param size      长度
     * @param flag      游标
     * @return          结果
     */
    List<BookComment> selectByUserId(@Param("userId") int userId, @Param("size") int size, @Param("flag") int flag);

    /**
     * @param bookId    书Id
     * @param size      长度
     * @param flag      游标
     * @return          结果
     */
    List<BookComment> selectByGoodId(@Param("bookId") int bookId, @Param("size") int size, @Param("flag") int flag);

    /**
     * @param comment   评论
     * @return          １ or error
     */
    int insert(BookComment comment);

    /**
     * @param orderNo   订单
     * @param reply     回复
     * @return          影响个数
     */
    int updateReplay(@Param("orderNo") long orderNo,@Param("reply") String reply);
}
