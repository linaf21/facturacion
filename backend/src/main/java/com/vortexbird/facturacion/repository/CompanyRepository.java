package com.vortexbird.facturacion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.CompanyDTO;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 *
 * 
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	

//	List<CompanyDTO> findAllCompanys();
//	
//	@Query(nativeQuery = true) 
//	List<CompanyDTO> findByNombreAndNit(String name, Integer nit);
//	Native => DTO
//	Repository => Domain
	@Query(nativeQuery = true) 
	Page<CompanyDTO> findByFilters(@Param("pCompId")Integer compId, @Param("pNit")Integer nit, @Param("pName")String name, Pageable pageable);	//return DTO? o domain
	
	@Query(nativeQuery = true) 
	List<CompanyDTO> findCompanyFilter(@Param("pCompId")Integer compId, @Param("pNit")Integer nit, @Param("pName")String name);	//return DTO? o domain
	
	@Query(nativeQuery = true) 
	List<CompanyDTO> findCompanyByStatus(@Param("pStatus")String status);
	
}