package top.year21.computerstore.service.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 表示商品状态异常
 * @date 2022/7/16 17:03
 */
public class ProductBadStatusException extends ServiceException {
    public ProductBadStatusException() {
        super();
    }

    public ProductBadStatusException(String message) {
        super(message);
    }

    public ProductBadStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductBadStatusException(Throwable cause) {
        super(cause);
    }

    protected ProductBadStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
