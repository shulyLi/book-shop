package org.lele.book.shop.commen.param.good;

import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.param.ControllerParam;
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;
import org.springframework.util.StringUtils;

/**
 * @author shuly
 * @date 2017/10/22.
 */
public class IndexParam implements ControllerParam {
    public int      page;
    public String[] tags = new String[0];
    public String   name = "";


    @Override
    public void checkAndFull() throws BookShopSystemException {
        Assert.assertion(page >= 0, Errors.InvalidArgument, "这个page %d", page);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTags(String tags) {
        if (!StringUtils.isEmpty(tags)) {
            this.tags = tags.split(",");
        }

    }

    public void setName(String name) {
        this.name = name;
    }
}
