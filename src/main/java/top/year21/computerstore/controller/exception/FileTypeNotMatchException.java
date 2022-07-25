package top.year21.computerstore.controller.exception;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理文件上传类型不符的异常
 * @date 2022/7/13 15:46
 */
public class FileTypeNotMatchException extends FileUploadException {

    public FileTypeNotMatchException() {
        super();
    }

    public FileTypeNotMatchException(String message) {
        super(message);
    }

    public FileTypeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeNotMatchException(Throwable cause) {
        super(cause);
    }

    protected FileTypeNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
