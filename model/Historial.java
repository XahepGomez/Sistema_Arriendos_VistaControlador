package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historial database table.
 * 
 */
@Entity
@NamedQuery(name="Historial.findAll", query="SELECT h FROM Historial h")
public class Historial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_historial")
	private int codigoHistorial;

	@Temporal(TemporalType.DATE)
	private Date fecha_Estadia;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="correo_estudiante")
	private Estudiante estudiante;

	//bi-directional many-to-one association to Vivienda
	@ManyToOne
	@JoinColumn(name="codigo_Vivienda")
	private Vivienda vivienda;

	public Historial() {
	}

	public int getCodigoHistorial() {
		return this.codigoHistorial;
	}

	public void setCodigoHistorial(int codigoHistorial) {
		this.codigoHistorial = codigoHistorial;
	}

	public Date getFecha_Estadia() {
		return this.fecha_Estadia;
	}

	public void setFecha_Estadia(Date fecha_Estadia) {
		this.fecha_Estadia = fecha_Estadia;
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