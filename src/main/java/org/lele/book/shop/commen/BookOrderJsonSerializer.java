package org.lele.book.shop.commen;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.lele.book.shop.domain.BookOrder;

import java.io.IOException;
import java.util.Map;

/**
 * author  shuly
 * date    17-9-12.
 */
public class BookOrderJsonSerializer extends JsonSerializer<BookOrder> {
    @Override
    public void serialize(BookOrder bookOrder, JsonGenerator json, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        json.writeStartObject();

        json.writeNumberField("orderNo", bookOrder.orderNo);
        json.writeStringField("addressHead", bookOrder.addressHead);
        json.writeStringField("addressTail", bookOrder.addressTail);
        json.writeStringField("receivePhone", bookOrder.receivePhone);
        json.writeStringField("receiveName", bookOrder.receiveName);
        json.writeStringField("state", bookOrder.state.toString());

        json.writeStringField("payType", bookOrder.payType.toString());
        json.writeStringField("payFee", String.format("%.2f元",bookOrder.payFee / 100.));
        json.writeNumberField("cnt", bookOrder.cnt);
        json.writeNumberField("bookId", bookOrder.bookId);
        json.writeNumberField("userId", bookOrder.userId);

        Map map = JSON.safeRead(bookOrder.detail, Map.class);

        if (map != null) {
            json.writeStringField("bookName", map.getOrDefault("bookName", "").toString());
            json.writeStringField("bookHead", map.getOrDefault("bookHead", "").toString());
            json.writeStringField("bookAuthor", map.getOrDefault("bookAuthor", "").toString());
            json.writeStringField("bookPrice", map.getOrDefault("price", "").toString());
        }

        json.writeStringField("createTime", LocalDateTimeUtil.transBasicString(bookOrder.createTime));
        json.writeEndObject();
    }
}
