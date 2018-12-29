package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.exception.VerificationException;
import bg.sofia.uni.fmi.piss.project.security.AuthUser;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {

    static AuthUser getAuthenticatedUser() {
        return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    static <T> Result<T> verify(Result<T> result) {
        if (!result.isOk()) {
            throw new VerificationException(result);
        }
        return result;
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T entity) {
        return new Result<>(entity, HttpStatus.OK.value());
    }

    public static <T> Result<T> ok(String message) {
        return new Result<>(null, HttpStatus.OK.value(), message);
    }

    public static <T> Result<T> created(T entity) {
        return new Result<>(entity, HttpStatus.CREATED.value());
    }

    public static <T> Result<T> badRequest() {
        return badRequest("Bad request");
    }

    public static <T> Result<T> badRequest(String message) {
        return new Result<>(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static <T> Result<T> badRequest(String field, String message) {
        return new Result<>(HttpStatus.BAD_REQUEST.value(), field, message);
    }

    public static <T> Result<T> notFound() {
        return new Result<>(HttpStatus.NOT_FOUND.value(), "Resource not found");
    }

    public static <T> Result<T> serverError() {
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
    }

    public static <T> Result<T> noContent() {
        return new Result<>(HttpStatus.NO_CONTENT.value());
    }
}
