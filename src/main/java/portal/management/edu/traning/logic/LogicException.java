package portal.management.edu.traning.logic;

import java.io.Serial;

public class LogicException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public LogicException() {
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(Exception e) {
        super(e);
    }

    public LogicException(String message, Exception e) {
        super(message, e);
    }

}
