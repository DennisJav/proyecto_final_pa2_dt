package ec.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.modelo.Usuario;
import ec.edu.repository.IUsuarioRepo;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Override
	public void insertarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		this.usuarioRepo.insertarUsuario(usuario);
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		this.usuarioRepo.actualizarUsuario(usuario);
	}

	@Override
	public Usuario buscarUsuario(Integer id) {
		// TODO Auto-generated method stub
		return this.usuarioRepo.buscarUsuario(id);
	}

	@Override
	public void borrarUsuario(Integer id) {
		// TODO Auto-generated method stub
		this.usuarioRepo.borrarUsuario(id);
	}

	@Override
	public Usuario buscarUsuarioCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.usuarioRepo.buscarUsuarioCedula(cedula);
	}

}
