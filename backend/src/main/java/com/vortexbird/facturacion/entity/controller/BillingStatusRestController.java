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

import com.vortexbird.facturacion.domain.BillingStatus;
import com.vortexbird.facturacion.dto.BillingStatusDTO;
import com.vortexbird.facturacion.entity.service.BillingStatusService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.BillingStatusMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/billingStatus")
@Slf4j
public class BillingStatusRestController {

	@Autowired
	private BillingStatusService billingStatusService;

	@Autowired
	private BillingStatusMapper billingStatusMapper;

	@GetMapping(value = "/{bistId}")
	public ResponseEntity<?> findById(@PathVariable("bistId") Integer bistId) throws VortexbirdException {
		log.debug("Request to findById() BillingStatus");

		Optional<BillingStatus> optionalBillingStatus = billingStatusService.findById(bistId);

		BillingStatus billingStatus = optionalBillingStatus.isPresent() ? optionalBillingStatus.get() : null;

		return ResponseEntity.ok().body(billingStatusMapper.billingStatusToBillingStatusDTO(billingStatus));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws VortexbirdException {
		log.debug("Request to findAll() BillingStatus");

		return ResponseEntity.ok()
				.body(billingStatusMapper.listBillingStatusToListBillingStatusDTO(billingStatusService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody BillingStatusDTO billingStatusDTO) throws VortexbirdException {
		log.debug("Request to save BillingStatus: {}", billingStatusDTO);

		BillingStatus billingStatus = billingStatusMapper.billingStatusDTOToBillingStatus(billingStatusDTO);
		billingStatus = billingStatusService.save(billingStatus);
		return ResponseEntity.ok().body(billingStatusMapper.billingStatusToBillingStatusDTO(billingStatus));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody BillingStatusDTO billingStatusDTO) throws VortexbirdException {
		log.debug("Request to update BillingStatus: {}", billingStatusDTO);

		BillingStatus billingStatus = billingStatusMapper.billingStatusDTOToBillingStatus(billingStatusDTO);
		billingStatus = billingStatusService.update(billingStatus);

		return ResponseEntity.ok().body(billingStatusMapper.billingStatusToBillingStatusDTO(billingStatus));

	}

	@DeleteMapping(value = "/{bistId}")
	public ResponseEntity<?> delete(@PathVariable("bistId") Integer bistId) throws VortexbirdException {
		log.debug("Request to delete BillingStatus");

		billingStatusService.deleteById(bistId);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(billingStatusService.count());
	}

}
