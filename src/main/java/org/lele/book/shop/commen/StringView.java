package org.lele.book.shop.commen;

public abstract class StringView {
    @Override
    public String toString() {
        return JSON.safeToJson(this);
    }
}
