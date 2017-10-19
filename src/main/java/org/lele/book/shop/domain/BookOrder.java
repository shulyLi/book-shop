package org.lele.book.shop.domain;

import org.lele.book.shop.domain.help.PayType;
import org.lele.book.shop.domain.help.State;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookOrder {
    public int      userId;
    public long     orderNo;
    public State    state;
    public PayType  payType;
    public int      payFee;
    public String   phone;
    public String   address;
    public String   detail;
    public LocalDateTime createTime;
}
