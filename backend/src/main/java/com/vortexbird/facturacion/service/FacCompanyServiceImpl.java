package com.vortexbird.facturacion.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.facturacion.domain.Billing;
import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.entity.service.CompanyService;
import com.vortexbird.facturacion.exception.SystemException;
import com.vortexbird.facturacion.exception.UserException;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.exception.ZMessManager.DeletingException;
import com.vortexbird.facturacion.exception.ZMessManager.EmptyFieldException;
import com.vortexbird.facturacion.exception.ZMessManager.NullEntityExcepcion;
import com.vortexbird.facturacion.mapper.CompanyMapper;
import com.vortexbird.facturacion.repository.CompanyRepository;
import com.vortexbird.facturacion.utility.Constantes;
import com.vortexbird.facturacion.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@Scope("singleton")
@Service
@Slf4j
public class FacCompanyServiceImpl implements FacCompanyService {

	@Autowired
	private CompanyService companyService;

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CompanyMapper companyMapper;

	public void validateCompany(CompanyDTO companyDTO) throws VortexbirdException {

		if(companyDTO == null) {
			throw new UserException("La empresa esta nula o vacía.");
		}
		if (companyDTO.getName() == null) {
			throw new UserException("El nombre de la empresa esta nulo o vacío.");
		}
		if (companyDTO.getName().trim().isEmpty()) {
			throw new UserException("El nombre de la empresa esta nulo o vacío.");
		}
		if (companyDTO.getBank() == null) {
			throw new UserException("El campo banco esta nulo o vacío.");
		}
		if (companyDTO.getBank().trim().isEmpty()) {
			throw new UserException("El campo banco esta nulo o vacío.");
		}
		if (companyDTO.getNit() == null) {
			throw new UserException("El campo nit esta nulo o vacío.");
		}
		if (companyDTO.getNit().toString().length() < 6) {
			throw new UserException("El nit no debe ser menor a 6 caracteres.");
		}
		if (companyDTO.getNit().toString().length() > 6) {
			throw new UserException("El nit no debe ser mayor a 6 caracteres.");
		}
		if (companyDTO.getPaymentDeadline() == null) {
			throw new UserException("El plazo pago de la empresa esta nulo o vacío.");
		}
		if (companyDTO.getPaymentDeadline().toString().trim() == null) {
			throw new UserException("El plazo pago de la empresa esta nulo o vacío.");
		}
		if (companyDTO.getPaymentDeadline() <= 0) {
			throw new UserException("El plazo para pago debe ser mayor o igual a 1 caracteres.");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyDTO> findCompanyFilter(CompanyDTO companyDTO) throws VortexbirdException {
		if(companyDTO == null) {
			throw new UserException("La empresa esta nula o vacía.");
		}
//		Filter compId Company
		Integer compId = companyDTO.getCompId() == null ? -1 : companyDTO.getCompId();
//		Filter Name Company using the Id
		String name = companyDTO.getName() == null || companyDTO.getName().trim().isEmpty() ? "-1" : companyDTO.getName().trim();
//		Filter NIT Company using the Id
		Integer nit = companyDTO.getNit() == null ? -1 : companyDTO.getNit();

//		Validates that is a correct CompId, an a existing company
		List<CompanyDTO> companys = companyRepository.findCompanyFilter(compId, nit, name);
		if (companys.isEmpty()) {
			throw new SystemException("No se encuentra la empresa relacionada a los filtros.");
		}
		return companys;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyDTO> findCompanyByStatusActive() throws VortexbirdException {
//		
		List<CompanyDTO> companyDtos = companyRepository.findCompanyByStatus(Constantes.STATUS_ACTIVE);
		if (companyDtos.isEmpty()) {
			throw new SystemException("No se encuentran empresas activas.");
		}
		return companyDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CompanyDTO> findByFilters(CompanyDTO companyDTO) throws VortexbirdException {

		if(companyDTO == null) {
			throw new UserException("La empresa esta nula o vacía.");
		}
//		Validates that the page number isn't null
		if (companyDTO.getPageNumber() == null) {
			throw new UserException("El numero de página esta nulo o vacío.");
		}
//		Validates that the page size isn't null
		if (companyDTO.getPageSize() == null) {
			throw new UserException("El tamaño de página esta nulo o vacío.");
		}

//		Filter compId Company
		Integer compId = companyDTO.getCompId() == null ? -1 : companyDTO.getCompId();

//		Filter Name Company using the Id
		String name = companyDTO.getName() == null || companyDTO.getName().trim().isEmpty() ? "-1"
				: companyDTO.getName().trim();
//		Filter NIT Company using the Id
		Integer nit = companyDTO.getNit() == null ? -1 : companyDTO.getNit();
//		Validates that is a correct CompId, an existing company
		List<CompanyDTO> companyDtos = companyRepository.findCompanyFilter(compId, nit, name);
		if (companyDtos.isEmpty()) {
			throw new SystemException("No se encuentra la empresa relacionada a los filtros.");
		}

//		Creates page for query
		Pageable pageable = PageRequest.of(companyDTO.getPageNumber().intValue(), companyDTO.getPageSize().intValue());

		return companyRepository.findByFilters(compId, nit, name, pageable);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createCompany(CompanyDTO companyDTO) throws VortexbirdException {
		Company company = new Company();
//		Method that validates a company
		validateCompany(companyDTO);
		
//		Method that validates if a company does exist using findById
		Integer compId = -1;
		String name = "-1";
		Integer nit = companyDTO.getNit();
		List<CompanyDTO> companyList = companyRepository.findCompanyFilter(compId, nit, name);
//	
		if (!companyList.isEmpty()) {
			throw new SystemException("Ya existe la empresa de nit: " + companyDTO.getNit());
		}

//		Setting common Data
		company.setStatus(Constantes.STATUS_ACTIVE);
		company.setCreationDate(new Date());
		company.setCreationUser(companyDTO.getCreationUser());

//		Setting Data
		company.setName(companyDTO.getName());
		company.setNit(companyDTO.getNit());
		company.setPaymentDeadline(companyDTO.getPaymentDeadline());
		company.setBank(companyDTO.getBank());

//		Saving 
		companyService.save(company);

	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CompanyDTO updateCompany(CompanyDTO companyDTO) throws VortexbirdException {
		if(companyDTO==null) {
			throw new UserException("La empresa esta nula o vacía.");
		}
//		Method that validates if a company does exist using findById
		Integer compId = companyDTO.getCompId();
		Optional<Company> companyOptional = companyRepository.findById(compId);
		if (!companyOptional.isPresent()) {
			throw new SystemException("No se conoce la empresa a modificar.");
		}
		
		Company company = companyOptional.get();
		validateCompany(companyDTO);

//		Setting common Data
		company.setStatus(Constantes.STATUS_ACTIVE);
		company.setModificationDate(new Date());
		company.setModificationUser(companyDTO.getModificationUser());
		company.setCreationDate(company.getCreationDate());
		company.setCreationUser(company.getCreationUser());
		company.setStatus(company.getStatus());

//		Setting Data
		company.setName(companyDTO.getName());
		company.setNit(companyDTO.getNit());
		company.setPaymentDeadline(companyDTO.getPaymentDeadline());
		company.setBank(companyDTO.getBank());

//		Saving 
		companyService.update(company);
		
		return companyMapper.companyToCompanyDTO(company); 
	}

}
