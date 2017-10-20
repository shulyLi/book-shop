package org.lele.book.shop.domain;

import org.lele.book.shop.commen.StringView;
import org.lele.book.shop.domain.help.Operator;
import org.lele.book.shop.domain.help.State;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookOrderHistory extends StringView {
    public int      id;
    public long     orderNo;
    public State    state;
    public String   message;
    public Operator operator;
    public LocalDateTime createTime;
}
