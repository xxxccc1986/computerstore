package top.year21.computerstore.controller.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理文件上传为空的异常
 * @date 2022/7/13 15:46
 */
public class FileEmptyException extends FileUploadException {

    public FileEmptyException() {
        super();
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    protected FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
