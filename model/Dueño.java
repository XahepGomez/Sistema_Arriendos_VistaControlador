package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dueño database table.
 * 
 */
@Entity
@NamedQuery(name="Dueño.findAll", query="SELECT d FROM Dueño d")
public class Dueño implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="correo_dueño")
	private String correoDueño;

	private int contacto;

	private String contraseña;

	private String idioma;

	private String username;

	//bi-directional many-to-one association to Vivienda
	@OneToMany(mappedBy="dueño")
	private List<Vivienda> viviendas;

	public Dueño() {
	}

	public String getCorreoDueño() {
		return this.correoDueño;
	}

	public void setCorreoDueño(String correoDueño) {
		this.correoDueño = correoDueño;
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

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Vivienda> getViviendas() {
		return this.viviendas;
	}

	public void setViviendas(List<Vivienda> viviendas) {
		this.viviendas = viviendas;
	}

	public Vivienda addVivienda(Vivienda vivienda) {
		getViviendas().add(vivienda);
		vivienda.setDueño(this);

		return vivienda;
	}

	public Vivienda removeVivienda(Vivienda vivienda) {
		getViviendas().remove(vivienda);
		vivienda.setDueño(null);

		return vivienda;
	}

}