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

import com.vortexbird.facturacion.domain.Parameters;
import com.vortexbird.facturacion.dto.ParametersDTO;
import com.vortexbird.facturacion.entity.service.ParametersService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.ParametersMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/parameters")
@Slf4j
public class ParametersRestController {

	@Autowired
	private ParametersService parametersService;

	@Autowired
	private ParametersMapper parametersMapper;

	@GetMapping(value = "/{paraId}")
	public ResponseEntity<?> findById(@PathVariable("paraId") Integer paraId) throws VortexbirdException {
		log.debug("Request to findById() Parameters");

		Optional<Parameters> optionalParameters = parametersService.findById(paraId);

		Parameters parameters = optionalParameters.isPresent() ? optionalParameters.get() : null;

		return ResponseEntity.ok().body(parametersMapper.parametersToParametersDTO(parameters));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws VortexbirdException {
		log.debug("Request to findAll() Parameters");

		return ResponseEntity.ok()
				.body(parametersMapper.listParametersToListParametersDTO(parametersService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ParametersDTO parametersDTO) throws VortexbirdException {
		log.debug("Request to save Parameters: {}", parametersDTO);

		Parameters parameters = parametersMapper.parametersDTOToParameters(parametersDTO);
		parameters = parametersService.save(parameters);
		return ResponseEntity.ok().body(parametersMapper.parametersToParametersDTO(parameters));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ParametersDTO parametersDTO) throws VortexbirdException {
		log.debug("Request to update Parameters: {}", parametersDTO);

		Parameters parameters = parametersMapper.parametersDTOToParameters(parametersDTO);
		parameters = parametersService.update(parameters);

		return ResponseEntity.ok().body(parametersMapper.parametersToParametersDTO(parameters));

	}

	@DeleteMapping(value = "/{paraId}")
	public ResponseEntity<?> delete(@PathVariable("paraId") Integer paraId) throws VortexbirdException {
		log.debug("Request to delete Parameters");

		parametersService.deleteById(paraId);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(parametersService.count());
	}

}
