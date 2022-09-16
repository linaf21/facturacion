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

import com.vortexbird.facturacion.domain.ComercialLine;
import com.vortexbird.facturacion.dto.ComercialLineDTO;
import com.vortexbird.facturacion.entity.service.ComercialLineService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.ComercialLineMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/comercialLine")
@Slf4j
public class ComercialLineRestController {

	@Autowired
	private ComercialLineService comercialLineService;

	@Autowired
	private ComercialLineMapper comercialLineMapper;

	@GetMapping(value = "/{coliId}")
	public ResponseEntity<?> findById(@PathVariable("coliId") Integer coliId) throws VortexbirdException {
		log.debug("Request to findById() ComercialLine");

		Optional<ComercialLine> optionalComercialLine = comercialLineService.findById(coliId);

		ComercialLine comercialLine = optionalComercialLine.isPresent() ? optionalComercialLine.get() : null;

		return ResponseEntity.ok().body(comercialLineMapper.comercialLineToComercialLineDTO(comercialLine));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws VortexbirdException {
		log.debug("Request to findAll() ComercialLine");

		return ResponseEntity.ok()
				.body(comercialLineMapper.listComercialLineToListComercialLineDTO(comercialLineService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ComercialLineDTO comercialLineDTO) throws VortexbirdException {
		log.debug("Request to save ComercialLine: {}", comercialLineDTO);

		ComercialLine comercialLine = comercialLineMapper.comercialLineDTOToComercialLine(comercialLineDTO);
		comercialLine = comercialLineService.save(comercialLine);
		return ResponseEntity.ok().body(comercialLineMapper.comercialLineToComercialLineDTO(comercialLine));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ComercialLineDTO comercialLineDTO) throws VortexbirdException {
		log.debug("Request to update ComercialLine: {}", comercialLineDTO);

		ComercialLine comercialLine = comercialLineMapper.comercialLineDTOToComercialLine(comercialLineDTO);
		comercialLine = comercialLineService.update(comercialLine);

		return ResponseEntity.ok().body(comercialLineMapper.comercialLineToComercialLineDTO(comercialLine));

	}

	@DeleteMapping(value = "/{coliId}")
	public ResponseEntity<?> delete(@PathVariable("coliId") Integer coliId) throws VortexbirdException {
		log.debug("Request to delete ComercialLine");

		comercialLineService.deleteById(coliId);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(comercialLineService.count());
	}

}
