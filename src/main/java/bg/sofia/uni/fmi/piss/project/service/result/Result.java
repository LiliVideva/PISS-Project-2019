package bg.sofia.uni.fmi.piss.project.service.result;

import org.springframework.http.HttpStatus;

public class Result<T> {
    private T entity;
    private int status;
    private String message;
    private String field;

    public Result(T entity, int status, String message) {
        this.entity = entity;
        this.status = status;
        this.message = message;
    }

    public Result(int status) {
        this(null, status, null);
    }

    public Result(int status, String message) {
        this(null, status, message);
    }

    public Result(T entity, int status) {
        this(entity, status, null);
    }

    public Result(int status, String field, String message) {
        this.status = status;
        this.field = field;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        switch (status) {
            case 200:
                return HttpStatus.OK;
            case 201:
                return HttpStatus.CREATED;
            case 204:
                return HttpStatus.NO_CONTENT;
            case 400:
                return HttpStatus.BAD_REQUEST;
            case 404:
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public boolean isOk() {
        return status == HttpStatus.OK.value() || status == HttpStatus.CREATED.value();
    }

    public boolean isBadRequest() {
        return status == HttpStatus.BAD_REQUEST.value();
    }

    public boolean isNotFound() {
        return status == HttpStatus.NOT_FOUND.value();
    }

    public T getEntity() {
        return entity;
    }

    public int getStatus() {
        return status;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
