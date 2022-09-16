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
public class ComercialLineDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer coliId;
	private Date creationDate;
	@Size(max = 255)
	private String creationUser;
	private Date modificationDate;
	private String modificationUser;
	@Size(max = 30)
	private String name;
	@Size(max = 1)
	private String status;
	private Integer pageNumber;
	private Integer pageSize;
	
	public ComercialLineDTO(Integer coliId, String name) {
		super();
		this.coliId = coliId;
		this.name = name;
	}
	public ComercialLineDTO(Integer coliId, String name, Date creationDate, String creationUser, String status) {
		super();
		this.coliId = coliId;
		this.name = name;
		this.creationDate = creationDate;
		this.creationUser = creationUser;
		this.status = status;
	}
}
