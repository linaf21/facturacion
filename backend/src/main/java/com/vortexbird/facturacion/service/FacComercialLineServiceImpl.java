package com.vortexbird.facturacion.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.facturacion.domain.ComercialLine;
import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.ComercialLineDTO;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.entity.service.ComercialLineService;
import com.vortexbird.facturacion.entity.service.CompanyService;
import com.vortexbird.facturacion.exception.SystemException;
import com.vortexbird.facturacion.exception.UserException;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.ComercialLineMapper;
import com.vortexbird.facturacion.mapper.CompanyMapper;
import com.vortexbird.facturacion.repository.ComercialLineRepository;
import com.vortexbird.facturacion.repository.CompanyRepository;
import com.vortexbird.facturacion.utility.Constantes;

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
public class FacComercialLineServiceImpl implements FacComercialLineService {
	@Autowired
	ComercialLineService comercialLineService;

	@Autowired
	ComercialLineRepository comercialLineRepository;
	
	@Autowired
	ComercialLineMapper comercialLineMapper;

	
	public void validateComercialLine(ComercialLineDTO comercialLineDTO) throws VortexbirdException {

		if(comercialLineDTO == null) {
			throw new UserException("La linea comercial esta nula o vacía.");
		}
		if (comercialLineDTO.getName() == null) {
			throw new UserException("El nombre de la linea comercial esta nulo o vacío.");
		}
		if (comercialLineDTO.getName().trim().isEmpty()) {
			throw new UserException("El nombre de la linea comercial esta nulo o vacío.");
		}
	}
	
	public void validateComercialLineAuditory(ComercialLine comercialLine) throws VortexbirdException {

		if(comercialLine == null) {
			throw new UserException("La linea comercial esta nula o vacía.");
		}
		if (comercialLine.getCreationDate() == null) {
			throw new UserException("La fecha de la linea comercial esta nula o vacía.");
		}
		if (comercialLine.getCreationUser() == null) {
			throw new UserException("El Usuario creador de la linea comercial esta nulo o vacío.");
		}
		if (comercialLine.getCreationUser().trim().isEmpty()) {
			throw new UserException("El Usuario creador de la linea comercial esta nulo o vacío.");
		}
	}

    /**
     * Returns {@code true} if, and only if, {@link #length()} is {@code 0}.
     *
     * @return {@code true} if {@link #length()} is {@code 0}, otherwise
     * {@code false}
     *
     * @since 1.6
     */
	@Override
	@Transactional(readOnly = true)
	public List<ComercialLineDTO> findComercialLineFilter(ComercialLineDTO comercialLineDTO) throws VortexbirdException {
		if(comercialLineDTO == null) {
			throw new UserException("La linea comercial esta nula o vacía.");
		}
//		Filter coliId ComercialLine
		Integer coliId = comercialLineDTO.getColiId() == null ? -1 : comercialLineDTO.getColiId();
//		Filter name ComercialLine
		String name = comercialLineDTO.getName() == null || comercialLineDTO.getName().trim().isEmpty() ? "-1" : comercialLineDTO.getName().trim();

//		Validates that is a correct coliId, an a existing ComercialLine
		List<ComercialLineDTO> comercialLineDTOs = comercialLineRepository.findComercialLineFilter(coliId, name);
		if (comercialLineDTOs.isEmpty()) {
			throw new SystemException("No se encuentra la linea comercial relacionada a los filtros.");
		}
		return comercialLineDTOs;
	}
	
    /**
     * 
     * Returns {@code true} if, and only if, {@link #length()} is {@code 0}.
     *
     * @return {@code true} if {@link #length()} is {@code 0}, otherwise
     * {@code false}
     *
     * @since 1.6
     */
	@Override
	@Transactional(readOnly = true)
	public List<ComercialLineDTO> findComercialLineaByStatus(String status) throws VortexbirdException {
//		
		if(status == null) {
			throw new UserException("El estado de la linea comercial esta nulo o vacío.");
		}
		if(status.trim().isEmpty()) {
			throw new UserException("El estado de la linea comercial esta nulo o vacío.");
		}
		if(status.trim().length() != 1) {
			throw new UserException("El estado de la linea comercial no debe ser mayor o menor a 1 caracter.");
		}
		List<ComercialLineDTO> comercialLineDTOs = comercialLineRepository.findComercialLineByStatus(status);
		if (comercialLineDTOs.isEmpty()) {
			throw new SystemException("No se encuentran lineas comerciales.");
		}
		return comercialLineDTOs;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createComercialLine(ComercialLineDTO comercialLineDTO) throws VortexbirdException {
		ComercialLine comercialLine = new ComercialLine();
//		Method that validates a commercial Line
		validateComercialLine(comercialLineDTO);

//		Setting common Data
		comercialLine.setStatus(Constantes.STATUS_ACTIVE);
		comercialLine.setCreationDate(new Date());
		comercialLine.setCreationUser(comercialLineDTO.getCreationUser());

		validateComercialLineAuditory(comercialLine);
//		Setting Data
		comercialLine.setName(comercialLineDTO.getName());

//		Saving 
		comercialLineService.save(comercialLine);

	}

}
