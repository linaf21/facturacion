package com.vortexbird.facturacion.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "billing") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billing implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bill_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bist_id")
	@NotNull
	private BillingStatus billingStatus;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coli_id")
	@NotNull
	private ComercialLine comercialLine;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comp_id")
	@NotNull
	private Company company;

	@NotNull
	@Column(name = "base_value", nullable = false)
	private Integer baseValue;
	@NotNull
	@Column(name = "concept", nullable = false)
	private Integer concept;
	@NotNull
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "creation_user", nullable = false)
	private String creationUser;
	@NotNull
	@Column(name = "iva", nullable = false)
	private Integer iva;
	@Column(name = "modification_date")
	private Date modificationDate;
	@Column(name = "modification_user")
	private String modificationUser;
	@NotNull
	@Column(name = "radication_date", nullable = false)
	private Date radicationDate;
	@Column(name = "retention")
	private Integer retention;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "status", nullable = false)
	private String status;

}