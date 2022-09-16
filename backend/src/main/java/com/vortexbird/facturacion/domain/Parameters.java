package com.vortexbird.facturacion.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "parameters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameters implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "para_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paraId;

	@NotNull
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "creation_user", nullable = false)
	private String creationUser;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "key", nullable = false)
	private String key;
	@Column(name = "modification_date")
	private Date modificationDate;
	@Column(name = "modification_user")
	private String modificationUser;
	@NotNull
	@NotEmpty
	@Size(max = 1)
	@Column(name = "status", nullable = false)
	private String status;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "value", nullable = false)
	private String value;

}