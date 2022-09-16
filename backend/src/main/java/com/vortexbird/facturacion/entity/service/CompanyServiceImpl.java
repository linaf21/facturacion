package com.vortexbird.facturacion.entity.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.facturacion.domain.Billing;
import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.repository.CompanyRepository;
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
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Company company) throws ConstraintViolationException {

		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return companyRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Company> findAll() {
		log.debug("finding all Company instances");
		return companyRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Company save(Company entity) throws VortexbirdException {
		log.debug("saving Company instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Company");
		}

		validate(entity);

		return companyRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Company entity) throws VortexbirdException {
		log.debug("deleting Company instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Company");
		}

		if (entity.getCompId() == null) {
			throw new ZMessManager().new EmptyFieldException("compId");
		}

		if (!companyRepository.existsById(entity.getCompId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getCompId()).ifPresent(entidad -> {
			List<Billing> billings = entidad.getBillings();
			if (Utilities.validationsList(billings)) {
				throw new ZMessManager().new DeletingException("billings");
			}
		});

		companyRepository.deleteById(entity.getCompId());
		log.debug("delete Company successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws VortexbirdException {
		log.debug("deleting Company instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("compId");
		}
		Optional<Company> optionalCompany = companyRepository.findById(id);
		if (optionalCompany.isPresent()) {
			delete(optionalCompany.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Company update(Company entity) throws VortexbirdException {

		log.debug("updating Company instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Company");
		}

		validate(entity);

		if (!companyRepository.existsById(entity.getCompId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return companyRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Company> findById(Integer compId) {
		log.debug("getting Company instance");
		return companyRepository.findById(compId);
	}

}
