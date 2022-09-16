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
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NotNull
	@NotEmpty
	@Size(max = 30)
	@Column(name = "password", nullable = false)
	private String password;
	@NotNull
	@NotEmpty
	@Size(max = 40)
	@Column(name = "user", nullable = false)
	private String user;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@NotNull
	private Date creationDate;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String creationUser;
	
	private Date modificationDate;
	
	private String modificationUser;
}