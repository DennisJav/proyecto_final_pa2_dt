package ec.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.modelo.Vehiculo;
import ec.edu.repository.IVehiculoRepo;

@Service
public class VehiculoServiceImpl implements IVehiculoService{

	@Autowired
	private IVehiculoRepo vehiculoRepo;
	
	@Override
	public void insertarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.insertarVehiculo(vehiculo);
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.actualizarVehiculo(vehiculo);
	}

	@Override
	public Vehiculo buscarVehiculo(Integer id) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarVehiculo(id);
	}

	@Override
	public void borrarVehiculo(Integer id) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.borrarVehiculo(id);
	}

	@Override
	public List<Vehiculo> buscarMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarMarcaModelo(marca, modelo);
	}

	@Override
	public Vehiculo buscarVehiculoPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarVehiculoPlaca(placa);
	}

}
