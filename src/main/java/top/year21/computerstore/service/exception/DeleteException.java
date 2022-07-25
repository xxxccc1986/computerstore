package top.year21.computerstore.service.exception;

import top.year21.computerstore.service.exception.ServiceException;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 表示删除失败的异常
 * @date 2022/7/16 0:03
 */
public class DeleteException extends ServiceException {
    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    protected DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
