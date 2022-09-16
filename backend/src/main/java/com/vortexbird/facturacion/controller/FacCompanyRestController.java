package com.vortexbird.facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.repository.CompanyRepository;
import com.vortexbird.facturacion.service.FacCompanyService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/company")
@Slf4j
public class FacCompanyRestController {
	
	@Autowired
	FacCompanyService facCompanyService;

	@Autowired
	CompanyRepository companyRepository;

	@GetMapping("/findActiveCompany")
	public ResponseEntity<?> findCompanyByStatusA() throws VortexbirdException{
		log.debug("Request to findCompanyByStatusA: {}");
		List<CompanyDTO> companyDTOs = facCompanyService.findCompanyByStatusActive();
		
		return ResponseEntity.ok().body(companyDTOs);
	}
	
	@PostMapping("/findByFilters")
	public ResponseEntity<?> findByFilters(@RequestBody CompanyDTO companyDTO) throws VortexbirdException{
		log.debug("Request to findByFilters: {}", companyDTO);
		
		return ResponseEntity.ok().body(facCompanyService.findByFilters(companyDTO));
	}
	@PostMapping("/createCompany")
	public ResponseEntity<?> createCompany(@RequestBody CompanyDTO companyDTO) throws VortexbirdException{
		log.debug("Request to createCompany: {}", companyDTO);
		facCompanyService.createCompany(companyDTO);
		
		return ResponseEntity.ok().body(companyDTO);
	}	
	@PutMapping("/updateCompany")
	public ResponseEntity<?> modificateCompany(@RequestBody CompanyDTO companyDTO) throws VortexbirdException{
		log.debug("Request to modificateCompany: {}", companyDTO);
		
		return ResponseEntity.ok().body(facCompanyService.updateCompany(companyDTO));
	}	
}