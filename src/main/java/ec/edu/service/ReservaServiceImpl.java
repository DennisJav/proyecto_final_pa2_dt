package ec.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.modelo.Reserva;
import ec.edu.repository.IReservaRepo;

@Service
public class ReservaServiceImpl implements IReservaService{
	
	@Autowired
	private IReservaRepo reservaRepo;
	
	@Override
	public void insertarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepo.insertarReserva(reserva);
	}

	@Override
	public void actualizarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepo.actualizarReserva(reserva);
	}

	@Override
	public Reserva buscarReserva(Integer id) {
		// TODO Auto-generated method stub
		return this.reservaRepo.buscarReserva(id);
	}

	@Override
	public void borrarReserva(Integer id) {
		// TODO Auto-generated method stub
		this.reservaRepo.borrarReserva(id);
	}

	@Override
	public Reserva buscarReservaNumero(String numero) {
		// TODO Auto-generated method stub
		return this.reservaRepo.buscarReservaNumero(numero);
	}

	
}
