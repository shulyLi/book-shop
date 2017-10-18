package org.lele.book.shop.exception;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lele.book.shop.commen.JSON;

public class BookShopSystemException extends RuntimeException {
    public final Errors err;

    public BookShopSystemException(Errors err, String message) {
        super(err + ":" + message);
        this.err = err;
    }


    public BookShopSystemException(Errors err, Exception ex) {
        super(err + ":" + ex.getMessage(), ex);
        this.err = err;
    }

    public int getHttpCode() {
        return err.httpCode;
    }

    @Override
    public String toString(){
        ObjectNode json = JSON.createObjectNode();
        err.err.forEach(json::put);
        return json.toString();
    }
}
