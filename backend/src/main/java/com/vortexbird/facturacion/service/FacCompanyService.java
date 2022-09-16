package com.vortexbird.facturacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.exception.VortexbirdException;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */
public interface FacCompanyService{

	void createCompany(CompanyDTO companyDTO) throws VortexbirdException;

	Page<CompanyDTO> findByFilters(CompanyDTO companyDTO) throws VortexbirdException;

	List<CompanyDTO> findCompanyFilter(CompanyDTO companyDTO) throws VortexbirdException;

	List<CompanyDTO> findCompanyByStatusActive() throws VortexbirdException;

	CompanyDTO updateCompany(CompanyDTO companyDTO) throws VortexbirdException;


}