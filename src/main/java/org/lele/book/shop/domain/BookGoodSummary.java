package org.lele.book.shop.domain;

import org.lele.book.shop.commen.StringView;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-20
 * description:
 */

public class BookGoodSummary extends StringView {
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
    public LocalDateTime createTime;
}
