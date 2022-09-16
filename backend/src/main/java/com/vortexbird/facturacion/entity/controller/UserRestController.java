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

import com.vortexbird.facturacion.domain.User;
import com.vortexbird.facturacion.dto.UserDTO;
import com.vortexbird.facturacion.entity.service.UserService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@GetMapping(value = "/{userId}")
	public ResponseEntity<?> findById(@PathVariable("userId") Integer userId) throws VortexbirdException {
		log.debug("Request to findById() User");

		Optional<User> optionalUser = userService.findById(userId);

		User user = optionalUser.isPresent() ? optionalUser.get() : null;

		return ResponseEntity.ok().body(userMapper.userToUserDTO(user));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws VortexbirdException {
		log.debug("Request to findAll() User");

		return ResponseEntity.ok().body(userMapper.listUserToListUserDTO(userService.findAll()));

	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO) throws VortexbirdException {
		log.debug("Request to save User: {}", userDTO);

		User user = userMapper.userDTOToUser(userDTO);
		user = userService.save(user);
		return ResponseEntity.ok().body(userMapper.userToUserDTO(user));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody UserDTO userDTO) throws VortexbirdException {
		log.debug("Request to update User: {}", userDTO);

		User user = userMapper.userDTOToUser(userDTO);
		user = userService.update(user);

		return ResponseEntity.ok().body(userMapper.userToUserDTO(user));

	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<?> delete(@PathVariable("userId") Integer userId) throws VortexbirdException {
		log.debug("Request to delete User");

		userService.deleteById(userId);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(userService.count());
	}

}
