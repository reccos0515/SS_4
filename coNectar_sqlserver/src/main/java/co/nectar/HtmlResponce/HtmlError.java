package co.nectar.HtmlResponce;

/**
 * Class to converted to a json to return an error
 * success: boolean success value
 * message: string denoting error
 * 
 * @author Ben
 *
 */
public class HtmlError implements HtmlResponce{
	boolean success;
	String message;
	
	public HtmlError() {
		this.success = false;
		this.message = "";
	}
	
	public HtmlError(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
