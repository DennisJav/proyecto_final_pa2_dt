package ec.edu.service;

import ec.edu.modelo.Usuario;

public interface IUsuarioService {

	void insertarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario);
	Usuario buscarUsuario(Integer id);
	void borrarUsuario(Integer id);
	Usuario buscarUsuarioCedula(String cedula);
}
