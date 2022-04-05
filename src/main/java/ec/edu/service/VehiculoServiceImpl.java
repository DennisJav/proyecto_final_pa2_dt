package ec.edu.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.time.temporal.ChronoUnit.DAYS;

import ec.edu.modelo.Usuario;
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

	@Override
	public BigDecimal costoReserva(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		
		Vehiculo vehiculoCalcular = this.buscarVehiculoPlaca(placa);
		long dias = DAYS.between(fechaInicio, fechaFin);
		
		BigDecimal valorDiario = vehiculoCalcular.getValorDia();
		BigDecimal valorSubtotal = valorDiario.multiply(new BigDecimal(dias));
		BigDecimal valorICE = valorSubtotal.multiply(new BigDecimal(0.15));
		BigDecimal valorTotal = valorSubtotal.add(valorICE);
		
		
		return valorTotal;
	}

}
