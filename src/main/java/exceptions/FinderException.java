package exceptions;

/**
 * Generic exception for this assignment.
 */
public class FinderException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7897304272534933627L;

    public FinderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FinderException(String message) {
        super(message);
    }
}
