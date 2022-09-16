package com.vortexbird.facturacion.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vortexbird.facturacion.domain.User;
import com.vortexbird.facturacion.dto.UserDTO;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 *
 * 
 */

// consultar por correo y estado, optional de user y que reciba el usuario
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUserAndStatus(String user, String statusActive);
	
	

}