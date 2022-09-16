package com.vortexbird.facturacion.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vortexbird.facturacion.domain.Billing;
import com.vortexbird.facturacion.dto.BillingDTO;

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
public interface BillingMapper {

	@Mapping(source = "billingStatus.bistId", target = "bistIdBillingStatus")
	@Mapping(source = "comercialLine.coliId", target = "coliIdComercialLine")
	@Mapping(source = "company.compId", target = "compIdCompany")

	public BillingDTO billingToBillingDTO(Billing billing);

	@Mapping(source = "bistIdBillingStatus", target = "billingStatus.bistId")
	@Mapping(source = "coliIdComercialLine", target = "comercialLine.coliId")
	@Mapping(source = "compIdCompany", target = "company.compId")

	public Billing billingDTOToBilling(BillingDTO billingDTO);

	public List<BillingDTO> listBillingToListBillingDTO(List<Billing> billings);

	public List<Billing> listBillingDTOToListBilling(List<BillingDTO> billingDTOs);
}
