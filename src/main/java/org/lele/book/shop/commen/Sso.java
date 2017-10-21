package org.lele.book.shop.commen;

/**
 * @author shuly
 * @date 2017/10/21.
 */
public class Sso {
    public static Long ssoCode(){
        long start = System.currentTimeMillis();
        start += Math.random();
        return start;
    }
}
