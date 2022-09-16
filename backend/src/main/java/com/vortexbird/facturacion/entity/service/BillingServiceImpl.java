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
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.repository.BillingRepository;

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
public class BillingServiceImpl implements BillingService {

	@Autowired
	private BillingRepository billingRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Billing billing) throws ConstraintViolationException {

		Set<ConstraintViolation<Billing>> constraintViolations = validator.validate(billing);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return billingRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Billing> findAll() {
		log.debug("finding all Billing instances");
		return billingRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Billing save(Billing entity) throws VortexbirdException {
		log.debug("saving Billing instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Billing");
		}

		validate(entity);

		return billingRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Billing entity) throws VortexbirdException {
		log.debug("deleting Billing instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Billing");
		}

		if (entity.getBillId() == null) {
			throw new ZMessManager().new EmptyFieldException("billId");
		}

		if (!billingRepository.existsById(entity.getBillId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		billingRepository.deleteById(entity.getBillId());
		log.debug("delete Billing successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws VortexbirdException {
		log.debug("deleting Billing instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("billId");
		}
		Optional<Billing> optionalBilling = billingRepository.findById(id);
		if (optionalBilling.isPresent()) {
			delete(optionalBilling.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Billing update(Billing entity) throws VortexbirdException {

		log.debug("updating Billing instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Billing");
		}

		validate(entity);

		if (!billingRepository.existsById(entity.getBillId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return billingRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Billing> findById(Integer billId) {
		log.debug("getting Billing instance");
		return billingRepository.findById(billId);
	}

}
