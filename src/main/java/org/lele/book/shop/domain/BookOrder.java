package org.lele.book.shop.domain;

import org.lele.book.shop.commen.StringView;
import org.lele.book.shop.domain.help.PayType;
import org.lele.book.shop.domain.help.State;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookOrder extends StringView {
    public int      userId;
    public long     orderNo;
    public int      bookId;
    public State    state;
    public PayType  payType;
    public int      payFee;
    public String   receiveName;
    public String   receivePhone;
    public String   addressHead;
    public String   addressTail;
    public String   detail;
    public LocalDateTime createTime;
}
