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
public class CreateGoodParam extends BookGood implements ControllerParam {
    @Override
    public void checkAndFull() throws BookShopSystemException {
        Assert.assertion(id == 0 , Errors.InvalidArgument, "id 不是 0");
        Assert.assertion(!StringUtils.isEmpty(bookName), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(bookAuthor), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(bookHead), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(simpleDesc), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(tag), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(betterPart), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(index), Errors.InvalidArgument, "参数不正确");
        Assert.assertion(!StringUtils.isEmpty(detail), Errors.InvalidArgument, "参数不正确");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookHead(String bookHead) {
        this.bookHead = bookHead;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSellCnt(int sellCnt) {
        this.sellCnt = sellCnt;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setBetterPart(String betterPart) {
        this.betterPart = betterPart;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
