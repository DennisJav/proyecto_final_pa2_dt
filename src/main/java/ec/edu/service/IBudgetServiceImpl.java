package ec.edu.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.modelo.DetalleReserva;
import ec.edu.modelo.Reserva;
import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;

@Service
public class IBudgetServiceImpl implements IBudgetService{
	
	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IReservaService reservaService;
	@Autowired
	private IUsuarioService usuarioService;
	
	//revisar
	public List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo) {	
		return this.vehiculoService.buscarMarcaModelo(marca, modelo);
	}


	@Override
	public Reserva realizarReserva(String placa, String cedula, LocalDate fechaInicio, LocalDate fechaFin,
			String tarjeta) {
		// TODO Auto-generated method stub
		Vehiculo vehiculoPlaca = this.vehiculoService.buscarVehiculoPlaca(placa);
		Duration tiempoReservado = Duration.between(fechaInicio, fechaFin); //duration devuelve en segundos
		long diasReservados = tiempoReservado.toDays(); //paso el tiempo en segundos a dias
		
		Usuario user = this.usuarioService.buscarUsuarioCedula(cedula);
		BigDecimal valorDiario = vehiculoPlaca.getValorDia();
		BigDecimal valorSubtotal = valorDiario.multiply(new BigDecimal(diasReservados));
		BigDecimal valorIva = valorSubtotal.multiply(new BigDecimal(0.12));
		BigDecimal valorTotal = valorSubtotal.add(valorIva);
		
		
		
		
		
		//Reserva reseBusqueda = new Reserva();
		
		
		
		
		return null;
		
	}
	
	

	
	
	
}
