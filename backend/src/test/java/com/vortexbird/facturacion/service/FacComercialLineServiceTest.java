package com.vortexbird.facturacion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.vortexbird.facturacion.builder.FacComercialLineBuilder;
import com.vortexbird.facturacion.builder.FacCompanyBuilder;
import com.vortexbird.facturacion.domain.ComercialLine;
import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.dto.ComercialLineDTO;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.entity.service.ComercialLineService;
import com.vortexbird.facturacion.entity.service.CompanyService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.ComercialLineMapper;
import com.vortexbird.facturacion.mapper.CompanyMapper;
import com.vortexbird.facturacion.repository.ComercialLineRepository;
import com.vortexbird.facturacion.repository.CompanyRepository;
import com.vortexbird.facturacion.utility.Constantes;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@ExtendWith(MockitoExtension.class)
class FacComercialLineServiceTest {

	@InjectMocks
	FacComercialLineServiceImpl facComercialLineServiceImpl;

	@Captor
	ArgumentCaptor<ComercialLine> comercialLineCaptor;

	@Mock
	ComercialLineRepository comercialLineRepository;

	@Mock
	ComercialLineService comercialLineService;

	@Mock
	ComercialLineMapper comercialLineMapper;

	@Nested
	class validateValidateComercialLine {

		@Test
		void itThrowsExceptionComercialLineIsNull() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = null;
			String messageExpectd = "La linea comercial esta nula o vacía.";
			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.validateComercialLine(comercialLineDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itThrowsExceptionComercialLineNameIsNull() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			comercialLineDTO.setName(null);
			String messageExpectd = "El nombre de la linea comercial esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.validateComercialLine(comercialLineDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionComercialLineNameIsEmpty() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			comercialLineDTO.setName(" ");
			String messageExpectd = "El nombre de la linea comercial esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.validateComercialLine(comercialLineDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

	}
	
	@Nested
	class validateValidateComercialLineAuditory {

		@Test
		void itThrowsExceptionComercialLineIsNull() throws VortexbirdException {

			// Arrange
			ComercialLine comercialLine = null;
			String messageExpectd = "La linea comercial esta nula o vacía.";
			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.validateComercialLineAuditory(comercialLine);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itThrowsExceptionComercialLineCreationDateIsNull() throws VortexbirdException {

			// Arrange
			ComercialLine comercialLine = FacComercialLineBuilder.getComercialLine();
			comercialLine.setCreationDate(null);
			String messageExpectd = "La fecha de la linea comercial esta nula o vacía.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.validateComercialLineAuditory(comercialLine);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionComercialLineCreationUserIsNull() throws VortexbirdException {

			// Arrange
			ComercialLine comercialLine = FacComercialLineBuilder.getComercialLine();
			comercialLine.setCreationUser(null);
			String messageExpectd = "El Usuario creador de la linea comercial esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.validateComercialLineAuditory(comercialLine);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}
		
		@Test
		void itThrowsExceptionComercialLineCreationUserIsEmpty() throws VortexbirdException {

			// Arrange
			ComercialLine comercialLine = FacComercialLineBuilder.getComercialLine();
			comercialLine.setCreationUser("  ");
			String messageExpectd = "El Usuario creador de la linea comercial esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.validateComercialLineAuditory(comercialLine);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

	}


	@Nested
	class validateFindComercialLineFilter {

		@Test
		void itThrowsExceptionNotExistingComercialLine() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
//			List<CompanyDTO> companyDTOs = FacCompanyBuilder.getCompanyListDTO();
			List<ComercialLineDTO> comercialLineDTOs = new ArrayList<>();
			String messageExpectd = "No se encuentra la linea comercial relacionada a los filtros.";

			// Act
			when(comercialLineRepository.findComercialLineFilter(any(), any())).thenReturn(comercialLineDTOs);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.findComercialLineFilter(comercialLineDTO);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itQuerysWhenNameIsNull() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			comercialLineDTO.setName(null);

			List<ComercialLineDTO> comercialLineDTOs = FacComercialLineBuilder.getComercialLineDTOs();
			// Act
			when(comercialLineRepository.findComercialLineFilter(any(), eq("-1"))).thenReturn(comercialLineDTOs);
			List<ComercialLineDTO> result = facComercialLineServiceImpl.findComercialLineFilter(comercialLineDTO);
			// Assert
			assertNotNull(result);

		}

		@Test
		void itQuerysWhenNameIsEmpty() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			comercialLineDTO.setName("   ");

			List<ComercialLineDTO> comercialLineDTOs = FacComercialLineBuilder.getComercialLineDTOs();
			// Act
			when(comercialLineRepository.findComercialLineFilter(any(), eq("-1"))).thenReturn(comercialLineDTOs);
			List<ComercialLineDTO> result = facComercialLineServiceImpl.findComercialLineFilter(comercialLineDTO);
			// Assert
			assertNotNull(result);

		}

		@Test
		void itQuerysWhenColiIdIsNull() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			comercialLineDTO.setColiId(null);

			List<ComercialLineDTO> comercialLineDTOs = FacComercialLineBuilder.getComercialLineDTOs();
			// Act
			when(comercialLineRepository.findComercialLineFilter(eq(-1), any())).thenReturn(comercialLineDTOs);
			List<ComercialLineDTO> result = facComercialLineServiceImpl.findComercialLineFilter(comercialLineDTO);
			// Assert
			assertNotNull(result);

		}
	}

