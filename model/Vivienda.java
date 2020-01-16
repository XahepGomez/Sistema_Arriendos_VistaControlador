package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vivienda database table.
 * 
 */
@Entity
@NamedQuery(name="Vivienda.findAll", query="SELECT v FROM Vivienda v")
public class Vivienda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo_Vivienda;

	private String descripcion;

	@Column(name="direccion_exacta")
	private String direccionExacta;

	private String estado;

	private float latitud;

	private float longitud;

	@Column(name="metodo_pago")
	private String metodoPago;

	private int precio;

	private String reglas;

	//bi-directional many-to-one association to Historial
	@OneToMany(mappedBy="vivienda")
	private List<Historial> historials;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="vivienda")
	private List<Solicitud> solicituds;

	//bi-directional many-to-one association to Dueño
	@ManyToOne
	@JoinColumn(name="correo_dueño")
	private Dueño dueño;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="correo_estudiante_actual")
	private Estudiante estudiante;

	public Vivienda() {
	}

	public String getCodigo_Vivienda() {
		return this.codigo_Vivienda;
	}

	public void setCodigo_Vivienda(String codigo_Vivienda) {
		this.codigo_Vivienda = codigo_Vivienda;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccionExacta() {
		return this.direccionExacta;
	}

	public void setDireccionExacta(String direccionExacta) {
		this.direccionExacta = direccionExacta;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public float getLatitud() {
		return this.latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return this.longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getMetodoPago() {
		return this.metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getReglas() {
		return this.reglas;
	}

	public void setReglas(String reglas) {
		this.reglas = reglas;
	}

	public List<Historial> getHistorials() {
		return this.historials;
	}

	public void setHistorials(List<Historial> historials) {
		this.historials = historials;
	}

	public Historial addHistorial(Historial historial) {
		getHistorials().add(historial);
		historial.setVivienda(this);

		return historial;
	}

	public Historial removeHistorial(Historial historial) {
		getHistorials().remove(historial);
		historial.setVivienda(null);

		return historial;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setVivienda(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setVivienda(null);

		return solicitud;
	}

	public Dueño getDueño() {
		return this.dueño;
	}

	public void setDueño(Dueño dueño) {
		this.dueño = dueño;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}