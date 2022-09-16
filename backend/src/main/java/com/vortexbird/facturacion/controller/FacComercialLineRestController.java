package com.vortexbird.facturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.facturacion.dto.ComercialLineDTO;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.repository.ComercialLineRepository;
import com.vortexbird.facturacion.service.FacComercialLineService;
import com.vortexbird.facturacion.utility.Constantes;

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
public class FacComercialLineRestController {
	
	@Autowired
	FacComercialLineService facComercialLineService;
	
	@Autowired
	ComercialLineRepository comercialLineRepository;
	
	@GetMapping("/findComercialLineA")
	public ResponseEntity<?> findComercialLineByStatus() throws VortexbirdException{
		log.debug("Request to findComercialLineByStatusA: {}");
		List<ComercialLineDTO> comercialLineDTOs = facComercialLineService.findComercialLineaByStatus(Constantes.STATUS_ACTIVE);
		
		return ResponseEntity.ok().body(comercialLineDTOs);
	}
	
	@PostMapping("/createComercialLine")
	public ResponseEntity<?> createComercialLine(@RequestBody ComercialLineDTO comercialLineDTO) throws VortexbirdException{
		log.debug("Request to createComercialLine: {}", comercialLineDTO);
		facComercialLineService.createComercialLine(comercialLineDTO);
		
		return ResponseEntity.ok().body(comercialLineDTO);
	}	
	
}
