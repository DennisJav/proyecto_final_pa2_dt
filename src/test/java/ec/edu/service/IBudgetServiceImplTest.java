package ec.edu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import ec.edu.modelo.Reserva;
import ec.edu.modelo.Usuario;
import ec.edu.modelo.Vehiculo;



@SpringBootTest
@Transactional
@Rollback(true)
class IBudgetServiceImplTest {

	@Autowired
	private IVehiculoService vehiculoService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IReservaService reservaService;
	@Autowired
	private IBudgetService budgetService;
	
	
	@Test
	void testBuscarVehiculosDisponibles() {
		//fail("Not yet implemented");
	}

	@Test
	void insertarVehiculos() {
		//fail("Not yet implemented");
		Vehiculo v=new Vehiculo();
		v.setMarca("Chevrolet");
		v.setModelo("Spark");
		v.setAniofabricacion("2015");
		v.setAvaluo(new BigDecimal(15000));
		v.setCilindraj("A123");
		v.setEstado("D");
		v.setPais("EEUU");
		v.setValorDia(new BigDecimal(15));
		v.setPlaca("1222");
		this.vehiculoService.insertarVehiculo(v);
		Vehiculo p=this.vehiculoService.buscarVehiculoPlaca(v.getPlaca());
		assertEquals(v.getPlaca(), p.getPlaca());	
	}
	
	@Test
	void buscarMarca() {
		assertThat(this.vehiculoService.buscarMarcaModelo("spark", "spark")).isNotEmpty();
	}
	
	@Test
	void buscarPlaca() {
		assertThat(this.vehiculoService.buscarVehiculoPlaca("poc4")).isNotNull();
	}
	
	@Test
	void buscarcedula() {
		assertThat(this.usuarioService.buscarUsuarioCedula("qw")).isNotNull();
	}
	
	@Test
	void actualizarUsurio() {
		assertThat(this.usuarioService.buscarUsuario(1));
	}
	
	@Test
	void eliminarUsuario() {
		Usuario u = new Usuario();
		u.setApellido("a");
		u.setGenero("F");
		this.usuarioService.insertarUsuario(u);
		Usuario n = this.usuarioService.buscarUsuario(u.getId());
		assertEquals(u,n);
	}
	
	@Test
	void valorTotal() {
		Vehiculo v= this.vehiculoService.buscarVehiculo(5);
		Reserva r = this.reservaService.buscarReserva(76);
		BigDecimal r1 = this.vehiculoService.costoReserva("poc4", r.getFechaInicio(), r.getFechaFin());
		assertNotNull(r1);
		
	}
	
	@Test 
	void reserva() {
		Reserva r = this.reservaService.buscarReserva(76);
		assertNotNull(r);
	}
	
	@Test 
	void realizaReserva() {
		Reserva r=new Reserva();
		r.setFechaFin(LocalDateTime.now());
		r.setFechaInicio(LocalDateTime.now());
		boolean t = this.budgetService.fechasNoDisponibles(r.getFechaFin(), r.getFechaInicio(), r.getFechaFin(), r.getFechaInicio());
		assertTrue(t);
		
	}
	
	@Test
	void buscarVehiculosDisponibles() {
	    List<Vehiculo> vehiculos = this.vehiculoService.buscarMarcaModelo("spark", "spark");
	    assertThat(vehiculos).isNotEmpty();
	
	}
	@Test
	void buscarVehiculosDisponiblesDos() {
	    List<Vehiculo> vehiculos = this.vehiculoService.buscarMarcaModelo("spark", "nissan");
	    assertThat(vehiculos).isEmpty();
	
	}
	
}
