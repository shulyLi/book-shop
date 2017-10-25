package org.lele.book.shop.commen;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.lele.book.shop.domain.BookUser;

import java.io.IOException;
import java.util.Map;

/**
 * author  shuly
 * date    17-9-12.
 */
public class BookUserJsonSerializer extends JsonSerializer<BookUser> {
    @Override
    public void serialize(BookUser user, JsonGenerator json, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        json.writeStartObject();

        json.writeNumberField("id", user.id);
        json.writeStringField("email", user.email);
        json.writeStringField("userName", user.userName);
        json.writeStringField("userHead", user.userHead);
        json.writeStringField("userType", user.userType.toString());
        json.writeStringField("createTime", LocalDateTimeUtil.transBasicString(user.createTime));
        json.writeEndObject();
    }
}
