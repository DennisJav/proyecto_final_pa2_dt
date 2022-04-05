package ec.edu.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.modelo.Reserva;
import ec.edu.modelo.Vehiculo;

@Repository
@Transactional
public class ReservaRepoImpl implements IReservaRepo{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	public void actualizarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.merge(reserva);
	}

	@Override
	public Reserva buscarReserva(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, id);
	}

	@Override
	public void borrarReserva(Integer id) {
		// TODO Auto-generated method stub
		Reserva reserva = this.buscarReserva(id);
		this.entityManager.remove(reserva);
	}

	
	@Override
	public Reserva buscarReservaNumero(String numero) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> miTypedQuery = this.entityManager
				.createQuery("select r from Reserva r where r.numeroReserva = :valoruno",Reserva.class);
		miTypedQuery.setParameter("valoruno", numero);
		return miTypedQuery.getSingleResult();
	}
	
}
