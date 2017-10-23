package org.lele.book.shop.service;

import java.util.List;

/**
 * author  shuly
 * date    17-10-23
 * description:
 */

public interface OrderService {
    void createOrder(String sso, int bookId, int cnt, String buyName, String buyPhone, String addressHead, String addressTail);

    List listOrder(String sso);
}
