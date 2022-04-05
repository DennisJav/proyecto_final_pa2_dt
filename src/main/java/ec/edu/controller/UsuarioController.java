package ec.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;
import ec.edu.service.IUsuarioService;
import ec.edu.service.IVehiculoService;

@Controller
@RequestMapping("/usuarios/")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IVehiculoService vehiculoService;

	@GetMapping("usuarioN")
	public String vistaPaginaIngresoDatos(Usuario usuario) {
		return "usuarioNuevo";
	}

	@PostMapping("insertar")
	public String insertarUsuarioCliente(Usuario usuario, BindingResult result, Model modelo) {
		this.usuarioService.insertarUsuario(usuario);
		return "usuarioRegistradoNotify";
	}

	@GetMapping("datosVehiculos")
	public String mostrarMarcaModelo(Vehiculo vehiculo) {
		return "mostrarMarcaModelo";
	}

	@GetMapping("buscar/disponibles")
	public String buscarVehiculosMarcaModelo(Vehiculo vehiculo, Model modelo) {

		List<Vehiculo> vehiculosDisponibles = this.vehiculoService.buscarMarcaModelo(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelo.addAttribute("vehiculosDisponibles",vehiculosDisponibles);
		return "listaVehiculosDisponibles";
	}
	
	
	

}
