package com.vortexbird.facturacion.builder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vortexbird.facturacion.domain.Company;
import com.vortexbird.facturacion.domain.User;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.dto.UserDTO;

public class FacUserBuilder {
public static User getUser() {
		
		User salida= new User();
		
		salida.setPassword("12345");
		salida.setStatus("A");
		salida.setUser("SCA");
		salida.setUserId(3);
		salida.setCreationDate(new Date());
		salida.setCreationUser("uwu");
		
		return salida;
		
	}
	
	public static UserDTO getUserDTO() {
		
		UserDTO salida= new UserDTO();
		
		salida.setPassword("098765");
		salida.setStatus("A");
		salida.setUser("SCA");
		salida.setUserId(3);
		salida.setCreationDate(new Date());
		salida.setCreationUser("uwu");
		
		return salida;
		
	}
	
	public static List<User> getUsers() {

		List<User> lista = new ArrayList<>();

		lista.add(getUser());

		return lista;
	}
	
	public static List<UserDTO> getUserDTOs() {

		List<UserDTO> lista = new ArrayList<>();

		lista.add(getUserDTO());

		return lista;
	
}
	
}



