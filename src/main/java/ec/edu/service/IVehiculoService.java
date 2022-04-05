package ec.edu.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import ec.edu.modelo.Vehiculo;

public interface IVehiculoService {

	void insertarVehiculo(Vehiculo vehiculo);
	void actualizarVehiculo(Vehiculo vehiculo);
	Vehiculo buscarVehiculo(Integer id);
	void borrarVehiculo(Integer id);
	List<Vehiculo> buscarMarcaModelo(String marca, String modelo);
	Vehiculo buscarVehiculoPlaca(String placa);
	BigDecimal costoReserva(String placa, LocalDate fechaInicio, LocalDate fechaFin);
}
