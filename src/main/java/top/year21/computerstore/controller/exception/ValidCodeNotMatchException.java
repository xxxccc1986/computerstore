package top.year21.computerstore.controller.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 表示验证码错误的异常
 * @date 2022/7/24 14:34
 */
public class ValidCodeNotMatchException extends RuntimeException {
    public ValidCodeNotMatchException() {
        super();
    }

    public ValidCodeNotMatchException(String message) {
        super(message);
    }

    public ValidCodeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidCodeNotMatchException(Throwable cause) {
        super(cause);
    }

    protected ValidCodeNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
