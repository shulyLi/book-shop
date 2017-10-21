package org.lele.book.shop.commen.param;

import org.lele.book.shop.exception.BookShopSystemException;

/**
 * @author shuly
 * @date 2017/10/21.
 */
public interface ControllerParam {
    void checkAndFull() throws BookShopSystemException;
}
