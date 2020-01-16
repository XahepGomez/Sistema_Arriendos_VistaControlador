package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sitios_interes database table.
 * 
 */
@Entity
@Table(name="sitios_interes")
@NamedQuery(name="SitiosInteres.findAll", query="SELECT s FROM SitiosInteres s")
public class SitiosInteres implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo_Interes;

	private String descripcion_Interes;

	private int latitud_Interes;

	private int longitud_Interes;

	private String nombre_Interes;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="sitiosIntere")
	private List<Evento> eventos;

	public SitiosInteres() {
	}

	public int getCodigo_Interes() {
		return this.codigo_Interes;
	}

	public void setCodigo_Interes(int codigo_Interes) {
		this.codigo_Interes = codigo_Interes;
	}

	public String getDescripcion_Interes() {
		return this.descripcion_Interes;
	}

	public void setDescripcion_Interes(String descripcion_Interes) {
		this.descripcion_Interes = descripcion_Interes;
	}

	public int getLatitud_Interes() {
		return this.latitud_Interes;
	}

	public void setLatitud_Interes(int latitud_Interes) {
		this.latitud_Interes = latitud_Interes;
	}

	public int getLongitud_Interes() {
		return this.longitud_Interes;
	}

	public void setLongitud_Interes(int longitud_Interes) {
		this.longitud_Interes = longitud_Interes;
	}

	public String getNombre_Interes() {
		return this.nombre_Interes;
	}

	public void setNombre_Interes(String nombre_Interes) {
		this.nombre_Interes = nombre_Interes;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setSitiosIntere(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setSitiosIntere(null);

		return evento;
	}

}