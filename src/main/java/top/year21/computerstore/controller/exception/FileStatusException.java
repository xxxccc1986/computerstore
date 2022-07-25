package top.year21.computerstore.controller.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理文件上传状态异常
 * @date 2022/7/13 15:46
 */
public class FileStatusException extends FileUploadException {

    public FileStatusException() {
        super();
    }

    public FileStatusException(String message) {
        super(message);
    }

    public FileStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStatusException(Throwable cause) {
        super(cause);
    }

    protected FileStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
