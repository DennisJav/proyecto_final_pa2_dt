package ec.edu.repository;

import java.util.List;

import ec.edu.modelo.Usuario;

public interface IUsuarioRepo {
	
	//METODOS CRUD 
	void insertarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario);
	Usuario buscarUsuario(Integer id);
	void borrarUsuario(Integer id);
	Usuario buscarUsuarioCedula(String cedula);
	List<Usuario> buscarUsuarioCedulaTodos(String cedula);
	
	
}
