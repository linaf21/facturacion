package com.vortexbird.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vortexbird.facturacion.domain.ComercialLine;
import com.vortexbird.facturacion.dto.ComercialLineDTO;
import com.vortexbird.facturacion.dto.CompanyDTO;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 *
 * 
 */
public interface ComercialLineRepository extends JpaRepository<ComercialLine, Integer> {

	@Query(nativeQuery = true) 
	List<ComercialLineDTO> findComercialLineFilter(@Param("pColiId")Integer coliId, @Param("pName")String name);	//return DTO? o domain
	
	@Query(nativeQuery = true) 
	List<ComercialLineDTO> findComercialLineByStatus(@Param("pStatus")String status);
}