	@Nested
	class validateFindCompanyByStatus {

		@Test
		void itThrowsExceptionComercialLineStatusIsNull() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			comercialLineDTO.setStatus(null);
			String messageExpectd = "El estado de la linea comercial esta nulo o vacío.";
			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.findComercialLineaByStatus(comercialLineDTO.getStatus());
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());
		}

		@Test
		void itThrowsExceptionComercialLineStatusIsEmpty() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			comercialLineDTO.setStatus(" ");
			String messageExpectd = "El estado de la linea comercial esta nulo o vacío.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.findComercialLineaByStatus(comercialLineDTO.getStatus());
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionComercialLineStatusIsAbove() throws VortexbirdException {

			// Arrange

			String status = "Ac";
			String messageExpectd = "El estado de la linea comercial no debe ser mayor o menor a 1 caracter.";

			// Act
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.findComercialLineaByStatus(status);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionNotComercialLinesFoundForActive() throws VortexbirdException {

			// Arrange
			List<ComercialLineDTO> comercialLineDTOs = new ArrayList<>();
			String messageExpectd = "No se encuentran lineas comerciales.";

			// Act
			when(comercialLineRepository.findComercialLineByStatus(Constantes.STATUS_ACTIVE))
					.thenReturn(comercialLineDTOs);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.findComercialLineaByStatus(Constantes.STATUS_ACTIVE);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}

		@Test
		void itThrowsExceptionNotComercialLinesFoundForInactive() throws VortexbirdException {

			// Arrange
			List<ComercialLineDTO> comercialLineDTOs = new ArrayList<>();
			String messageExpectd = "No se encuentran lineas comerciales.";

			// Act
			when(comercialLineRepository.findComercialLineByStatus(Constantes.STATUS_INACTIVE))
					.thenReturn(comercialLineDTOs);
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facComercialLineServiceImpl.findComercialLineaByStatus(Constantes.STATUS_INACTIVE);
			});

			// Assert
			assertEquals(messageExpectd, exception.getException());

		}
	}

	@Nested
	class validateCreatesComercialLine {

		@Test
		void itCreatesAComercialLine() throws VortexbirdException {

			// Arrange
			ComercialLineDTO comercialLineDTO = FacComercialLineBuilder.getComercialLineDTO();
			ComercialLine comercialLineSent;
			// Act
			facComercialLineServiceImpl.createComercialLine(comercialLineDTO);
			verify(comercialLineService).save(comercialLineCaptor.capture());
			comercialLineSent = comercialLineCaptor.getValue();
			// Assert
			assertAll(() -> assertEquals(comercialLineDTO.getName(), comercialLineSent.getName()),
					() -> assertNotNull(comercialLineSent.getCreationUser()),
					() -> assertNotNull(comercialLineSent.getCreationDate()),
					() -> assertEquals(comercialLineDTO.getStatus(), comercialLineSent.getStatus()));

		}
	}
	

}
