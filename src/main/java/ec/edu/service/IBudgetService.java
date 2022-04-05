package ec.edu.service;

import java.time.LocalDate;
import java.util.List;

import ec.edu.modelo.Reserva;
import ec.edu.modelo.Vehiculo;

public interface IBudgetService {
	Reserva realizarReserva(String placa, String cedula, LocalDate fechaInicio, LocalDate fechaFin, String tarjeta);
	boolean fechasNoDisponibles(LocalDate fechaInicioReservada, LocalDate fechaFinReservada, LocalDate fechaInicioVerificar,
			LocalDate fechaFinVerificar);
}
