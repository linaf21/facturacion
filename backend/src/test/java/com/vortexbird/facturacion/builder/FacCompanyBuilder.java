package com.vortexbird.facturacion.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.CompanyDTO;

public class FacCompanyBuilder {

	public static Company getCompany() {

		Company salida = new Company();

		salida.setCompId(500);
		salida.setBank("Bancolombia");
		salida.setName("Google");
		salida.setNit(123456);
		salida.setPaymentDeadline(10);
		salida.setStatus("A");
		salida.setCreationDate(new Date());
		salida.setCreationUser("123456");

		return salida;

	}

	public static CompanyDTO getCompanyDTO() {

		CompanyDTO salida = new CompanyDTO();

		salida.setCompId(500);
		salida.setBank("Bancolombia");
		salida.setName("Google");
		salida.setNit(123456);
		salida.setPaymentDeadline(10);
		salida.setStatus("A");
		salida.setCreationDate(new Date());
		salida.setCreationUser("123456");
		salida.setPageNumber(0);
		salida.setPageSize(10);

		return salida;

	}
	
	public static CompanyDTO getEmptyCompanyDTO() {

		CompanyDTO salida = new CompanyDTO();

		salida.setCompId(null);
		salida.setBank(null);
		salida.setName(null);
		salida.setNit(null);
		salida.setPaymentDeadline(null);
		salida.setStatus(null);
		salida.setCreationDate(null);
		salida.setCreationUser(null);
		salida.setPageNumber(null);
		salida.setPageSize(null);

		return salida;

	}

	public static List<Company> getCompanyList() {

		List<Company> lista = new ArrayList<>();

		lista.add(getCompany());

		return lista;
	}

	public static List<CompanyDTO> getCompanyListDTO() {

		List<CompanyDTO> lista = new ArrayList<>();

		lista.add(getCompanyDTO());

		return lista;
	}

	public static Page<CompanyDTO> getCompanyPageDTO() {

		List<CompanyDTO> companyDTOs = new ArrayList<>();
		CompanyDTO salida = new CompanyDTO();
		salida.setCompId(500);
		salida.setBank("Bancolombia");
		companyDTOs.add(salida);
		Page<CompanyDTO> page = new PageImpl<>(companyDTOs);

		return page;
	}
}