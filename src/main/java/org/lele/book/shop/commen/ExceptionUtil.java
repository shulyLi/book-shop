package org.lele.book.shop.commen;


import com.google.common.base.Throwables;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Enumeration;

public class ExceptionUtil {
    private final static char COLON    = ':';
    private final static char NEWLINES = '\n';
    private static String HOSTNAME;

    static {
        try {
            HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            HOSTNAME = "UnknownHost";
        }
    }

    public static String print(HttpServletRequest request, Throwable e) {
        StringBuffer sb = new StringBuffer().append(NEWLINES);
        //sb.append("RequestID").append(COLON).append(request.getAttribute(Constant.REQUEST_ID)).append(NEWLINES);
        sb.append("HostName").append(COLON).append(HOSTNAME).append(NEWLINES);
        sb.append("Time").append(COLON).append(LocalDateTime.now().toString()).append(NEWLINES);
        sb.append("Error").append(COLON).append(Throwables.getRootCause(e).getMessage()).append(NEWLINES);
        sb.append("Resources").append(COLON).append(request.getMethod()).append(" ").append(request.getRequestURI()).append(NEWLINES);
        sb.append("RequestParameters {").append(NEWLINES);
        request.getParameterMap().keySet().forEach(k -> sb.append("\t").append(k).append(COLON).append(request.getParameter(k)).append(NEWLINES));
        sb.append("}").append(NEWLINES);
        sb.append("RequestHeaders {").append(NEWLINES);
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String key = headers.nextElement().toString();
            sb.append("\t").append(key).append(":").append(request.getHeader(key)).append(NEWLINES);
        }
        sb.append("}").append(NEWLINES);
        sb.append(NEWLINES);
        sb.append(Throwables.getStackTraceAsString(e));

        return sb.toString();
    }
}
