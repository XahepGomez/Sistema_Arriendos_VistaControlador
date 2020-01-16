package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the solicitud database table.
 * 
 */
@Entity
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo_Solicitud;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_entrada")
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_salida")
	private Date fechaSalida;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="correo_estudiante")
	private Estudiante estudiante;

	//bi-directional many-to-one association to Vivienda
	@ManyToOne
	@JoinColumn(name="codigo_Vivienda")
	private Vivienda vivienda;

	public Solicitud() {
	}

	public String getCodigo_Solicitud() {
		return this.codigo_Solicitud;
	}

	public void setCodigo_Solicitud(String codigo_Solicitud) {
		this.codigo_Solicitud = codigo_Solicitud;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Vivienda getVivienda() {
		return this.vivienda;
	}

	public void setVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}

}