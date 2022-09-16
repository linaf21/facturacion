package com.vortexbird.facturacion.exception;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

//	Errores que hacen parte del sistema
public class SystemException extends VortexbirdException {

	private static final long serialVersionUID = 1L;

	public SystemException(String exception) {
		super(500, exception, null);
	}

	public SystemException(String exception, Exception e) {
		super(500, exception, e);
	}
}
