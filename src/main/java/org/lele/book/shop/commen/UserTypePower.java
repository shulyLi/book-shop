package org.lele.book.shop.commen;

import com.google.common.collect.Lists;
import org.lele.book.shop.domain.BookUser;

import java.util.List;

/**
 * author  shuly
 * date    17-10-26
 * description:
 */

public class UserTypePower {

    public static List<String> powers(BookUser user){
        if (UserCheck.isAdmin(user)) {
            return Lists.newArrayList("good", "user", "order");
        }
        return Lists.newArrayList("order");
    }
}
