package com.vortexbird.facturacion.exception;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

//	Error por parte del usuario / campos
public class UserException extends VortexbirdException {

	private static final long serialVersionUID = 1L;

	public UserException(String exception) {
		super(400, exception, null);
	}

	public UserException(String exception, Exception e) {
		super(400, exception, e);
	}
}