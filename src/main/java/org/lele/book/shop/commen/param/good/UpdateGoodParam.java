package org.lele.book.shop.commen.param.good;

import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.param.ControllerParam;
import org.lele.book.shop.domain.BookGood;
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;
import org.springframework.util.StringUtils;

/**
 * @author shuly
 * @date 2017/10/22.
 */
public class UpdateGoodParam extends CreateGoodParam {

    @Override
    public void checkAndFull() throws BookShopSystemException {
        Assert.assertion(id != 0, Errors.InvalidArgument, "id 是 0");
        Assert.assertion(!StringUtils.isEmpty(bookName), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(bookAuthor), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(bookHead), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(simpleDesc), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(tag), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(betterPart), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(index), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(detail), Errors.InvalidArgument, "参数不正确");
    }
}
