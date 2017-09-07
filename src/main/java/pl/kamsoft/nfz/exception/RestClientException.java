package pl.kamsoft.nfz.exception;

public class RestClientException extends Exception{
	private static final long serialVersionUID = 1L;

	public RestClientException(String message){
		super(message);
	}

	public RestClientException(String message, Throwable cause) {
		super(message, cause);
	}
}
