package org.lele.book.shop.commen;

import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assert {

    private static final Logger logger = LoggerFactory.getLogger(Assert.class);

    public static void assertion(boolean expression, Errors error, String format, Object... args) {
        if (!expression) {
            BookShopSystemException ex = new BookShopSystemException(error, String.format(format, args));
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    public static void assertion(BindingResult result, Errors error) {
        if (result.hasErrors()) {
            BookShopSystemException ex = new BookShopSystemException(error, result.getFieldError().getDefaultMessage());
            logger.error(ex.getMessage());
            throw ex;
        }
    }


    public static void match(String m, String p, Errors error, String format, Object... args) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(m);
        if (!matcher.matches()) {
            BookShopSystemException ex = new BookShopSystemException(error, String.format(format, args));
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    public static <T, R> R run(Function<T, R> function, T arg, Errors error, String format, Object... args) {
        try {
            return function.apply(arg);
        } catch (Exception e) {
            throw new BookShopSystemException(error, String.format(format, args));
        }
    }

    public static <T, U, R> R run(BiFunction<T, U, R> function, T arg1, U arg2, Errors error, String format, Object... args) {
        try {
            return function.apply(arg1, arg2);
        } catch (Exception e) {
            throw new BookShopSystemException(error, String.format(format, args));
        }
    }
}
