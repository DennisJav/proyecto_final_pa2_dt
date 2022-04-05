package ec.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;
import ec.edu.service.IBudgetService;
import ec.edu.service.IUsuarioService;
import ec.edu.service.IVehiculoService;

@Controller
@RequestMapping("/empleados/")
public class UsuarioEmpleadoController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IBudgetService budgetService;
	
	@GetMapping("registrarC")
	public String vistaPaginaIngresoDatosCliente(Usuario usuario) {
		return "registrarCliente";
	}
	
	@PostMapping("insertar")
	public String insertarUsuarioCliente(Usuario usuario, BindingResult result, Model modelo) {
		this.usuarioService.insertarUsuario(usuario);
		return "clienteRegistradoNotify";
	}
	
	
	@GetMapping("buscar/cliente")
	public String vistaPaginaBuscarCliente(Usuario usuario) {
		return "buscarCliente";
	}
	
	@GetMapping("buscar/cliente/resultado")
	public String buscarClienteCedula(Usuario usuario, Model modelo) {
		Usuario clienteBuscado = this.usuarioService.buscarUsuarioCedula(usuario.getCedula());
		modelo.addAttribute("clienteBuscado",clienteBuscado);
		return "mostrarCliente";
	}
	
	
	@GetMapping("datosVehiculo")
	private String vistaPaginaInsertarDatosVehiculo(Vehiculo vehiculo) {
		return "registrarVehiculo";
	}
	
	
	@PostMapping("insertar/vehiculo")
	private String insertarVehiculo(Vehiculo vehiculo, BindingResult result, Model modelo) {
		this.vehiculoService.insertarVehiculo(vehiculo);
		return "vehiculoRegistradoNotify";
	}
	
	
	@GetMapping("buscar/vehiculo/placa")
	private String buscarVehiculoPlaca(Vehiculo vehiculo) {
		return "buscarPlaca";
	}
	@GetMapping("buscar/vehiculo/placa/resultado")
	private String resultadoVehiculoPlaca(Vehiculo vehiculo, Model modelo) {
		Vehiculo vehiculoBuscado = this.vehiculoService.buscarVehiculoPlaca(vehiculo.getPlaca());
		modelo.addAttribute("vehiculoBuscado",vehiculoBuscado);
		return "mostrarPlaca";
	}
	
	
	
}
