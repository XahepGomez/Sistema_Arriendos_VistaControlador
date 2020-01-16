package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the evento database table.
 * 
 */
@Entity
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_evento")
	private int codigoEvento;

	@Column(name="abierta_publico")
	private String abiertaPublico;

	@Column(name="descripcion_evento")
	private String descripcionEvento;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Time hora;

	@Column(name="nombre_evento")
	private String nombreEvento;

	//bi-directional many-to-one association to AsistenciaEvento
	@OneToMany(mappedBy="evento")
	private List<AsistenciaEvento> asistenciaEventos;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="correo_estudiante_dueño")
	private Estudiante estudiante;

	//bi-directional many-to-one association to SitiosInteres
	@ManyToOne
	@JoinColumn(name="codigo_Interes")
	private SitiosInteres sitiosIntere;

	public Evento() {
	}

	public int getCodigoEvento() {
		return this.codigoEvento;
	}

	public void setCodigoEvento(int codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getAbiertaPublico() {
		return this.abiertaPublico;
	}

	public void setAbiertaPublico(String abiertaPublico) {
		this.abiertaPublico = abiertaPublico;
	}

	public String getDescripcionEvento() {
		return this.descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getNombreEvento() {
		return this.nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public List<AsistenciaEvento> getAsistenciaEventos() {
		return this.asistenciaEventos;
	}

	public void setAsistenciaEventos(List<AsistenciaEvento> asistenciaEventos) {
		this.asistenciaEventos = asistenciaEventos;
	}

	public AsistenciaEvento addAsistenciaEvento(AsistenciaEvento asistenciaEvento) {
		getAsistenciaEventos().add(asistenciaEvento);
		asistenciaEvento.setEvento(this);

		return asistenciaEvento;
	}

	public AsistenciaEvento removeAsistenciaEvento(AsistenciaEvento asistenciaEvento) {
		getAsistenciaEventos().remove(asistenciaEvento);
		asistenciaEvento.setEvento(null);

		return asistenciaEvento;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public SitiosInteres getSitiosIntere() {
		return this.sitiosIntere;
	}

	public void setSitiosIntere(SitiosInteres sitiosIntere) {
		this.sitiosIntere = sitiosIntere;
	}

}