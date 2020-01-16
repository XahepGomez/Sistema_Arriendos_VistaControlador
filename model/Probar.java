package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the probar database table.
 * 
 */
@Entity
@NamedQuery(name="Probar.findAll", query="SELECT p FROM Probar p")
public class Probar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="correo_prueba")
	private String correoPrueba;

	private String contraseña;

	@Column(name="nombre_probar")
	private String nombreProbar;

	public Probar() {
	}

	public String getCorreoPrueba() {
		return this.correoPrueba;
	}

	public void setCorreoPrueba(String correoPrueba) {
		this.correoPrueba = correoPrueba;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombreProbar() {
		return this.nombreProbar;
	}

	public void setNombreProbar(String nombreProbar) {
		this.nombreProbar = nombreProbar;
	}

}