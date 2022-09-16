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
import com.vortexbird.facturacion.domain.ComercialLine;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.repository.ComercialLineRepository;
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
public class ComercialLineServiceImpl implements ComercialLineService {

	@Autowired
	private ComercialLineRepository comercialLineRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(ComercialLine comercialLine) throws ConstraintViolationException {

		Set<ConstraintViolation<ComercialLine>> constraintViolations = validator.validate(comercialLine);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return comercialLineRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ComercialLine> findAll() {
		log.debug("finding all ComercialLine instances");
		return comercialLineRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ComercialLine save(ComercialLine entity) throws VortexbirdException {
		log.debug("saving ComercialLine instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("ComercialLine");
		}

		validate(entity);

		return comercialLineRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(ComercialLine entity) throws VortexbirdException {
		log.debug("deleting ComercialLine instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("ComercialLine");
		}

		if (entity.getColiId() == null) {
			throw new ZMessManager().new EmptyFieldException("coliId");
		}

		if (!comercialLineRepository.existsById(entity.getColiId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getColiId()).ifPresent(entidad -> {
			List<Billing> billings = entidad.getBillings();
			if (Utilities.validationsList(billings)) {
				throw new ZMessManager().new DeletingException("billings");
			}
		});

		comercialLineRepository.deleteById(entity.getColiId());
		log.debug("delete ComercialLine successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws VortexbirdException {
		log.debug("deleting ComercialLine instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("coliId");
		}
		Optional<ComercialLine> optionalComercialLine = comercialLineRepository.findById(id);
		if (optionalComercialLine.isPresent()) {
			delete(optionalComercialLine.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ComercialLine update(ComercialLine entity) throws VortexbirdException {

		log.debug("updating ComercialLine instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("ComercialLine");
		}

		validate(entity);

		if (!comercialLineRepository.existsById(entity.getColiId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return comercialLineRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ComercialLine> findById(Integer coliId) {
		log.debug("getting ComercialLine instance");
		return comercialLineRepository.findById(coliId);
	}

}
