package org.lele.book.shop.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import org.lele.book.shop.commen.Assert;
import org.lele.book.shop.commen.Sso;
import org.lele.book.shop.commen.UserCheck;
import org.lele.book.shop.dao.UserDao;
import org.lele.book.shop.domain.BookOrder;
import org.lele.book.shop.domain.BookUser;
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;
import org.lele.book.shop.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author shuly
 * @date 2017/10/21.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    // 本來這個 最好 放進 redis 的 調價有限  保存五分鐘，操作會刷新時間
    private Cache<String, Integer> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES).build();

    @Override
    public String userRegister(String password, String email, String userName) {
        BookUser user = new BookUser();
        user.passWord = password;
        user.email    = email;
        user.userName = userName;
        user.defaultValue();
        try {
            userDao.insert(user);
        } catch (DuplicateKeyException e) {
            throw new BookShopSystemException(Errors.MailHasUsed, "郵箱已經占用");
        }
        String sso = Sso.ssoCode();
        cache.put(sso, user.id);
        return sso;
    }

    @Override
    public String userLogin(String password, String email) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("passWord", password);
        param.put("email", email);
        BookUser user = userDao.select(param);
        Assert.assertion(user != null, Errors.NoSuchUser, "密碼或者賬戶不對");
        String sso = Sso.ssoCode();
        cache.put(sso, user.id);
        return sso;
    }

    @Override
    public Integer ssoUserId(String ssoCode) {
        Integer uid =  (cache).getIfPresent(ssoCode);
        Assert.assertion(uid != null, Errors.NoSuchUser, "没有这个用户");
        return uid;
    }

    @Override
    public BookUser getUser(String ssoCode) {
        return this.getUser(this.ssoUserId(ssoCode));
    }


    @Override
    public BookUser getMyIgnore(String sso) {
        Integer uid =  (cache).getIfPresent(sso);
        if (uid == null) return null;
        Map<String, Object> param = Maps.newHashMap();
        param.put("id", uid);
        return userDao.select(param);
    }
    @Override
    public BookUser getUser(Integer userId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("id", userId);
        BookUser user = userDao.select(param);
        Assert.assertion(user != null, Errors.NoSuchOrder, "沒有這個用戶%d", userId);
        return user;
    }

    @Override
    public List<BookUser> listUser(String sso) {
        BookUser who = this.getUser(sso);
        Assert.assertion(UserCheck.isAdmin(who), Errors.MethodNotAllowed, "您没有这个权限");
        return userDao.selectList(null, null, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}