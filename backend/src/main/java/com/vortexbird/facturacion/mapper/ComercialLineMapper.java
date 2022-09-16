package com.vortexbird.facturacion.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vortexbird.facturacion.domain.ComercialLine;
import com.vortexbird.facturacion.dto.ComercialLineDTO;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 *
 * 
 *                 Mapper Build with MapStruct https://mapstruct.org/ MapStruct
 *                 is a code generator that greatly simplifies the
 *                 implementation of mappings between Java bean type based on a
 *                 convention over configuration approach.
 */

@Mapper
public interface ComercialLineMapper {

	public ComercialLineDTO comercialLineToComercialLineDTO(ComercialLine comercialLine);

	public ComercialLine comercialLineDTOToComercialLine(ComercialLineDTO comercialLineDTO);

	public List<ComercialLineDTO> listComercialLineToListComercialLineDTO(List<ComercialLine> comercialLines);

	public List<ComercialLine> listComercialLineDTOToListComercialLine(List<ComercialLineDTO> comercialLineDTOs);
}
