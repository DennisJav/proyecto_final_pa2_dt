package ec.edu.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ("seq_vehiculo"))
	@SequenceGenerator(name = "seq_vehiculo", sequenceName = "seq_vehiculo",allocationSize = 1)
	@Column(name="vehi_id")
	private Integer id;
	@Column(name="vehi_placa")
	private String placa;
	@Column(name="vehi_modelo")
	private String modelo;
	@Column(name="vehi_marca")
	private String marca;
	@Column(name="vehi_nio_fabricacion")
	private String aniofabricacion;
	@Column(name="vehi_estado")
	private String estado;
	@Column(name="vehi_valor_dia")
	private BigDecimal valorDia;
	@Column(name="vehi_pais_fabricacion")
	private String pais;
	@Column(name="vehi_avaluo")
	private BigDecimal avaluo;
	@Column(name="vehi_cilindraje")
	private String cilindraj;
	
	//Relaciones
	
	@OneToMany(mappedBy = "vehiculo",cascade=CascadeType.ALL) 
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
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getAniofabricacion() {
		return aniofabricacion;
	}
	public void setAniofabricacion(String aniofabricacion) {
		this.aniofabricacion = aniofabricacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getValorDia() {
		return valorDia;
	}
	public void setValorDia(BigDecimal valorDia) {
		this.valorDia = valorDia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public BigDecimal getAvaluo() {
		return avaluo;
	}
	public void setAvaluo(BigDecimal avaluo) {
		this.avaluo = avaluo;
	}
	public String getCilindraj() {
		return cilindraj;
	}
	public void setCilindraj(String cilindraj) {
		this.cilindraj = cilindraj;
	}
	
	
	
	
	
	
}
