package ec.edu.repository;

import java.util.List;

import ec.edu.modelo.Vehiculo;

public interface IVehiculoRepo {

	//METODOS CRUD 
	void insertarVehiculo(Vehiculo vehiculo);
	void actualizarVehiculo(Vehiculo vehiculo);
	Vehiculo buscarVehiculo(Integer id);
	void borrarVehiculo(Integer id);
	List<Vehiculo> buscarMarcaModelo(String marca, String modelo);
	Vehiculo buscarVehiculoPlaca(String placa);
}
