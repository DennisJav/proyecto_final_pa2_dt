package ec.edu.service;


import ec.edu.modelo.Reserva;

public interface IReservaService {

	void insertarReserva(Reserva reserva);
	void actualizarReserva(Reserva reserva);
	Reserva buscarReserva(Integer id);
	void borrarReserva(Integer id);
	
}
