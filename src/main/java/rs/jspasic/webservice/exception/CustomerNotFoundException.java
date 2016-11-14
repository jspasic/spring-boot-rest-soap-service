package rs.jspasic.webservice.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5936103999768099098L;


	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}
