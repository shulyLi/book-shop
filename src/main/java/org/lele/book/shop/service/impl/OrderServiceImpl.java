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
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;
import org.lele.book.shop.service.GoodService;
import org.lele.book.shop.service.OrderService;
import org.lele.book.shop.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.NESTED)
    public void createOrder(String sso, int bookId, int cnt, String buyName, String buyPhone, String add1, String add2) {
        BookUser user = userService.getUser(sso);
        BookOrder order = new BookOrder();
        BookGood good = goodService.queryBook(bookId);
        Assert.assertion(good != null, Errors.NoSuchBook, "没有这个货物");
        Assert.assertion(good.stock > cnt, Errors.LowerStock, "{} now is {}, need{}", bookId, good.stock, cnt);
        order.userId = user.id;
        order.orderNo = OrderUtils.allocateOrderNo(System.currentTimeMillis(), serverId);
        order.bookId = bookId;
        order.state = State.Submit;
        order.cnt = cnt;
        order.payFee = good.price * cnt + 20 * 100;
        order.payType = PayType.Arrive_PAY;
        order.receiveName = buyName;
        order.receivePhone = buyPhone;
        order.addressHead = add1;
        order.addressTail = add2;
        order.makeDetail(user, good);
        orderDao.insert(order);
        goodService.reduceStock(bookId, cnt);
    }

    @Override
    public List<BookOrder> listOrder(String sso) {
        BookUser user = userService.getUser(sso);
        // admin　都可以看到
        if (user.userType == UserType.Admin || user.userType == UserType.Master) {
            return orderDao.selectList(null, null, Integer.MAX_VALUE, Long.MAX_VALUE);
        } else {
            // 只能看自己的
            return orderDao.selectList(user.id, null, Integer.MAX_VALUE, Long.MAX_VALUE);
        }
    }

    @Override
    public BookOrder getOrder(Long orderNo, String sso) {
        BookUser user = userService.getUser(sso);
        BookOrder order = orderDao.select(orderNo);
        Assert.assertion(order != null, Errors.NoSuchOrder, "么有这个订单");
        if (order.userId != user.id && user.userType != UserType.Admin && user.userType != UserType.Master) {
            throw new BookShopSystemException(Errors.MethodNotAllowed, "你没有权限");
        }
        return order;
    }
}
