package com.vortexbird.facturacion.service;

import java.util.List;

import com.vortexbird.facturacion.dto.ComercialLineDTO;
import com.vortexbird.facturacion.exception.VortexbirdException;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */
public interface FacComercialLineService {

	List<ComercialLineDTO> findComercialLineFilter(ComercialLineDTO comercialLineDTO) throws VortexbirdException;

    /**
     * Returns {@code true} if, and only if, {@link #length()} is {@code 0}.
     *
     * @return {@code true} if {@link #length()} is {@code 0}, otherwise
     * {@code false}
     *
     * @since 1.6
     */
	List<ComercialLineDTO> findComercialLineaByStatus(String status) throws VortexbirdException;

	void createComercialLine(ComercialLineDTO comercialLineDTO) throws VortexbirdException;

}