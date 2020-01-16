package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the due�o database table.
 * 
 */
@Entity
@NamedQuery(name="Due�o.findAll", query="SELECT d FROM Due�o d")
public class Due�o implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="correo_due�o")
	private String correoDue�o;

	private int contacto;

	private String contrase�a;

	private String idioma;

	private String username;

	//bi-directional many-to-one association to Vivienda
	@OneToMany(mappedBy="due�o")
	private List<Vivienda> viviendas;

	public Due�o() {
	}

	public String getCorreoDue�o() {
		return this.correoDue�o;
	}

	public void setCorreoDue�o(String correoDue�o) {
		this.correoDue�o = correoDue�o;
	}

	public int getContacto() {
		return this.contacto;
	}

	public void setContacto(int contacto) {
		this.contacto = contacto;
	}

	public String getContrase�a() {
		return this.contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
		vivienda.setDue�o(this);

		return vivienda;
	}

	public Vivienda removeVivienda(Vivienda vivienda) {
		getViviendas().remove(vivienda);
		vivienda.setDue�o(null);

		return vivienda;
	}

}