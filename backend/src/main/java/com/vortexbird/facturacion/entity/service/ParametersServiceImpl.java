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

import com.vortexbird.facturacion.domain.Parameters;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.repository.ParametersRepository;

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
public class ParametersServiceImpl implements ParametersService {

	@Autowired
	private ParametersRepository parametersRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Parameters parameters) throws ConstraintViolationException {

		Set<ConstraintViolation<Parameters>> constraintViolations = validator.validate(parameters);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return parametersRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Parameters> findAll() {
		log.debug("finding all Parameters instances");
		return parametersRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Parameters save(Parameters entity) throws VortexbirdException {
		log.debug("saving Parameters instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Parameters");
		}

		validate(entity);

		return parametersRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Parameters entity) throws VortexbirdException {
		log.debug("deleting Parameters instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Parameters");
		}

		if (entity.getParaId() == null) {
			throw new ZMessManager().new EmptyFieldException("paraId");
		}

		if (!parametersRepository.existsById(entity.getParaId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		parametersRepository.deleteById(entity.getParaId());
		log.debug("delete Parameters successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws VortexbirdException {
		log.debug("deleting Parameters instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("paraId");
		}
		Optional<Parameters> optionalParameters = parametersRepository.findById(id);
		if (optionalParameters.isPresent()) {
			delete(optionalParameters.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Parameters update(Parameters entity) throws VortexbirdException {

		log.debug("updating Parameters instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Parameters");
		}

		validate(entity);

		if (!parametersRepository.existsById(entity.getParaId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return parametersRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Parameters> findById(Integer paraId) {
		log.debug("getting Parameters instance");
		return parametersRepository.findById(paraId);
	}

}
