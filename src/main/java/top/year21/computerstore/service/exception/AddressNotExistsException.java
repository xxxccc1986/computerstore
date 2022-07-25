package top.year21.computerstore.service.exception;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 表示地址不存在的异常
 * @date 2022/7/14 23:09
 */
public class AddressNotExistsException extends ServiceException {
    public AddressNotExistsException() {
        super();
    }

    public AddressNotExistsException(String message) {
        super(message);
    }

    public AddressNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotExistsException(Throwable cause) {
        super(cause);
    }

    protected AddressNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
