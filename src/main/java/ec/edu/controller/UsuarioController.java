package ec.edu.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.modelo.Reserva;
import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;
import ec.edu.modelo.DetalleReserva;
import ec.edu.service.IBudgetService;
import ec.edu.service.IUsuarioService;
import ec.edu.service.IVehiculoService;


@Controller
@RequestMapping("/usuarios/")
public class UsuarioController {

	private Reserva reservaGlobal;
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IBudgetService budgetService;

	@GetMapping("usuarioN")
	public String vistaPaginaIngresoDatos(Usuario usuario) {
		return "usuarioNuevo";
	}

	@PostMapping("insertar")
	public String insertarUsuarioCliente(Usuario usuario, BindingResult result, Model modelo) {
		this.usuarioService.insertarUsuario(usuario);
		return "usuarioRegistradoNotify";
	}

	//
	@GetMapping("buscar/")
	public String mostrarMarcaModelo(Vehiculo vehiculo) {
		return "mostrarMarcaModelo";
	}

	@GetMapping("resultado")
	public String buscarVehiculosMarcaModelo(Vehiculo vehiculo, Model modelo) {

		List<Vehiculo> vehiculosDisponibles = this.vehiculoService.buscarMarcaModelo(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelo.addAttribute("vehiculosDisponibles", vehiculosDisponibles);
		return "listaVehiculosDisponibles";
	}

	///
	@GetMapping("reserva/buscar/vehiculo")
	public String vistaPaginaBuscarvehiculos(Reserva reserva) {
		return "reservaIngresoDatos";
	}

	@GetMapping("verficarDisponible")
	public String verificarDisponibilidad(Reserva reserva, Model modelo, BindingResult result) {
		
		System.out.println("--------METODO VERIFICAR DISPONIBLE------- "+reserva.getFechaFin());
		Vehiculo vehiBuscado = this.vehiculoService.buscarVehiculoPlaca(reserva.getVehiculo().getPlaca());
		BigDecimal valorTotal = this.vehiculoService.costoReserva(reserva.getVehiculo().getPlaca(),
				reserva.getFechaInicio(), reserva.getFechaFin());
		List<Reserva> reservasVehiculos = vehiBuscado.getReservaVehiculo();
		if (reservasVehiculos.isEmpty()||reservasVehiculos == null) {
			System.out.println("--- ENTRO AL IF ---");
			return "pagarReserva";
		} else {
			for (Reserva r : reservasVehiculos) {
				if (this.budgetService.fechasNoDisponibles(r.getFechaInicio(), r.getFechaFin(),
						reserva.getFechaInicio(), reserva.getFechaFin())) {
					System.out.println("-----NO FECHAS---");
					return "listaVehiculosDisponibles";
				}
			}
			System.out.println("----EXISTES FECHAS-----");
			return "pagarReserva";
		}
	}

	@PutMapping("reserva/pagoGenerado")
	public String pagoGenerado(Model modelo, Reserva reserva) {
		Reserva reservaActualizar = this.budgetService.realizarReserva(reserva.getVehiculo().getPlaca(),
				reserva.getUsuario().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin(),
				reserva.getUsuario().getNumeroTarjeta());
		System.out.println("----METODO PAGO GENERADO DESPUES-------");
		System.out.println("PAGO GENERADO"+reservaActualizar.getValorTotal());
		modelo.addAttribute("reservaActualizar",reservaActualizar);
		return "mostrarReserva";
	}
	

	

}
