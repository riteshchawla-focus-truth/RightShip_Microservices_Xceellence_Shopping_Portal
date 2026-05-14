package software.testing.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String msg) { super(msg); }
}

