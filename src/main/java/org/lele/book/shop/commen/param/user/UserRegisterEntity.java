package org.lele.book.shop.commen.param.user;

import com.alibaba.druid.util.StringUtils;
import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;

/**
 * @author shuly
 * @date 2017/10/21.
 */
public class UserRegisterEntity extends UserLoginEntity {

    public String userName;

    @Override
    public void checkAndFull() throws BookShopSystemException {
        super.checkAndFull();
        Assert.assertion(!StringUtils.isEmpty(userName), Errors.InvalidArgument, "userName格式不對");

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
