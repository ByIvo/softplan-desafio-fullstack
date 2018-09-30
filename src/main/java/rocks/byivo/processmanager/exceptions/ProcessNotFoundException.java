package rocks.byivo.processmanager.exceptions;

public class ProcessNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ProcessNotFoundException(Long idProcess) {
	super("It was not possible to find a Process with id " + idProcess);
    }

    
}
