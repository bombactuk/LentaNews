package portal.management.edu.traning.dao;

import java.io.Serial;

public class DaoException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }

}
