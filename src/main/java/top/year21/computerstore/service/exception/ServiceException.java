package top.year21.computerstore.service.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 专用于处理业务层的异常基类
 * @date 2022/7/10 21:59
 */
//e.g. throws new ServiceException("业务层出现异常")
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
