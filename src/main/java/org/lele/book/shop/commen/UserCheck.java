package org.lele.book.shop.commen;

import com.google.common.collect.Sets;
import org.lele.book.shop.domain.BookUser;
import org.lele.book.shop.domain.help.UserType;

import java.util.Set;

/**
 * author  shuly
 * date    17-10-25
 * description:
 */

public class UserCheck {
    private static Set<UserType> master = Sets.newHashSet(UserType.Master, UserType.Admin);

    public static boolean isAdmin(UserType type) {
        return master.contains(type);
    }

    public static boolean isAdmin(BookUser user) {
        return user != null && (master.contains(user.userType) || user.id == 1);
    }

}
