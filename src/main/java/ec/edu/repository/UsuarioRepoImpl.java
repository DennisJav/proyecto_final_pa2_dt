package ec.edu.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.modelo.Usuario;

@Repository
@Transactional
public class UsuarioRepoImpl implements IUsuarioRepo{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		this.entityManager.persist(usuario);
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		this.entityManager.merge(usuario);
	}

	@Override
	public Usuario buscarUsuario(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Usuario.class, id);
	}

	@Override
	public void borrarUsuario(Integer id) {
		// TODO Auto-generated method stub
		Usuario us = this.buscarUsuario(id);
		this.entityManager.remove(us);
	}

	@Override
	public Usuario buscarUsuarioCedula(String cedula) {
		TypedQuery<Usuario> miTypedQuery = this.entityManager
				.createQuery("select u from Usuario u where u.cedula = :valoruno",Usuario.class);
		miTypedQuery.setParameter("valoruno", cedula);
		return miTypedQuery.getSingleResult();
	}

	
}
