package org.lele.book.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import org.lele.book.shop.commen.StringView;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookGood extends StringView {
    public int id;
    public String bookName;
    public String tag;
    public String bookAuthor;
    public String bookHead;
    public int price;
    public int sellCnt;
    public String simpleDesc;
    public boolean isBlack;
    public int stock;
    public String betterPart;
    public String index;
    public String detail;
    @JsonIgnore
    public LocalDateTime updateTime;
    @JsonIgnore
    public LocalDateTime createTime;

    public Map<String, Object> toMap() {
        Map<String, Object> ans = Maps.newHashMap();
        ans.put("id", id);
        ans.put("bookName", bookName);
        ans.put("bookAuthor", bookAuthor);
        ans.put("bookHead", bookHead);
        ans.put("price", price);
        ans.put("sellCnt", sellCnt);
        ans.put("simpleDesc", simpleDesc);
        ans.put("tag", tag);
        ans.put("isBlack", isBlack);
        ans.put("stock", stock);
        ans.put("betterPart", betterPart);
        ans.put("index", index);
        ans.put("detail", detail);
        return ans;
    }
}
