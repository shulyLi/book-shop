package org.lele.book.shop.exception;


import java.util.LinkedHashMap;
import java.util.Map;

public enum Errors {
    NoSuchOrder(404, "NoSuchOrder", "The specified order does not exist."),
    NoSuchBook(404, "NoSuchBook", "The specified book does not exist."),
    MailHasUsed(404, "MailHasUsed", "the mail has used"),
    NoSuchUser(403, "NoSuchUser", "The specified user does not exist."),
    MissingContentLength(400, "MissingContentLength", "You must provide the Content-Length HTTP header."),
    MissingRequiredParameter(400, "MissingRequiredParameter", "Your request is missing a required parameter."),
    MalformedJson(400, "MalformedJson", "The JSON you provided was not well-formed or did not validate against our published schema."),
    EntityTooLarge(400, "EntityTooLarge", "Your proposed content exceeds the maximum allowed order size."),
    OperationAborted(409, "OperationAborted", "A conflicting conditional operation is currently in progress against this resource."),
    RequestTimeout(400, "RequestTimeout", "Your socket connection to the server was not read from or written to within the timeout period."),
    ConnectionLoss(400, "ConnectionLoss", "Your socket connection to the server may be lost."),
    InvalidArgument(400, "InvalidArgument", "One of your provided argument is malformed or otherwise invalid."),
    IllegalOrderStatus(409, "IllegalOrderStatus", "The request is not valid with the current state of the order."),
    IncorrectAmount(409, "IncorrectAmount", "Incorrect payment amount."),
    MethodNotAllowed(405, "MethodNotAllowed", "The specified method is not allowed against this resource."),
    InternalError(500, "InternalError", "We encountered an internal error. Please try again.");
    int httpCode;
    Map<String, String> err = new LinkedHashMap<>();
    String code;

    Errors(int httpCode, String code, String message) {
        this.httpCode = httpCode;
        err.put("err", code);
        err.put("msg", message);
        this.code = code;
    }
}
