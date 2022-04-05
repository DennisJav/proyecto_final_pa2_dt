package ec.edu.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.edu.modelo.Reserva;
import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;
import ec.edu.service.IBudgetService;
import ec.edu.service.IReservaService;
import ec.edu.service.IUsuarioService;
import ec.edu.service.IVehiculoService;

@Controller
@RequestMapping("/empleados/")
public class UsuarioEmpleadoController {

	private Reserva aux;
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IBudgetService budgetService;
	@Autowired
	private IReservaService reservaService;
	
	@GetMapping("menu/empleado")
	public String menuPrincipal() {
		return "menuEmpleado";
	}
	
	
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
	
	@GetMapping("retirar/vehiculo/buscar")
	public String vistaPaginaRetirarrVehiculoReservado(Reserva reserva) {
		return "retirarVehiculoDatos";
	}
	
	@GetMapping("retirar/encontrado")
	public String mostrarVehiculoReservado(Model modelo, Reserva reserva) {
		
		Reserva vehiculoReserva = this.reservaService.buscarReservaNumero(reserva.getNumeroReserva());
		modelo.addAttribute("vehiculoReserva",vehiculoReserva);
		System.out.println("RESERVA MOSTRAR"+reserva.getNumeroReserva());
		aux = reserva;
		return "mostrarVehiculoRetirar";
	}
	
	
	@GetMapping("retirar/vehiculo")
	public String retirarVehiculoReservado(Model modelo, Reserva reserva) {
		System.out.println("RETIRAR+  "+reserva.getNumeroReserva());
		reserva = aux;
		aux=null;
		Reserva vehiculoReserva = this.budgetService.retirarVehiculoReserva(reserva.getNumeroReserva());
		modelo.addAttribute("vehiculoReserva",vehiculoReserva);
		
		return "retirarVehiculoEncontradoNotify";
	}
	
	/// 2F
	
	@GetMapping("sinReserva/buscar")
	public String vistaPaginaSinReservaDatos(Vehiculo vehiculo){
		return "vistaPaginaSinReserva";
	}
	
	@GetMapping("sinReserva/encontrado")
	public String mostrarSinReservaDatos(Vehiculo vehiculo, Model model) {
		
		List<Vehiculo> vehiculos = this.vehiculoService.buscarMarcaModelo(vehiculo.getMarca(), vehiculo.getModelo());
		model.addAttribute("vehiculos",vehiculos);
		
		return "listaVehiculosSinReservaDatos";
	}
	
	@GetMapping("sinReserva/ingresoDatos")
	public String obtenerPaginaVehiculo(Reserva reserva, Model modelo) {
		return "sinReservaIngresoDatos";
	}
	
	
	@GetMapping("sinReserva/verficarDisponible")
	public String verificarDisponible(Model modelo, Reserva reserva) {
		System.out.println("--------METODO VERIFICAR DISPONIBLE------- "+reserva.getFechaFin());
		Vehiculo vehiBuscado = this.vehiculoService.buscarVehiculoPlaca(reserva.getVehiculo().getPlaca());
		BigDecimal valorTotal = this.vehiculoService.costoReserva(reserva.getVehiculo().getPlaca(),
				reserva.getFechaInicio(), reserva.getFechaFin());
		List<Reserva> reservasVehiculos = vehiBuscado.getReservaVehiculo();
		if (reservasVehiculos.isEmpty()||reservasVehiculos == null) {
			System.out.println("--- ENTRO AL IF ---");
			return "pagarSinReserva";
		} else {
			for (Reserva r : reservasVehiculos) {
				if (this.budgetService.fechasNoDisponibles(r.getFechaInicio(), r.getFechaFin(),
						reserva.getFechaInicio(), reserva.getFechaFin())) {
					System.out.println("-----NO FECHAS---");
					return "vistaPaginaSinReserva";
				}
			}
			System.out.println("----EXISTES FECHAS-----");
			return "pagarSinReserva";
		}
	}
	
	@PutMapping("sinReserva/pagoGenerado")
	public String pagoGeneradoSinReserva(Model modelo, Reserva reserva) {
		Reserva reservaActualizar = this.budgetService.realizarReserva(reserva.getVehiculo().getPlaca(),
				reserva.getUsuario().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin(),
				reserva.getUsuario().getNumeroTarjeta());
		System.out.println("----METODO PAGO GENERADO DESPUES-------");
		System.out.println("PAGO GENERADO"+reservaActualizar.getValorTotal());
		modelo.addAttribute("reservaActualizar",reservaActualizar);
		return "sinReservaMostrarPago";
	}
	
	
}
