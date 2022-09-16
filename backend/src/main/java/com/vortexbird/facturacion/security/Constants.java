package com.vortexbird.facturacion.security;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 *
 */
public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	// Spring Security

	public static final String LOGIN_URL = "/api/v1/user/login"; 
	public static final String ACTUATOR_URL = "/actuator/**";

	// Path http://localhost:9090/swagger-ui/index.html
	public static final String API_DOCS = "/v3/api-docs/**";
	public static final String SWAGGER_UI = "/swagger-ui/**/";

	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT

	public static final String ISSUER_INFO = "https://zathuracode.org";
	public static final String SUPER_SECRET_KEY = "z4tur4c0dv102022isth3b3stcodeg3n3ratorintheworldofj4v4v0rtex2022";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

}
