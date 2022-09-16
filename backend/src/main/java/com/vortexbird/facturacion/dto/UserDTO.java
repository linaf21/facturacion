package com.vortexbird.facturacion.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Size(max = 30)
	private String password;
	@NotNull
	@NotEmpty
	@Size(max = 40)
	private String user;
	@NotNull
	private Integer userId;
	
	@NotNull
	@NotEmpty
	private String status;
	
	private String token;
	
	@NotNull
	private Date creationDate;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String creationUser;
	
	private Date modificationDate;
	
	private String modificationUser;
}
