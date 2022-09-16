package com.vortexbird.facturacion.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
 * 
 */
@Entity
@Table(name = "billing_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingStatus implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bist_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bistId;

	@NotNull
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "creation_user", nullable = false)
	private String creationUser;
	@Column(name = "modification_date")
	private Date modificationDate;
	@Column(name = "modification_user")
	private String modificationUser;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "name", nullable = false)
	private String name;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "status", nullable = false)
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billingStatus")
	private List<Billing> billings = new ArrayList<>();

}