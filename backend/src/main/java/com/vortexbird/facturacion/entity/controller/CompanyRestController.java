package com.vortexbird.facturacion.entity.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.entity.service.CompanyService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.CompanyMapper;

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
public class CompanyRestController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyMapper companyMapper;

	@GetMapping(value = "/{compId}")
	public ResponseEntity<?> findById(@PathVariable("compId") Integer compId) throws VortexbirdException {
		log.debug("Request to findById() Company");

		Optional<Company> optionalCompany = companyService.findById(compId);

		Company company = optionalCompany.isPresent() ? optionalCompany.get() : null;

		return ResponseEntity.ok().body(companyMapper.companyToCompanyDTO(company));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws VortexbirdException {
		log.debug("Request to findAll() Company");

		return ResponseEntity.ok().body(companyMapper.listCompanyToListCompanyDTO(companyService.findAll()));

	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody CompanyDTO companyDTO) throws VortexbirdException {
		log.debug("Request to save Company: {}", companyDTO);

		Company company = companyMapper.companyDTOToCompany(companyDTO);
		company = companyService.save(company);
		return ResponseEntity.ok().body(companyMapper.companyToCompanyDTO(company));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody CompanyDTO companyDTO) throws VortexbirdException {
		log.debug("Request to update Company: {}", companyDTO);

		Company company = companyMapper.companyDTOToCompany(companyDTO);
		company = companyService.update(company);

		return ResponseEntity.ok().body(companyMapper.companyToCompanyDTO(company));

	}

	@DeleteMapping(value = "/{compId}")
	public ResponseEntity<?> delete(@PathVariable("compId") Integer compId) throws VortexbirdException {
		log.debug("Request to delete Company");

		companyService.deleteById(compId);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(companyService.count());
	}

}
