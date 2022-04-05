package ec.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.edu.modelo.Reserva;
import ec.edu.service.IReservaService;
import ec.edu.service.IUsuarioService;
import ec.edu.service.IVehiculoService;

@Controller
@RequestMapping("/reportes/")
public class ReportesController {

	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IReservaService reservaService;
	
	
	
	
	
}
