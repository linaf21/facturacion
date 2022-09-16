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

import com.vortexbird.facturacion.domain.Billing;
import com.vortexbird.facturacion.dto.BillingDTO;
import com.vortexbird.facturacion.entity.service.BillingService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.BillingMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/billing")
@Slf4j
public class BillingRestController {

	@Autowired
	private BillingService billingService;

	@Autowired
	private BillingMapper billingMapper;

	@GetMapping(value = "/{billId}")
	public ResponseEntity<?> findById(@PathVariable("billId") Integer billId) throws VortexbirdException {
		log.debug("Request to findById() Billing");

		Optional<Billing> optionalBilling = billingService.findById(billId);

		Billing billing = optionalBilling.isPresent() ? optionalBilling.get() : null;

		return ResponseEntity.ok().body(billingMapper.billingToBillingDTO(billing));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws VortexbirdException {
		log.debug("Request to findAll() Billing");

		return ResponseEntity.ok().body(billingMapper.listBillingToListBillingDTO(billingService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody BillingDTO billingDTO) throws VortexbirdException {
		log.debug("Request to save Billing: {}", billingDTO);

		Billing billing = billingMapper.billingDTOToBilling(billingDTO);
		billing = billingService.save(billing);
		return ResponseEntity.ok().body(billingMapper.billingToBillingDTO(billing));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody BillingDTO billingDTO) throws VortexbirdException {
		log.debug("Request to update Billing: {}", billingDTO);

		Billing billing = billingMapper.billingDTOToBilling(billingDTO);
		billing = billingService.update(billing);

		return ResponseEntity.ok().body(billingMapper.billingToBillingDTO(billing));

	}

	@DeleteMapping(value = "/{billId}")
	public ResponseEntity<?> delete(@PathVariable("billId") Integer billId) throws VortexbirdException {
		log.debug("Request to delete Billing");

		billingService.deleteById(billId);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(billingService.count());
	}

}
