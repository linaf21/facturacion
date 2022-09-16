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
public class BillingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Integer baseValue;
	@NotNull
	private Integer billId;
	@NotNull
	private Integer concept;
	@NotNull
	private Date creationDate;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String creationUser;
	@NotNull
	private Integer iva;
	private Date modificationDate;
	private String modificationUser;
	@NotNull
	private Date radicationDate;
	private Integer retention;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	private String status;
	private Integer bistIdBillingStatus;
	private Integer coliIdComercialLine;
	private Integer compIdCompany;
}
