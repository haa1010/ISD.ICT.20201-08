package common.exception;
/**
 * The EcobikeException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 * @author hangntt
 * @version 1.0
 */
public class EcobikeException extends RuntimeException {

	/**
	 * Exception Construction
	 */
    public EcobikeException() {

    }

    /**
	 * Exception Construction
	 * @param String message
	 */
    public EcobikeException(String message) {
        super(message);
    }
}