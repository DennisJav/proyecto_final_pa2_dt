package ec.edu.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ("seq_usuario"))
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario",allocationSize = 1)
	@Column(name="usua_id")
	private Integer id;
	@Column(name = "usua_cedula")
	private String cedula;
	@Column(name = "usua_nombre")
	private String nombre;
	@Column(name = "usua_apellido")
	private String apellido;
	@Column(name = "usua_fecha_nacimiento")
	private LocalDate fechaNacimiento;
	@Column(name = "usua_genero")
	private String genero;
	@Column(name = "usua_registro")
	private String registro;
	@Column(name = "usua_tarjeta")
	private String numeroTarjeta;
	@Column(name = "usua_pasword")
	private String pasword;
	
	

	@OneToMany(mappedBy = "usuario",cascade=CascadeType.ALL) 
	private List<Reserva> reservaVehiculo;
	
	public List<Reserva> getReservaVehiculo() {
		return reservaVehiculo;
	}
	public void setReservaVehiculo(List<Reserva> reservaVehiculo) {
		this.reservaVehiculo = reservaVehiculo;
	}
	
	
	//Metodos set y get 
	
	
	
	
	public Integer getId() {
		return id;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	
	
	
	
	
}
