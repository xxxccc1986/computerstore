package top.year21.computerstore.controller.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理文件读取的异常
 * @date 2022/7/13 15:46
 */
public class FileUploadIOException extends FileUploadException {

    public FileUploadIOException() {
        super();
    }

    public FileUploadIOException(String message) {
        super(message);
    }

    public FileUploadIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIOException(Throwable cause) {
        super(cause);
    }

    protected FileUploadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
