package com.vortexbird.facturacion.exception;

public class ConfigException extends VortexbirdException {

	/**
	 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
	 *         www.zathuracode.org
	 * @generationDate 2022-08-23T11:10:20.573413
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ConfigException(String exception) {
		super(412, exception, null);
	}

	public ConfigException(String exception, Exception e) {
		super(412, exception, e);
	}
}