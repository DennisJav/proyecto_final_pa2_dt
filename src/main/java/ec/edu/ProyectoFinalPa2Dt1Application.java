package ec.edu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.modelo.DetalleReserva;
import ec.edu.modelo.Reserva;
import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;
import ec.edu.service.IReservaService;
import ec.edu.service.IUsuarioService;
import ec.edu.service.IVehiculoService;

@SpringBootApplication
public class ProyectoFinalPa2Dt1Application implements CommandLineRunner{

	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired 
	private IUsuarioService usuarioService;

	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2Dt1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		Vehiculo v=new Vehiculo();
//		v.setMarca("Chevrolet");
//		v.setModelo("Spark");
//		v.setAniofabricacion("2015");
//		v.setAvaluo(new BigDecimal(15000));
//		v.setCilindraj("A123");
//		v.setEstado("D");
//		v.setPais("EEUU");
//		v.setValorDia(new BigDecimal(15));
//		v.setPlaca("POC5552");
		//v.setReservaVehiculo(null);
		//this.vehiculoService.insertarVehiculo(v);
//		
//		Usuario user=new Usuario();
//		user.setCedula("123");
//		user.setApellido("Ortiz");
//		user.setNombre("Dennis");
//		user.setFechaNacimiento(LocalDateTime.of(1996, Month.JANUARY, 16, 8, 0));
//		user.setGenero("M");
//		user.setRegistro("C");
		//user.setVehiculo(v);
		//this.usuarioService.insertarUsuario(user);
		//v.setId(1);
		//v.setUsuario(user);
		
//		List<Reserva> r1=new ArrayList<>();
//		Reserva r=new Reserva();
//		r.setNumeroReserva("1");
//		r.setEstado("G");
//		r.setFechaFin(LocalDate.now());
//		r.setFechaFin(LocalDate.of(2022, Month.APRIL, 10));
//		DetalleReserva d1 = new DetalleReserva();
//		d1.setValorIce(new BigDecimal(10));
//		d1.setValorSubtotal(new BigDecimal(25));
//		d1.setValorTotal(new BigDecimal(50));
//		
//		r.setDetalleReserva(d1);
//		d1.setReserva(r);
//		
//	
//		user.setId(1);
//		v.setId(1);
//		
//		user.setReservaVehiculo(r1);
//		
//		v.setReservaVehiculo(r1);
//		
//		r.setUsuario(user);
//		r.setVehiculo(v);
//		r1.add(r);
//		
//		this.usuarioService.actualizarUsuario(user);
//		
//		
//
//		
	
		
	}
	
	
	

}
