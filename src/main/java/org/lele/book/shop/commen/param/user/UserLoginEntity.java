package org.lele.book.shop.commen.param.user;

import com.alibaba.druid.util.StringUtils;
import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.param.ControllerParam;
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;

/**
 * @author shuly
 * @date 2017/10/21.
 */
public class UserLoginEntity implements ControllerParam {
    public String email;
    public String password;

    @Override
    public void checkAndFull() throws BookShopSystemException {
        Assert.assertion(!StringUtils.isEmpty(email), Errors.InvalidArgument, "email格式不對");
        Assert.assertion(!StringUtils.isEmpty(password), Errors.InvalidArgument, "password格式不對");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
