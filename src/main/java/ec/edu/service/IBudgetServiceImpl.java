package ec.edu.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.time.temporal.ChronoUnit.DAYS;

import ec.edu.modelo.DetalleReserva;
import ec.edu.modelo.Reserva;
import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;

@Service
public class IBudgetServiceImpl implements IBudgetService{
	
	 static final Logger LOG = Logger.getLogger(IBudgetServiceImpl.class);
	
	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IReservaService reservaService;
	@Autowired
	private IUsuarioService usuarioService;
	
	//revisar



	@Override
	@Transactional
	public Reserva realizarReserva(String placa, String cedula, LocalDate fechaInicio, LocalDate fechaFin,
			String tarjeta) {
		// TODO Auto-generated method stub
		Vehiculo vehiculoPlaca = this.vehiculoService.buscarVehiculoPlaca(placa);
		long diasReservados = DAYS.between(fechaInicio, fechaFin); //paso el tiempo en segundos a dias
		Usuario user = this.usuarioService.buscarUsuarioCedula(cedula);
		
		//Calculo de valores para reserva
		BigDecimal valorDiario = vehiculoPlaca.getValorDia();
		BigDecimal valorSubtotal = valorDiario.multiply(new BigDecimal(diasReservados));
		BigDecimal valorICE = valorSubtotal.multiply(new BigDecimal(0.15));
		BigDecimal valorTotal = valorSubtotal.add(valorICE);
		
		List<Reserva> reservaCliente = user.getReservaVehiculo();
		if(reservaCliente == null) {
			reservaCliente = new ArrayList<>();
		}
		Reserva reserva = new Reserva();
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(fechaFin);
		reserva.setEstado("G");
		reserva.setUsuario(user);
		reserva.setVehiculo(vehiculoPlaca);
		reserva.setNumeroReserva(reserva.getId().toString());
		this.reservaService.insertarReserva(reserva);
	
		List<Reserva> reservaVehiculo = vehiculoPlaca.getReservaVehiculo();
		if(reservaVehiculo==null) {
			reservaVehiculo=new ArrayList<>();
		}
		reservaVehiculo.add(reserva);
		vehiculoPlaca.setReservaVehiculo(reservaVehiculo);
		this.vehiculoService.actualizarVehiculo(vehiculoPlaca);
		
		reservaCliente.add(reserva);
		user.setReservaVehiculo(reservaCliente);
		user.setNumeroTarjeta(tarjeta);
		this.usuarioService.actualizarUsuario(user);
		
		
		
		DetalleReserva pago = new DetalleReserva();
		pago.setFechaReserva(LocalDateTime.now());
		pago.setReserva(reserva);
		pago.setTarjeta(tarjeta);
		pago.setValorIce(valorICE);
		pago.setValorSubtotal(valorSubtotal);
		pago.setValorTotal(valorTotal);
		
		reserva.setDetalleReserva(pago);
		
		reserva.setNumeroReserva(reserva.getId().toString());
		this.reservaService.actualizarReserva(reserva);

		return reserva;
		
	}
	
	
	@Override
	public boolean fechasNoDisponibles(LocalDate fechaInicioReservada, LocalDate fechaFinReservada, LocalDate fechaInicioVerificar,
			LocalDate fechaFinVerificar) {
		
		Period fecha = Period.between(fechaFinReservada, fechaInicioVerificar);
		if (fecha.isZero()) {
			return true;
		} else if (fechaInicioVerificar.isAfter(fechaFinReservada) && fechaFinVerificar.isAfter(fechaInicioVerificar)) {
			return false;
		} else if (fechaInicioVerificar.isBefore(fechaInicioReservada) && fechaFinVerificar.isBefore(fechaInicioVerificar)) {
			return false;
		} else {
			return true;
		}
		
	}
	
	


	
	
}
