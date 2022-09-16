package com.vortexbird.facturacion.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.vortexbird.facturacion.domain.ComercialLine;
import com.vortexbird.facturacion.dto.ComercialLineDTO;
import com.vortexbird.facturacion.utility.Constantes;

public class FacComercialLineBuilder {

	public static ComercialLine getComercialLine() {

		ComercialLine salida = new ComercialLine();

		salida.setColiId(500);
		salida.setName("Bancolombia");
		salida.setStatus(Constantes.STATUS_ACTIVE);
		salida.setCreationDate(new Date());
		salida.setCreationUser("Alejandra");

		return salida;

	}

	public static ComercialLineDTO getComercialLineDTO() {

		ComercialLineDTO salida = new ComercialLineDTO();

		salida.setColiId(500);
		salida.setName("Bancolombia");
		salida.setStatus(Constantes.STATUS_ACTIVE);
		salida.setCreationDate(new Date());
		salida.setCreationUser("Alejandra");
		salida.setPageNumber(0);
		salida.setPageSize(10);

		return salida;

	}
	
	public static List<ComercialLine> getComercialLines() {

		List<ComercialLine> lista = new ArrayList<>();

		lista.add(getComercialLine());

		return lista;
	}

	public static List<ComercialLineDTO> getComercialLineDTOs() {

		List<ComercialLineDTO> lista = new ArrayList<>();

		lista.add(getComercialLineDTO());

		return lista;
	}

	public static Page<ComercialLineDTO> getComPage() {

		List<ComercialLineDTO> comercialLineDTOs = new ArrayList<>();
		ComercialLineDTO salida = new ComercialLineDTO();
		salida.setColiId(500);
		salida.setName("Niki");
		comercialLineDTOs.add(salida);
		Page<ComercialLineDTO> page = new PageImpl<>(comercialLineDTOs);

		return page;
	}
	
}
