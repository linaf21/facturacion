package com.vortexbird.facturacion.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.facturacion.domain.User;
import com.vortexbird.facturacion.dto.CompanyDTO;
import com.vortexbird.facturacion.dto.UserDTO;
import com.vortexbird.facturacion.entity.service.UserService;
import com.vortexbird.facturacion.exception.UserException;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.exception.ZMessManager;
import com.vortexbird.facturacion.repository.UserRepository;
import com.vortexbird.facturacion.security.Constants;
import com.vortexbird.facturacion.utility.Constantes;
import com.vortexbird.facturacion.utility.PasswordGenerator;
import com.vortexbird.facturacion.utility.Utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@Scope("singleton")
@Service
@Slf4j
public class FacUserServiceImpl implements FacUserService {
	
	public void validateUser(UserDTO userdtO) throws VortexbirdException {
		if(userdtO.getUser() != null && !userdtO.getUser().trim().isEmpty()) {
//			validate that the name is not longer than 100 characters
			if(userdtO.getUser().trim().length() > 100) {
				throw new UserException("El nombre no puede ser mayor a 100 caracteres.");
			}
			if(userdtO.getUser().trim().length() <= 1){
				throw new UserException("El nombre no puede ser menor a 1 caracter.");
			}
		}else {
			throw new UserException("El nombre de usuario esta nulo o vacío.");
		}
		
		if(userdtO.getPassword() == null) {
			throw new UserException("El campo de la contraseña esta nulo o vacío.");
		}
		if(userdtO.getPassword().trim().isEmpty()) {
			throw new UserException("El campo de la contraseña esta nulo o vacío.");
		}
		if(userdtO.getPassword().toString().length() <= 1) {
			throw new UserException("La contraseña no debe ser menor a 1 caracter.");
		}
	}
	
	
	@Autowired 
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;	
	
	@Override
	@Transactional(readOnly = true)
	public UserDTO login(UserDTO userDTO) throws Exception {


		//Consultamos por usuario y estado 
		List<User> usuarioOPT = userRepository.findByUserAndStatus(userDTO.getUser(), Constantes.STATUS_ACTIVE);

		if(usuarioOPT.isEmpty()) {
			throw new UserException("El usuario no existe o no está activo"); 
		}

		User user = usuarioOPT.get(0);
// Revisar
//		String contra = bCryptPasswordEncoder.encode(userDTO);
		if(!userDTO.getPassword().equals(user.getPassword())) {
			
			throw new UserException("Usuario o contraseña incorrectos");
		}

		//Obtener token
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(Constants.SUPER_SECRET_KEY));
		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(Constants.ISSUER_INFO)
				.setSubject(userDTO.getUser())
				.setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME)).signWith(key).compact();
		
		userDTO.setToken(token);
		userDTO.setUserId(user.getUserId());
		userDTO.setPassword(null);
		userDTO.setUser(user.getUser());
		return (userDTO);


	}


}
