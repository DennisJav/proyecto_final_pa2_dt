package ec.edu.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public Reserva realizarReserva(String placa, String cedula, LocalDate fechaInicio, LocalDate fechaFin,
			String tarjeta) {
		// TODO Auto-generated method stub
		Vehiculo vehiculoPlaca = this.vehiculoService.buscarVehiculoPlaca(placa);
		Duration tiempoReservado = Duration.between(fechaInicio, fechaFin); //duration devuelve en segundos
		long diasReservados = tiempoReservado.toDays(); //paso el tiempo en segundos a dias
		Usuario user = this.usuarioService.buscarUsuarioCedula(cedula);
		
		//Calculo de valores para reserva
		BigDecimal valorDiario = vehiculoPlaca.getValorDia();
		BigDecimal valorSubtotal = valorDiario.multiply(new BigDecimal(diasReservados));
		BigDecimal valorICE = valorSubtotal.multiply(new BigDecimal(0.15));
		BigDecimal valorTotal = valorSubtotal.add(valorICE);
		
		List<Reserva> reservaCliente = user.getReservaVehiculo();
		Reserva reserva = new Reserva();
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(fechaFin);
		reserva.setEstado("G");
		reserva.setUsuario(user);
		reserva.setVehiculo(vehiculoPlaca);
		reserva.setNumeroReserva(reserva.getId().toString());
		
		DetalleReserva pago = new DetalleReserva();
		pago.setFechaReserva(LocalDateTime.now());
		pago.setReserva(reserva);
		pago.setTarjeta(tarjeta);
		pago.setValorIce(valorICE);
		pago.setValorSubtotal(valorSubtotal);
		pago.setValorTotal(valorTotal);
		reserva.setDetalleReserva(pago);
		
		
		List<Reserva> reservaVehiculo = vehiculoPlaca.getReservaVehiculo();
		reservaVehiculo.add(reserva);
		vehiculoPlaca.setReservaVehiculo(reservaVehiculo);
		 
		//Cambio estado a no disponible
		vehiculoPlaca.setEstado("ND");
		
		
		this.vehiculoService.actualizarVehiculo(vehiculoPlaca);
		
		reservaCliente.add(reserva);
		user.setReservaVehiculo(reservaCliente);
		this.usuarioService.actualizarUsuario(user);
		
		this.reservaService.insertarReserva(reserva);

		
		return reserva;
		
	}	
	
	

	
	
	
}
