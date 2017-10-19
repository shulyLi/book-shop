package org.lele.book.shop.domain;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookComment {
    public int      id;
    public int      userId;
    public long     orderNo;
    public int      bookId;
    public String   commont;
    public String   reply;
    public boolean  isDel;
    public boolean  isBlack;
    public LocalDateTime replayTime;
    public LocalDateTime createTime;
}
