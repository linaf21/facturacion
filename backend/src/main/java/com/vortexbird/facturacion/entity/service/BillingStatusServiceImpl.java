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
import com.vortexbird.facturacion.domain.BillingStatus;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.repository.BillingStatusRepository;
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
public class BillingStatusServiceImpl implements BillingStatusService {

	@Autowired
	private BillingStatusRepository billingStatusRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(BillingStatus billingStatus) throws ConstraintViolationException {

		Set<ConstraintViolation<BillingStatus>> constraintViolations = validator.validate(billingStatus);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return billingStatusRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BillingStatus> findAll() {
		log.debug("finding all BillingStatus instances");
		return billingStatusRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BillingStatus save(BillingStatus entity) throws VortexbirdException {
		log.debug("saving BillingStatus instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("BillingStatus");
		}

		validate(entity);

		return billingStatusRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(BillingStatus entity) throws VortexbirdException {
		log.debug("deleting BillingStatus instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("BillingStatus");
		}

		if (entity.getBistId() == null) {
			throw new ZMessManager().new EmptyFieldException("bistId");
		}

		if (!billingStatusRepository.existsById(entity.getBistId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getBistId()).ifPresent(entidad -> {
			List<Billing> billings = entidad.getBillings();
			if (Utilities.validationsList(billings)) {
				throw new ZMessManager().new DeletingException("billings");
			}
		});

		billingStatusRepository.deleteById(entity.getBistId());
		log.debug("delete BillingStatus successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws VortexbirdException {
		log.debug("deleting BillingStatus instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("bistId");
		}
		Optional<BillingStatus> optionalBillingStatus = billingStatusRepository.findById(id);
		if (optionalBillingStatus.isPresent()) {
			delete(optionalBillingStatus.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BillingStatus update(BillingStatus entity) throws VortexbirdException {

		log.debug("updating BillingStatus instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("BillingStatus");
		}

		validate(entity);

		if (!billingStatusRepository.existsById(entity.getBistId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return billingStatusRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<BillingStatus> findById(Integer bistId) {
		log.debug("getting BillingStatus instance");
		return billingStatusRepository.findById(bistId);
	}

}
