package org.lele.book.shop.domain;

import org.lele.book.shop.commen.StringView;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookComment extends StringView {
    public int      id;
    public int      userId;
    public long     orderNo;
    public int      goodId;
    public String   commont;
    public String   reply;
    public boolean  isDel;
    public boolean  isBlack;
    public LocalDateTime replyTime;
    public LocalDateTime createTime;
}
