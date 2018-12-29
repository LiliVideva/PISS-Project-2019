package bg.sofia.uni.fmi.piss.project.exception;

import bg.sofia.uni.fmi.piss.project.service.result.Result;

public class VerificationException extends RuntimeException {
    private static final long serialVersionUId = -8029469635695013612L;

    public static final int REASON_BAD_REQUEST = 1;

    public int reason;

    public VerificationException() {
        super();
    }

    public VerificationException(String message) {
        super(message);
    }

    public VerificationException(String message, int reason) {
        super(message);
        this.reason = reason;
    }

    public VerificationException(Result<?> result) {
        this(result.getMessage());
    }
}
