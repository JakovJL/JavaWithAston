package Module_3.Exeptions;

public class FileWriteException extends FileOperationException {
    public FileWriteException(String message) {
        super(message);
    }

    public FileWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}