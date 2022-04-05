package ec.edu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import ec.edu.modelo.Reserva;
import ec.edu.modelo.Vehiculo;

public interface IBudgetService {
	Reserva realizarReserva(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tarjeta);
	boolean fechasNoDisponibles(LocalDateTime fechaInicioReservada, LocalDateTime fechaFinReservada, LocalDateTime fechaInicioVerificar,
			LocalDateTime fechaFinVerificar);
	Reserva retirarVehiculoReserva(String numeroReserva);
	
}
