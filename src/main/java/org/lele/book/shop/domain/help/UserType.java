package org.lele.book.shop.domain.help;

/**
 * author  shuly
 * date    17-10-19
 * description:
 */

public enum UserType {
    None,    // 无身份
    Purple,  // 普通人,　下单,　完成.
    Trader,  // 具有货品管理权限，　接单
    Admin,   //　能够管理人，trader,
    Master   // 一切权限
}
