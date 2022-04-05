package ec.edu.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	public Reserva realizarReserva(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			String tarjeta) {
		System.out.println("ENTRO AL METODO");
		
		// TODO Auto-generated method stub
		Vehiculo vehiculoPlaca = this.vehiculoService.buscarVehiculoPlaca(placa);
		System.out.println("placa"+vehiculoPlaca.getPlaca());
		long diasReservados = DAYS.between(fechaInicio, fechaFin); //paso el tiempo en segundos a dias
		System.out.println("dias "+diasReservados);
		Usuario user = this.usuarioService.buscarUsuarioCedula(cedula);
		System.out.println("Cedula "+user.getCedula());
		//Calculo de valores para reserva
		
		BigDecimal valorDiario = vehiculoPlaca.getValorDia();
		BigDecimal valorSubtotal = valorDiario.multiply(new BigDecimal(diasReservados));
		BigDecimal valorICE = valorSubtotal.multiply(new BigDecimal(0.15)).setScale(3,RoundingMode.UP);
		BigDecimal valorTotal = valorSubtotal.add(valorICE).setScale(3,RoundingMode.UP);
		System.out.println("Valor total: "+valorTotal);
		
		List<Reserva> reservaCliente = user.getReservaVehiculo();
		if(reservaCliente == null) {
			reservaCliente = new ArrayList<>();
		}
		Reserva reserva = new Reserva();
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(fechaFin);
		reserva.setEstado("Generado");
		reserva.setUsuario(user);
		reserva.setVehiculo(vehiculoPlaca);
		reserva.setValorIce(valorICE);
		reserva.setValorSubtotal(valorSubtotal);
		reserva.setValorTotal(valorTotal);
		
		String s = String.valueOf((int)(Math.random()*500)+100);
		reserva.setNumeroReserva(s);

		System.out.println("Metodo MEDIO");
		
		List<Reserva> reservaVehiculo = vehiculoPlaca.getReservaVehiculo();
		if(reservaVehiculo==null) {
			reservaVehiculo=new ArrayList<>();
		}
		reservaVehiculo.add(reserva);
		vehiculoPlaca.setReservaVehiculo(reservaVehiculo);
		vehiculoPlaca.setEstado("No Disponible");
		
		reservaCliente.add(reserva);
		user.setReservaVehiculo(reservaCliente);
		user.setNumeroTarjeta(tarjeta);
		
		
		System.out.println("METODOS");
		
		DetalleReserva pago = new DetalleReserva();
		pago.setFechaReserva(LocalDateTime.now());
		pago.setReserva(reserva);
		pago.setTarjeta(tarjeta);
	
		System.out.println("YA MISMO");
		
		reserva.setDetalleReserva(pago);
		
		System.out.println("YA MISMO DOS");
		
		
		System.out.println("DESPUES DEL M");
		
		System.out.println("antes de actualizarles");
		
		this.reservaService.insertarReserva(reserva);

		
		
		System.out.println("Resera");
		System.out.println("antes de salir");
		return reserva;
		
	}
	
	
	@Override
	public boolean fechasNoDisponibles(LocalDateTime fechaInicioReservada, LocalDateTime fechaFinReservada, LocalDateTime fechaInicioVerificar,
			LocalDateTime fechaFinVerificar) {

		
		if (fechaInicioReservada.isEqual(fechaInicioVerificar)) {
			return true;
		} else if (fechaInicioVerificar.isAfter(fechaInicioReservada) && fechaInicioVerificar.isBefore(fechaFinReservada)) {
			return true;
		} else if (fechaFinVerificar.isAfter(fechaInicioReservada) && fechaFinVerificar.isBefore(fechaFinReservada)) {
			return true;
		} else {
			return false;
		}
		
	}


	@Override
	public Reserva retirarVehiculoReserva(String numeroReserva) {
		// TODO Auto-generated method stub
		Reserva reserva = this.reservaService.buscarReservaNumero(numeroReserva);
		reserva.setEstado("Ejecutada");
		this.reservaService.actualizarReserva(reserva);
		
		Vehiculo vehiculo = reserva.getVehiculo();
		vehiculo.setEstado("Indisponible");
		this.vehiculoService.actualizarVehiculo(vehiculo);
		
		
		return reserva;
	}
	
	
	

	
}
