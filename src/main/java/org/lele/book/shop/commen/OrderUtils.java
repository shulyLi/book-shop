package org.lele.book.shop.commen;


import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OrderUtils {
    private static AtomicLong allocator = new AtomicLong(0);
    private static Random     random    = new Random();


    public static long allocateOrderNo(long time, int serverId) {
        time -= 1000000000000L;
        time /= 1000;
        time *= 10000000;
        int server = serverId * 100000;
        long value = allocator.getAndAdd(random.nextInt(16) + 1) & (0xffff); //[0 ~ 65535]
        time += server + value;
        return time;
    }
}
