package ec.edu.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "detallereserva")
public class DetalleReserva {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ("seq_detalle"))
	@SequenceGenerator(name = "seq_detalle", sequenceName = "seq_detalle",allocationSize = 1)
	@Column(name="dere_id")
	private Integer id;
	@Column(name = "dere__valor_subtotal")
	private BigDecimal valorSubtotal;
	@Column(name = "dere__valor_total")
	private BigDecimal valorTotal;
	@Column(name = "dere__valor_ice")
	private BigDecimal valorIce;
	@Column(name = "fecha_reserva")
	private LocalDateTime fechaReserva;
	@Column(name = "dere_tarjeta")
	private String tarjeta;

	@OneToOne
	@JoinColumn(name = "dere_id_reserva")
	private Reserva reserva;
	
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}
	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValorIce() {
		return valorIce;
	}
	public void setValorIce(BigDecimal valorIce) {
		this.valorIce = valorIce;
	}
	
	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	
}
