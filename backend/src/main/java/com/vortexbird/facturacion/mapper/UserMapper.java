package com.vortexbird.facturacion.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vortexbird.facturacion.domain.User;
import com.vortexbird.facturacion.dto.UserDTO;

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
public interface UserMapper {

	public UserDTO userToUserDTO(User user);

	public User userDTOToUser(UserDTO userDTO);

	public List<UserDTO> listUserToListUserDTO(List<User> users);

	public List<User> listUserDTOToListUser(List<UserDTO> userDTOs);
}
