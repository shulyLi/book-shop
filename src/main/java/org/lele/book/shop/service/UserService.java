package org.lele.book.shop.service;


import org.lele.book.shop.domain.BookUser;

/**
 * @author shuly
 * @date 2017/10/21.
 */

public interface UserService {
    /**
     * @param password 密碼
     * @param email    郵箱
     * @return 加密的獨特 密匙
     */
    Long userRegister(String password, String email, String userName);


    /**
     * @param password 密碼
     * @param email    郵箱
     * @return 加密的獨特 密匙
     */
    Long userLogin(String password, String email);

    Integer ssoUserId(Long ssoCode);

    BookUser getUser(Integer userId);
}



