package org.lele.book.shop.commen;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * author  shuly
 * date    17-10-23
 * description:
 */

public class CookieUtil {


    public static String get(HttpServletRequest request, String name) {
        Cookie [] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }
        for(Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return "";
    }
}
