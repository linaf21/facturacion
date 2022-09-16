package com.vortexbird.facturacion.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vortexbird.facturacion.domain.BillingStatus;
import com.vortexbird.facturacion.dto.BillingStatusDTO;

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
public interface BillingStatusMapper {

	public BillingStatusDTO billingStatusToBillingStatusDTO(BillingStatus billingStatus);

	public BillingStatus billingStatusDTOToBillingStatus(BillingStatusDTO billingStatusDTO);

	public List<BillingStatusDTO> listBillingStatusToListBillingStatusDTO(List<BillingStatus> billingStatuss);

	public List<BillingStatus> listBillingStatusDTOToListBillingStatus(List<BillingStatusDTO> billingStatusDTOs);
}
