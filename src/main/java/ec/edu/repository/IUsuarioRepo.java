package ec.edu.repository;

import ec.edu.modelo.Usuario;

public interface IUsuarioRepo {
	
	//METODOS CRUD 
	void insertarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario);
	Usuario buscarUsuario(Integer id);
	void borrarUsuario(Integer id);
	Usuario buscarUsuarioCedula(String cedula);
	
}
