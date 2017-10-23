package org.lele.book.shop.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Maps;
import org.lele.book.shop.commen.JSON;
import org.lele.book.shop.commen.StringView;
import org.lele.book.shop.commen.BookOrderJsonSerializer;
import org.lele.book.shop.domain.help.PayType;
import org.lele.book.shop.domain.help.State;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */
@JsonSerialize(using = BookOrderJsonSerializer.class)
public class BookOrder extends StringView {
    public int      userId;
    public long     orderNo;
    public int      bookId;
    public int      cnt;
    public State    state;
    public PayType  payType;
    public int      payFee;
    public String   receiveName;
    public String   receivePhone;
    public String   addressHead;
    public String   addressTail;
    public String   detail;
    public LocalDateTime createTime;


    public void makeDetail(BookUser user, BookGood good){
        Map<String, Object> map = Maps.newHashMap();
        map.put("userName", user.userName);
        map.put("email", user.email);
        map.put("bookName", good.bookName);
        map.put("bookHead", good.bookHead);
        map.put("bookAuthor", good.bookAuthor);
        map.put("price", good.price);
        this.detail = JSON.safeToJson(map);
    }
}
