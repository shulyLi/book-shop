package org.lele.book.shop.domain;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookGood {
    public int      id;
    public String   bookName;
    public String   bookAuthor;
    public String   bookHead;
    public int      price;
    public int      sellCnt;
    public String   simpleDesc;
    public String   tag;
    public boolean  isBlack;
    public int      stock;
    public String   betterPart;
    public String   index;
    public String   detail;
    public LocalDateTime updateTime;
    public LocalDateTime createTime;
}
