package ec.edu.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.modelo.Vehiculo;

@Repository
@Transactional
public class VehiculoRepoImpl implements IVehiculoRepo{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public Vehiculo buscarVehiculo(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public void borrarVehiculo(Integer id) {
		// TODO Auto-generated method stub
		Vehiculo vehi=this.buscarVehiculo(id);
		this.entityManager.remove(vehi);
		
	}

	@Override
	public List<Vehiculo> buscarMarcaModelo(String marca, String modelo) {
		TypedQuery<Vehiculo> miTypedQuery = this.entityManager
				.createQuery("select v from Vehiculo v where v.marca = :valoruno AND v.modelo = :valordos",Vehiculo.class);
		miTypedQuery.setParameter("valoruno", marca);
		miTypedQuery.setParameter("valordos", modelo);

		List<Vehiculo> listaDetalles = miTypedQuery.getResultList();
		
		return listaDetalles;
	}

	@Override
	public Vehiculo buscarVehiculoPlaca(String placa) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> miTypedQuery = this.entityManager
				.createQuery("select v from Vehiculo v where v.placa = :valoruno",Vehiculo.class);
		miTypedQuery.setParameter("valoruno", placa);
		return miTypedQuery.getSingleResult();
	}

	
	
	
}
