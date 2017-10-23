package org.lele.book.shop.commen.param.order;

import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.param.ControllerParam;
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;
import org.springframework.util.StringUtils;

/**
 * author  shuly
 * date    17-10-23
 * description:
 */

public class CreateOrderEntity implements ControllerParam {

    public int bookId;
    public int cnt;
    public String detailAddress;
    public String province;
    public String city;
    public String county;
    public String buyName;
    public String buyPhone;

    @Override
    public void checkAndFull() throws BookShopSystemException {
        Assert.assertion(bookId > 0, Errors.InvalidArgument, "bookId <= 0");
        Assert.assertion(cnt > 0, Errors.InvalidArgument, "cnt <= 0");
        Assert.assertion(! StringUtils.isEmpty(detailAddress), Errors.InvalidArgument, "具体地址为空");
        Assert.assertion(! StringUtils.isEmpty(buyPhone), Errors.InvalidArgument, "手机号为空");
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public void setBuyPhone(String buyPhone) {
        this.buyPhone = buyPhone;
    }

    public String createAddress() {
        StringBuilder sb = new StringBuilder();
        if (province!= null) sb.append(province).append(",");
        if (city != null) sb.append(city).append(",");
        if (county != null) sb.append(county).append(";");
        return sb.toString();
    }
}
