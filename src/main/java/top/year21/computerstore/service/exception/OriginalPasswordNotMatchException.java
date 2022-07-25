package top.year21.computerstore.service.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 表示原密码不正确的异常
 * @date 2022/7/11 23:03
 */
public class OriginalPasswordNotMatchException extends ServiceException{
    public OriginalPasswordNotMatchException() {
        super();
    }

    public OriginalPasswordNotMatchException(String message) {
        super(message);
    }

    public OriginalPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public OriginalPasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected OriginalPasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
