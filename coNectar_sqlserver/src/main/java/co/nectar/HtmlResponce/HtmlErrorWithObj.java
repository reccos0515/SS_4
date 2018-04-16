package co.nectar.HtmlResponce;

/**
 * Class to converted to a json to return an error
 * success: boolean success value
 * message: string denoting error
 * 
 * @author Ben
 *
 */
public class HtmlErrorWithObj implements HtmlResponce{
	boolean success;
	String message;
	Object object;
	
	public HtmlErrorWithObj() {
		this.success = false;
		this.message = "";
	}
	
	public HtmlErrorWithObj(boolean success, String message, Object object) {
		super();
		this.success = success;
		this.message = message;
		this.object = object;
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
