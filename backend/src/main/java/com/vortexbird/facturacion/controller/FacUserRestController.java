package com.vortexbird.facturacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.dto.UserDTO;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.repository.CompanyRepository;
import com.vortexbird.facturacion.repository.UserRepository;
import com.vortexbird.facturacion.service.FacCompanyService;
import com.vortexbird.facturacion.service.FacUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class FacUserRestController {
	@Autowired
	FacUserService facUserService;

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws Exception{
		log.debug("Request to findByUser: {}", userDTO);
		
		return ResponseEntity.ok().body(facUserService.login(userDTO));
	}
}