package ec.edu.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ("seq_reserva"))
	@SequenceGenerator(name = "seq_reserva", sequenceName = "seq_reserva",allocationSize = 1)
	@Column(name="rese_id")
	private Integer id;
	@Column(name = "rese_fecha_inicio")
	private LocalDate fechaInicio;
	@Column(name = "rese_fecha_fin")
	private LocalDate fechaFin;
	@Column(name = "rese_estado")
	private String estado;
	@Column(name = "rese_numero_reserva")
	private String numeroReserva;
	
	//Relaciones
	@ManyToOne
	@JoinColumn(name = "vehi_id")
	private Vehiculo vehiculo;

	
	@ManyToOne
	@JoinColumn(name = "usua_id")
	private Usuario usuario;
	
	
	@OneToOne(mappedBy = "reserva",cascade=CascadeType.ALL)
	private DetalleReserva detallereserva;
	
	

	
	
	//metodos set y get
	


	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public DetalleReserva getDetalleReserva() {
		return detallereserva;
	}
	public void setDetalleReserva(DetalleReserva detallereserva) {
		this.detallereserva = detallereserva;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNumeroReserva() {
		return numeroReserva;
	}
	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}
		
	
}
