package simpleDI.exceptions;

/**
 * Created by sleiman on 31/03/2014.
 */
public class CyclicDependencyException extends InvalidDIBindingException {
    public CyclicDependencyException(String message) {
        super(message);
    }
}
