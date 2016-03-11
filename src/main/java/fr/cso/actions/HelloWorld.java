package fr.cso.actions;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorld extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		setMessage(getText(MESSAGE));
		return SUCCESS;
	}

	/**
	 * Provide default value for Message property.
	 */
	public static final String MESSAGE = "HelloWorld.message";

	/**
	 * Field for Message property.
	 */
	private String message;

	/**
	 * Return Message property.
	 *
	 * @return Message property
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set Message property.
	 *
	 * @param message
	 *            Text to display on HelloWorld page.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}