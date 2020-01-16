package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estudiante database table.
 * 
 */
@Entity
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="correo_estudiante")
	private String correoEstudiante;

	private int contacto;

	private String contraseña;

	private String edad;

	private String genero;

	private String idioma;

	private String pais;

	private String username;

	//bi-directional many-to-one association to AsistenciaEvento
	@OneToMany(mappedBy="estudiante")
	private List<AsistenciaEvento> asistenciaEventos;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="estudiante")
	private List<Evento> eventos;

	//bi-directional many-to-one association to Historial
	@OneToMany(mappedBy="estudiante")
	private List<Historial> historials;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="estudiante")
	private List<Solicitud> solicituds;

	//bi-directional many-to-one association to Vivienda
	@OneToMany(mappedBy="estudiante")
	private List<Vivienda> viviendas;

	public Estudiante() {
	}

	public String getCorreoEstudiante() {
		return this.correoEstudiante;
	}

	public void setCorreoEstudiante(String correoEstudiante) {
		this.correoEstudiante = correoEstudiante;
	}

	public int getContacto() {
		return this.contacto;
	}

	public void setContacto(int contacto) {
		this.contacto = contacto;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEdad() {
		return this.edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AsistenciaEvento> getAsistenciaEventos() {
		return this.asistenciaEventos;
	}

	public void setAsistenciaEventos(List<AsistenciaEvento> asistenciaEventos) {
		this.asistenciaEventos = asistenciaEventos;
	}

	public AsistenciaEvento addAsistenciaEvento(AsistenciaEvento asistenciaEvento) {
		getAsistenciaEventos().add(asistenciaEvento);
		asistenciaEvento.setEstudiante(this);

		return asistenciaEvento;
	}

	public AsistenciaEvento removeAsistenciaEvento(AsistenciaEvento asistenciaEvento) {
		getAsistenciaEventos().remove(asistenciaEvento);
		asistenciaEvento.setEstudiante(null);

		return asistenciaEvento;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setEstudiante(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setEstudiante(null);

		return evento;
	}

	public List<Historial> getHistorials() {
		return this.historials;
	}

	public void setHistorials(List<Historial> historials) {
		this.historials = historials;
	}

	public Historial addHistorial(Historial historial) {
		getHistorials().add(historial);
		historial.setEstudiante(this);

		return historial;
	}

	public Historial removeHistorial(Historial historial) {
		getHistorials().remove(historial);
		historial.setEstudiante(null);

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
		solicitud.setEstudiante(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setEstudiante(null);

		return solicitud;
	}

	public List<Vivienda> getViviendas() {
		return this.viviendas;
	}

	public void setViviendas(List<Vivienda> viviendas) {
		this.viviendas = viviendas;
	}

	public Vivienda addVivienda(Vivienda vivienda) {
		getViviendas().add(vivienda);
		vivienda.setEstudiante(this);

		return vivienda;
	}

	public Vivienda removeVivienda(Vivienda vivienda) {
		getViviendas().remove(vivienda);
		vivienda.setEstudiante(null);

		return vivienda;
	}

}