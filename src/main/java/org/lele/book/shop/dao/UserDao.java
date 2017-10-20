package org.lele.book.shop.dao;

import org.apache.ibatis.annotations.Param;
import org.lele.book.shop.domain.BookUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * author  shuly
 * date    17-10-19
 * description:
 */
@Repository
public interface UserDao {

    int insert(BookUser user);
    int update(Map map);

    BookUser select(Map map);
    List<BookUser> selectList(@Param("name") String name, @Param("email") String email, @Param("size") int size, @Param("flag") int flag);
}
