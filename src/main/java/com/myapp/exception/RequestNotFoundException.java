package com.myapp.exception;

public class RequestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5305222764395271878L;

	public RequestNotFoundException() {
		super();
	}

	public RequestNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public RequestNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RequestNotFoundException(String arg0) {
		super(arg0);
	}

	public RequestNotFoundException(Throwable arg0) {
		super(arg0);
	}

}
