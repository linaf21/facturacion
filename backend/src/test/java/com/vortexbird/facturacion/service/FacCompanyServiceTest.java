package com.vortexbird.facturacion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vortexbird.facturacion.builder.FacCompanyBuilder;
import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.entity.service.CompanyService;
import com.vortexbird.facturacion.entity.service.CompanyServiceImpl;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.CompanyMapper;
import com.vortexbird.facturacion.repository.CompanyRepository;
import com.vortexbird.facturacion.utility.Constantes;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@ExtendWith(MockitoExtension.class)
class FacCompanyServiceTest {

	@InjectMocks
	FacCompanyServiceImpl facCompanyServiceImpl;

	@Captor
	ArgumentCaptor<Company> companyCaptor;

	@Mock
	CompanyRepository companyRepository;

	@Mock
	CompanyService companyService;
	
	@Mock
	CompanyMapper companyMapper;

	@Nested
	class validateValidateCompany {

		@Test
		void itThrowsExceptionCompanyIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = null;
			String messageExpectd = "La empresa esta nula o vacía.";
			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itThrowsExceptionCompanyNameIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setName(null);
			String messageExpectd = "El nombre de la empresa esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyNameIsEmpty() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setName(" ");
			String messageExpectd = "El nombre de la empresa esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyBankIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setBank(null);
			String messageExpectd = "El campo banco esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyBankIsEmpty() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setBank(" ");
			String messageExpectd = "El campo banco esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyNitIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setNit(null);
			String messageExpectd = "El campo nit esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyNitIsUnder() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setNit(12);
			String messageExpectd = "El nit no debe ser menor a 6 caracteres.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyNitIsAbove() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setNit(123456789);
			String messageExpectd = "El nit no debe ser mayor a 6 caracteres.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyDeadlineIsNegative() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setPaymentDeadline(0);
			String messageExpectd = "El plazo para pago debe ser mayor o igual a 1 caracteres.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyDeadlineIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setPaymentDeadline(null);
			String messageExpectd = "El plazo pago de la empresa esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.validateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}
	}

	@Nested
	class validateFindCompanyFilter {

		@Test
		void itThrowsExceptionNotExistingCompany() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
//			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			List<CompanyDTO> companyDTOs = new ArrayList<>();
			String messageExpectd = "No se encuentra la empresa relacionada a los filtros.";

			// Act
			when(companyRepository.findCompanyFilter(any(), any(), any())).thenReturn(companyDTOs);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.findCompanyFilter(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itQuerysWhenNitIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setNit(null);

			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			// Act
			when(companyRepository.findCompanyFilter(any(), eq(-1), any())).thenReturn(companyDTOs);
			List<CompanyDTO> result = facCompanyServiceImpl.findCompanyFilter(companyDTO);
			// Assert
			assertNotNull(result);

		}

		@Test
		void itQuerysWhenNameIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setName(null);

			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			// Act
			when(companyRepository.findCompanyFilter(any(), any(), eq("-1"))).thenReturn(companyDTOs);
			List<CompanyDTO> result = facCompanyServiceImpl.findCompanyFilter(companyDTO);
			// Assert
			assertNotNull(result);

		}

		@Test
		void itQuerysWhenNameIsEmpty() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setName(" ");

			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			// Act
			when(companyRepository.findCompanyFilter(any(), any(), eq("-1"))).thenReturn(companyDTOs);
			List<CompanyDTO> result = facCompanyServiceImpl.findCompanyFilter(companyDTO);
			// Assert
			assertNotNull(result);

		}

		@Test
		void itQuerysWhenCompIdIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setCompId(null);

			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			// Act
			when(companyRepository.findCompanyFilter(eq(-1), any(), any())).thenReturn(companyDTOs);
			List<CompanyDTO> result = facCompanyServiceImpl.findCompanyFilter(companyDTO);
			// Assert
			assertNotNull(result);

		}

	}

	@Nested
	class validateFindCompanyByStatusActive {

		@Test
		void itThrowsExceptionNotActiveCompanys() throws VortexbirdException {

			// Arrange
			List<CompanyDTO> companyDTOs = new ArrayList<>();
			String messageExpectd = "No se encuentran empresas activas.";

			// Act
			when(companyRepository.findCompanyByStatus(any())).thenReturn(companyDTOs);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.findCompanyByStatusActive();
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}
	}

	@Nested
	class validateFindByFilters {

		@Test
		void itThrowsExceptionPageNumberIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setPageNumber(null);
			String messageExpectd = "El numero de página esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.findByFilters(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionPageSizeIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setPageNumber(1);
			companyDTO.setPageSize(null);
			String messageExpectd = "El tamaño de página esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.findByFilters(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itQuerysWhenNitIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setNit(null);

			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			Page<CompanyDTO> page = FacCompanyBuilder.getCompanyPageDTO();
			// Act
			when(companyRepository.findCompanyFilter(any(), any(), any())).thenReturn(companyDTOs);

			when(companyRepository.findByFilters(any(), eq(-1), any(), any())).thenReturn(page);
			Page<CompanyDTO> pageResult = facCompanyServiceImpl.findByFilters(companyDTO);

			// Assert
			assertEquals(page, pageResult);

		}

		@Test
		void itQuerysWhenNameIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setName(null);

			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			Page<CompanyDTO> page = FacCompanyBuilder.getCompanyPageDTO();
			// Act
			when(companyRepository.findCompanyFilter(any(), any(), any())).thenReturn(companyDTOs);

			when(companyRepository.findByFilters(any(), any(), eq("-1"), any())).thenReturn(page);
			Page<CompanyDTO> pageResult = facCompanyServiceImpl.findByFilters(companyDTO);

			// Assert
			assertEquals(page, pageResult);

		}

		@Test
		void itQuerysWhenCompIdIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setCompId(null);

			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			Page<CompanyDTO> page = FacCompanyBuilder.getCompanyPageDTO();
			// Act
			when(companyRepository.findCompanyFilter(any(), any(), any())).thenReturn(companyDTOs);

			when(companyRepository.findByFilters(eq(-1), any(), any(), any())).thenReturn(page);
			Page<CompanyDTO> pageResult = facCompanyServiceImpl.findByFilters(companyDTO);

			// Assert
			assertEquals(page, pageResult);

		}

		@Test
		void itThrowsExceptionNotExistingCompany() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setPageNumber(1);
			companyDTO.setPageSize(1);
			String messageExpectd = "No se encuentra la empresa relacionada a los filtros.";
