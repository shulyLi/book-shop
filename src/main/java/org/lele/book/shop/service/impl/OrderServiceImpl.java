package org.lele.book.shop.service.impl;

import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.OrderUtils;
import org.lele.book.shop.dao.OrderDao;
import org.lele.book.shop.domain.BookGood;
import org.lele.book.shop.domain.BookOrder;
import org.lele.book.shop.domain.BookUser;
import org.lele.book.shop.domain.help.PayType;
import org.lele.book.shop.domain.help.State;
import org.lele.book.shop.domain.help.UserType;
import org.lele.book.shop.exception.Errors;
import org.lele.book.shop.service.GoodService;
import org.lele.book.shop.service.OrderService;
import org.lele.book.shop.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author  shuly
 * date    17-10-23
 * description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private GoodService goodService;

    @Resource
    private UserService userService;

    @Value("#{config['server.id']}")
    private int serverId = 1;

    @Override
    public void createOrder(String sso, int bookId, int cnt, String buyName, String buyPhone, String add1, String add2) {
        Integer userId = userService.ssoUserId(sso);

        Assert.assertion(userId != null, Errors.NoSuchUser, "兄弟你没有登陆, 或者已过期限的id");
        BookOrder order = new BookOrder();
        BookGood good = goodService.queryBook(bookId);
        Assert.assertion(good != null, Errors.NoSuchBook, "没有这个货物");

        order.userId = userId;
        order.orderNo = OrderUtils.allocateOrderNo(System.currentTimeMillis(), serverId);
        order.bookId = bookId;
        order.state = State.Submit;
        order.payFee = good.price * cnt + 20;
        order.payType = PayType.Arrive_PAY;
        order.receiveName = buyName;
        order.receivePhone = buyPhone;
        order.addressHead = add1;
        order.addressTail = add2;
        order.detail = "";
        orderDao.insert(order);
    }

    @Override
    public List listOrder(String sso) {
        Integer userId = userService.ssoUserId(sso);
        Assert.assertion(userId != null, Errors.NoSuchUser, "兄弟你没有登陆, 或者已过期限的id");

        BookUser user = userService.getUser(userId);
        // admin　都可以看到
        if (user.userType == UserType.Admin || user.userType == UserType.Master) {
            return orderDao.selectList(null, null, Integer.MAX_VALUE, Long.MAX_VALUE);
        } else {
            // 只能看自己的
            return orderDao.selectList(userId, null, Integer.MAX_VALUE, Long.MAX_VALUE);
        }
    }
}
