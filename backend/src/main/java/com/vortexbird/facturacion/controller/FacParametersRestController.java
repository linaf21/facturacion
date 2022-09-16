package com.vortexbird.facturacion.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.facturacion.domain.Parameters;
import com.vortexbird.facturacion.dto.ParametersDTO;
import com.vortexbird.facturacion.entity.service.ParametersService;
import com.vortexbird.facturacion.exception.VortexbirdException;
import com.vortexbird.facturacion.mapper.ParametersMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@RestController
@RequestMapping("/api/v1/parameters")
@Slf4j
public class FacParametersRestController {

}
