package ec.edu.repository;

import ec.edu.modelo.Reserva;

public interface IReservaRepo {
	//METODOS CRUD 
	void insertarReserva(Reserva reserva);
	void actualizarReserva(Reserva reserva);
	Reserva buscarReserva(Integer id);
	void borrarReserva(Integer id);
	Reserva buscarReservaNumero(String numero);
	
	
	
	
}
