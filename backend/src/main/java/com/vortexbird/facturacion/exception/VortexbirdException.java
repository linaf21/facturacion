package com.vortexbird.facturacion.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@Getter
@Setter
public class VortexbirdException extends Exception {
	private static final long serialVersionUID = 1L;
	private final Integer code;
	private final String exception;

	public VortexbirdException(Integer code, String exception, Exception e) {
		super(e);
		this.code = code;
		this.exception = exception;
	}

	public VortexbirdException(String exception, Exception e) {
		super(e);
		this.code = null;
		this.exception = exception;
	}
}
