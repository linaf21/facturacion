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
public class CompanyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 255)
	private String bank;
	private Integer compId;
	private Date creationDate;
	@Size(max = 255)
	private String creationUser;
	private Date modificationDate;
	private String modificationUser;
	@Size(max = 255)
	private String name;
	private Integer nit;
	private Integer paymentDeadline;
	@Size(max = 1)
	private String status;
	private Integer pageNumber;
	private Integer pageSize;
	
	public CompanyDTO(Integer compId, Integer nit, String name, String bank, Integer paymentDeadline) {
		super();
		this.compId = compId;
		this.nit = nit;
		this.name = name;
		this.bank = bank;
		this.paymentDeadline = paymentDeadline;
	}
	public CompanyDTO(Integer compId, Integer nit, String name, String bank, Integer paymentDeadline, Date creationDate, String creationUser, String status) {
		super();
		this.compId = compId;
		this.nit = nit;
		this.name = name;
		this.bank = bank;
		this.paymentDeadline = paymentDeadline;
		this.creationDate = creationDate;
		this.creationUser = creationUser;
		this.status = status;
	}
	
	
}
