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

import com.vortexbird.facturacion.domain.User;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.repository.UserRepository;

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
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(User user) throws ConstraintViolationException {

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return userRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		log.debug("finding all User instances");
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User save(User entity) throws VortexbirdException {
		log.debug("saving User instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("User");
		}

		validate(entity);

		return userRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(User entity) throws VortexbirdException {
		log.debug("deleting User instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("User");
		}

		if (entity.getUserId() == null) {
			throw new ZMessManager().new EmptyFieldException("userId");
		}

		if (!userRepository.existsById(entity.getUserId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		userRepository.deleteById(entity.getUserId());
		log.debug("delete User successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws VortexbirdException {
		log.debug("deleting User instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("userId");
		}
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			delete(optionalUser.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User update(User entity) throws VortexbirdException {

		log.debug("updating User instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("User");
		}

		validate(entity);

		if (!userRepository.existsById(entity.getUserId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return userRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Integer userId) {
		log.debug("getting User instance");
		return userRepository.findById(userId);
	}

}
