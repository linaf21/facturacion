package  com.vortexbird.facturacion.service;


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

import com.vortexbird.facturacion.builder.FacCompanyBuilder;
import com.vortexbird.facturacion.builder.FacUserBuilder;
import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.domain.User;
import com.vortexbird.facturacion.domain.UserApplication;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.dto.UserDTO;
import com.vortexbird.facturacion.entity.service.UserService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.repository.CompanyRepository;
import com.vortexbird.facturacion.repository.UserRepository;

/**
* @author Zathura Code Generator Version 22.08 http://zathuracode.org/
* www.zathuracode.org
* @generationDate 2022-08-23T11:10:20.573413
* 
*/

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class FacUserServiceTest{
	
	@InjectMocks
	FacUserServiceImpl facUserServiceImpl;
	
	@Mock
	FacUserService facUserService;
	
	@Captor
	ArgumentCaptor<UserDTO> userDTOCaptor;

	@Mock
	UserRepository userRepository;
	
	@Mock
	UserService userService;
	
	@Nested
	class validateValidateUser {

		@Test
		void itThrowsExceptionUserIsNull() throws VortexbirdException {
				//Arrange
			UserDTO userDTO = FacUserBuilder.getUserDTO();
			userDTO.setUser(null);
			String messageExpectd = "El nombre de usuario esta nulo o vacío.";

				
				//Act
			
			VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facUserServiceImpl.validateUser(userDTO);
			});
				
				
				//Assert
				assertEquals(messageExpectd, exception.getException());

		    }
		
			@Test
		    void itThrowsExceptionPasswordIsNull() throws VortexbirdException {
		    	//Arrange
				UserDTO userDTO = FacUserBuilder.getUserDTO();
				userDTO.setPassword(null);
				String messageExpectd = "El campo de la contraseña esta nulo o vacío.";
				
				//Act
				VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
					facUserServiceImpl.validateUser(userDTO);
				});
				
				//Assert
				assertEquals(messageExpectd, exception.getException());
		    }
			@Test
			void itThrowsExceptionPasswordIsEmpty() throws VortexbirdException {
					//Arrange
				UserDTO userDTO = FacUserBuilder.getUserDTO();
				userDTO.setUser(" ");
				String messageExpectd = "El nombre de usuario esta nulo o vacío.";

					
					//Act
				
				VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
					facUserServiceImpl.validateUser(userDTO);
				});
					
					
					//Assert
					assertEquals(messageExpectd, exception.getException());

			    }
			
			@Test
		    void itThrowsExceptionUserIsShort() throws VortexbirdException {
				// Arrange
				UserDTO userDTO = FacUserBuilder.getUserDTO();
				userDTO.setUser(" a");
				String messageExpectd = "El nombre no puede ser menor a 1 caracter.";

				// Act
				VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
					facUserServiceImpl.validateUser(userDTO);
				});

				// Assert
				assertEquals(messageExpectd, exception.getException());

		    }
			
			@Test
		    void itThrowsExceptionUserIsLong() throws VortexbirdException {
				// Arrange
				UserDTO userDTO = FacUserBuilder.getUserDTO();
				userDTO.setUser("md+&O&!BT1RFn!oYA=hW+f=eauSOq8dX1ZVYu&mp2xZzW#!UH#xvnR!htwRr7BRPzF7neVZ&gbA%dZeFNPgdwYUQ3!K7+uZEZENK\r\n"
						+ "YvK#+tMT=2yZzpcFHcP5!1sK1jhcO0=&j2n&w33wHoP1gnxPFsdB%A0quYOGrn51PTF0%%eR$OB98H=pdOxHhuDMfNKD#2dt#cy*\r\n"
						+ "r3Np7Z2pDPG#OYkqbgS1&ZeS$6pR&=8VZP5MCoPF81fZ3Ba7M!J1MEFyYM5ay6h*a#Domkcvj1zBSGNdNYe5rJB5MWqzVW8k4Z#W\r\n"
						+ "T#7fhq+5z$4xJ46uRdarfw=CT0SXUE=+tF1#FP=pe6H!nbADMQPur7P&6QZ$bjTMfZAoe%R7kweCmG8ty5Rp9vM@MJo4EUtUbRsE\r\n"
						+ "4N5Q%SBc5hNm+J&TrSdc%RXg0BCTzOY8gvXN29a7gOGz=+bG%UJh%T76jsuFcJyr6G*R+G*oJwxb&VKbTs676UggFxj*DQM0r%ECh");
				String messageExpectd = "El nombre no puede ser mayor a 100 caracteres.";

				// Act
				VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
					facUserServiceImpl.validateUser(userDTO);
				});

				// Assert
				assertEquals(messageExpectd, exception.getException());

		    }
			@Test
		    void itThrowsExceptionPasswordIsShort() throws VortexbirdException {
				// Arrange
				// Arrange
				UserDTO userDTO = FacUserBuilder.getUserDTO();
				userDTO.setPassword("a");
				String messageExpectd = "La contraseña no debe ser menor a 1 caracter.";

				// Act
				VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
					facUserServiceImpl.validateUser(userDTO);
				});

				// Assert
				assertEquals(messageExpectd, exception.getException());

		    }
			@Nested //metodos
			class validatefindByUserAndStatus {
			
			@Test
		    void itThrowsExceptionUserNoExistOrInvalid() throws VortexbirdException {
				
				// Arrange
				
				UserDTO userDTO = FacUserBuilder.getUserDTO();
				
				
				
				List<User> User = new ArrayList<>();
				String messageExpectd = "El usuario no existe o no está activo";
				

				// Act
				
				when(userRepository.findByUserAndStatus(any(),any())).thenReturn(User);
				VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facUserServiceImpl.login(userDTO);
				});
				
				// Assert
				
				assertEquals(messageExpectd, exception.getException());	

				}
			@Test
		    void itThrowsExceptionPasswordAndUserIsWrong() throws VortexbirdException {
				
				// Arrange
				
				UserDTO userDTO = FacUserBuilder.getUserDTO();
				String messageExpectd = "Usuario o contraseña incorrectos";
				
				List<User> users = FacUserBuilder.getUsers();
				

				// Act
				
				when(userRepository.findByUserAndStatus(any(),any())).thenReturn(users);
				VortexbirdException exception = assertThrows(VortexbirdException.class, () -> {
				facUserServiceImpl.login(userDTO);
				});
				
				// Assert
				
				assertEquals(messageExpectd, exception.getException());
						
			}

			@Nested
			class validateCreatesUser {
				
				@Test
				void itCompareReturnToken() throws Exception {

					// Arrange
					UserDTO userDTO = FacUserBuilder.getUserDTO();
					List<User> user = FacUserBuilder.getUsers();
					userDTO.setPassword("12345");
					UserDTO result;
					
					when(userRepository.findByUserAndStatus(any(),any())).thenReturn(user);
					// Act
					result = facUserServiceImpl.login(userDTO);
				
					// Assert
					assertNotNull(result.getToken());

				}
				
				
				}
			}
	}
}
	

				
	

		    
		
		
//			@Test
//			@DisplayName("delete")
//			void delete() throws Exception {
//				//Arrange
//				
//				//Act
//				
//				//Assert
//				assertNotNull(userService);
//			}
//		
//		    @Test
//			@DisplayName("deleteById")
//		    void deleteById() throws Exception {
//		    	//Arrange
//				
//				//Act
//				
//				//Assert
//		    	assertNotNull(userService);
//		    }
//		
//			@Test
//			@DisplayName("update")
//		    void update() throws Exception {
//		    	//Arrange
//				
//				//Act
//				
//				//Assert
//				assertNotNull(userService);
//		    }
//		
//			@Test
//			@DisplayName("findById")
//		    void findById() throws Exception {
//		    	//Arrange
//				
//				//Act
//				
//				//Assert
//		    	assertNotNull(userService);
//		    }
//		
//			@Test
//			@DisplayName("count")
//		    void count() throws Exception {
//		    	//Arrange
//				
//				//Act
//				
//				//Assert
//		    	assertNotNull(userService);


	

