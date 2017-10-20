package org.lele.book.shop.controller.exception;


import com.google.common.net.MediaType;
import org.lele.book.shop.commen.ExceptionUtil;
import org.lele.book.shop.exception.BookShopSystemException;
import org.lele.book.shop.exception.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        BookShopSystemException err;

        if (ex instanceof BookShopSystemException) {
            err = (BookShopSystemException) ex;
        } else if (ex instanceof IllegalArgumentException || ex instanceof MethodArgumentTypeMismatchException) {
            err = new BookShopSystemException(Errors.InvalidArgument, ex);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            err = new BookShopSystemException(Errors.MethodNotAllowed, ex);
        } else if (ex instanceof MissingServletRequestParameterException) {
            err = new BookShopSystemException(Errors.MissingRequiredParameter, ex);
        } else {
            err = new BookShopSystemException(Errors.InternalError, ex);
        }

        String error = ExceptionUtil.print(request, err);
        if (err.err == Errors.InternalError) {
            logger.error("Hello Error{}", error);
        } else {
            logger.warn("RuntimeException {}", error);
        }

        response.setContentType(MediaType.JSON_UTF_8.toString());
        response.setStatus(err.getHttpCode());
        try {
            response.getWriter().write(err.toString());
            response.flushBuffer();
        } catch (IOException ignore) {
        }
        return new ModelAndView();
    }
}