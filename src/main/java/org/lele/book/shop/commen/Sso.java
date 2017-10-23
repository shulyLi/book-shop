package org.lele.book.shop.commen;

import java.util.Random;

/**
 * @author shuly
 * @date 2017/10/21.
 */
public class Sso {
    private static Random random = new Random();
    public static String ssoCode(){
        long timeMillis = System.currentTimeMillis();
        long start = random.nextInt() & (0xf);
        start <<= 55;
        start += (timeMillis << 4);
        start += timeMillis;
        start += random.nextInt() & (0xf);
        return String.valueOf(start);
    }

    public static void main(String[] args) {
        System.out.println(ssoCode());
    }
}
