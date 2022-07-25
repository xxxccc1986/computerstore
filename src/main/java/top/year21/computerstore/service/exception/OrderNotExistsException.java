package top.year21.computerstore.service.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 表示查询的订单不存在的异常
 * @date 2022/7/20 0:02
 */
public class OrderNotExistsException extends ServiceException{
    public OrderNotExistsException() {
        super();
    }

    public OrderNotExistsException(String message) {
        super(message);
    }

    public OrderNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotExistsException(Throwable cause) {
        super(cause);
    }

    protected OrderNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