//			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			List<CompanyDTO> companyDTOs = new ArrayList<>();

			// Act
			when(companyRepository.findCompanyFilter(any(), any(), any())).thenReturn(companyDTOs);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.findByFilters(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}
	}

	@Nested
	class validateCreatesCompany {

		@Test
		void itThrowsExceptionExistingCompanyNit() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setNit(200212);
			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			companyDTOs.get(0).setNit(123456);
			String messageExpectd = "Ya existe la empresa de nit: " + companyDTO.getNit();

			// Act
			when(companyRepository.findCompanyFilter(any(), any(), any())).thenReturn(companyDTOs);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.createCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itCreatesACompany() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();

			Company companySent;
			when(companyRepository.findCompanyFilter(any(), any(), any())).thenReturn(companyDTOs);
			// Act
			facCompanyServiceImpl.createCompany(companyDTO);
			verify(companyService).save(companyCaptor.capture());
			companySent = companyCaptor.getValue();
			// Assert
			assertAll(() -> assertEquals(companyDTO.getNit(), companySent.getNit()),
					() -> assertEquals(companyDTO.getName(), companySent.getName()),
					() -> assertEquals(companyDTO.getBank(), companySent.getBank()),
					() -> assertEquals(companyDTO.getPaymentDeadline(), companySent.getPaymentDeadline()),
					() -> assertEquals(companyDTO.getCreationUser(), companySent.getCreationUser()),
					() -> assertNotNull(companySent.getCreationDate()),
					() -> assertEquals(companyDTO.getStatus(), companySent.getStatus()));

		}
	}

	@Nested
	class validateUpdateACompany {

		@Test
		void itThrowsExceptionCompanyNameIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setName(null);
			String messageExpectd = "El nombre de la empresa esta nulo o vacío.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyNameIsEmpty() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setName(" ");
			String messageExpectd = "El nombre de la empresa esta nulo o vacío.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyBankIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setBank(null);
			String messageExpectd = "El campo banco esta nulo o vacío.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itThrowsExceptionCompanyBankIsEmpty() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setBank(" ");
			String messageExpectd = "El campo banco esta nulo o vacío.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itThrowsExceptionCompanyNitIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setNit(null);
			String messageExpectd = "El campo nit esta nulo o vacío.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyNitIsUnder() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setNit(12);
			String messageExpectd = "El nit no debe ser menor a 6 caracteres.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyNitIsAbove() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setNit(123456789);
			String messageExpectd = "El nit no debe ser mayor a 6 caracteres.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyDeadlineIsNegative() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setPaymentDeadline(0);
			String messageExpectd = "El plazo para pago debe ser mayor o igual a 1 caracteres.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyDeadlineIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			companyDTO.setPaymentDeadline(null);
			String messageExpectd = "El plazo pago de la empresa esta nulo o vacío.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionCompanyIsNull() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = null;
			String messageExpectd = "La empresa esta nula o vacía.";
			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itThrowsExceptionNotExistingCompany() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = new CompanyDTO();
			Optional<Company> companys = Optional.empty();
			String messageExpected = "No se conoce la empresa a modificar.";

			// Act
			when(companyRepository.findById(any())).thenReturn(companys);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facCompanyServiceImpl.updateCompany(companyDTO);
			});

			// Assert
			assertEquals(messageExpected, exception.getException());

		}

		@Test
		void itSetsStatusCompany() throws VortexbirdException {

			// Arrange
			CompanyDTO companyDTO = FacCompanyBuilder.getCompanyDTO();
			companyDTO.setNit(111999);
			companyDTO.setName("iKea");
			companyDTO.setPaymentDeadline(30);
			companyDTO.setBank("Fundacion Banco Agrario");
			Company company = FacCompanyBuilder.getCompany();
			Optional<Company> companyOPT = Optional.of(company);
			Company companySent;			

			// Act
			when(companyRepository.findById(any())).thenReturn(companyOPT);
			facCompanyServiceImpl.updateCompany(companyDTO);
			verify(companyService).update(companyCaptor.capture());
			companySent = companyCaptor.getValue();
		
			// Assert
			assertAll(() -> assertEquals(companyDTO.getNit(), companySent.getNit()),
					() -> assertEquals(companyDTO.getName(), companySent.getName()),
					() -> assertEquals(companyDTO.getBank(), companySent.getBank()),
					() -> assertEquals(companyDTO.getPaymentDeadline(), companySent.getPaymentDeadline()),
					() -> assertNotNull(companySent.getCreationUser()),
					() -> assertNotNull(companySent.getCreationDate()),
					() -> assertEquals(companySent.getStatus(),Constantes.STATUS_ACTIVE));

		}

	}
}