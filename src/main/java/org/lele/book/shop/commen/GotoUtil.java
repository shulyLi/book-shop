package org.lele.book.shop.commen;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * author  shuly
 * date    17-10-23
 * description:
 */

public class GotoUtil {
    public static Map<String, Object> go(String path, String message) {
        Map<String, Object> ans = Maps.newHashMap();
        ans.put("go", path);
        ans.put("msg", message);
        return ans;
    }
}
