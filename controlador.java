package controller;

import java.io.Serializable;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import model.Dueño;
import model.Estudiante;
import model.Evento;
import model.Historial;
import model.Probar;
import model.SitiosInteres;
import model.Solicitud;
import model.Vivienda;
/**
 * 
 * @author Xahep Gomez - Javier Abuchaibe
 *
 */
public class controlador{
	
	private String h = "Bienvenido a ";
	private MapModel simpleModel;
	public String correo;
	public String contraseña;
	public Vivienda vivi = new Vivienda();
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private List<Solicitud> solicitudes;
	private Solicitud solicitud;

	private List<Probar> pruebas;
	private Probar prueba;

	private List<Dueño> dueños;
	private Dueño dueño;

	private List<Estudiante> estudiantes;
	private Estudiante estudiante;

	private List<Evento> eventos;
	private Evento evento;

	private List<Historial> historiales;
	private Historial historial;

	private List<SitiosInteres> sitiosInteres;
	private SitiosInteres sitioInteres;

	private List<Vivienda> viviendas;
	private Vivienda vivienda;
	
	// Constructor
	public controlador() {
		// TODO Auto-generated constructor stub
		this.emf = Persistence.createEntityManagerFactory("Proyecto_Paradigmas_AlquilaRoom");
		this.em = this.emf.createEntityManager();
		
		this.pruebas = consultarPruebas();
		this.prueba = new Probar();
		
		this.dueño = new Dueño();
		this.dueños = consultarDueños();
		
		this.estudiantes = consultarEstudiantes();	
		this.estudiante = new Estudiante();
		
		this.eventos = consultarEventos();
		this.evento = new Evento();
		
		this.viviendas = consultarViviendas();
		this.vivienda = new Vivienda();
		
		this.solicitudes = consultarSolicitudes();
		this.solicitud = new Solicitud();
		
	}

	// ------------------- METODOS CREAR-----------------------
	
	public void crearEventos_Main(Evento ev) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(ev);
			this.em.getTransaction().commit();
			System.out.println("Evento creado!");

			// Reinicio todo
			this.em = this.emf.createEntityManager();
			this.evento = new Evento();
			this.eventos = consultarEventos();
		} catch (Exception h) {
			System.out.println(h);
		}
	}

	public void crearDueño_Main(Dueño d) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(d);
			this.em.getTransaction().commit();
			System.out.println("Dueño creado!");

			// Reinicio todo
			this.em = this.emf.createEntityManager();
			this.dueño = new Dueño();
			this.dueños = consultarDueños();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	//-------------------
	
	/**
	 * En este método se crear el dueño con los datos que llegan del xhtml llamado crearDueño.
	 * @return true, para que se pueda hacer la navegación a otro xhtml.
	 */
	public Boolean crearDueño_Web() {

			this.em.getTransaction().begin();
			this.em.persist(this.dueño);
			this.em.getTransaction().commit();
			
			this.dueños.add(this.dueño);
			this.dueño = new Dueño();
			System.out.println("Dueño creado!");
			return true;
		
	}
	
	/**
	 * En este método se crear el estudiante con los datos que llegan del xhtml llamado crearEstudiante.
	 * @return true, para que se pueda hacer la navegación a otro xhtml. O false, y no navegaría a la siguiente pestaña. Solo se quedá en la pensaña en la que se encuentra.
	 */
	public Boolean crearEstudiante_Web() {
		try {
			this.em.getTransaction().begin();
			this.em.persist(this.estudiante);
			this.em.getTransaction().commit();
			
			this.estudiantes.add(this.estudiante);
			this.estudiante = new Estudiante();
			System.out.println("Estudiante creado!!");
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	
	/**
	 * En este método se crea la vivienda con los datos que el dueño ingresa en el xhtml llamada registrarVivienda. El código de la vivienda se genera manualmente. Ya que 
	 * el autoincremental no funciona. El estado tambíen se coloca manual igualmente que el dueño, el cual es el que está logueado.
	 */
	public void crearVivienda_Web() {
		try {
		
			//-------------------------------------
			Query qD = em.createQuery("Select d FROM Vivienda d");
			List v = qD.getResultList();
			 
			if(v.size() == 0) {
				this.vivienda.setCodigo_Vivienda(0+"");
			}else {
				int c = v.size()-1;
				Vivienda va = (Vivienda) v.get(c);
				int g = Integer.parseInt(va.getCodigo_Vivienda());
				int h = g+1;
				String newG = String.valueOf(h);
				System.out.println("Codigo de la vivienda = " + (newG));
				this.vivienda.setCodigo_Vivienda(newG+"");
			}
			//-------------------------------------
			System.out.println("Dueño logueado(Crear vivienda)= " + this.dueño.getCorreoDueño());
			this.vivienda.setEstado("r");
			this.vivienda.setDueño(this.dueño);
			this.em.getTransaction().begin();
			this.em.persist(this.vivienda);
			this.em.getTransaction().commit();
			
			this.viviendas.add(this.vivienda);
			this.vivienda = new Vivienda();
			System.out.println("Vivienda creada!!");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Cuando un estudiante notifica a un dueño sobre el interes por su vivienda se guarda por medio de este método. La vivienda que se guarda en la tabla solicitud es la que el estudiante 
	 * selecciona y el estudiante que se guarda es el que está logueado. El código de esta tabla tambien se genera manual, ya que no funciona el autoincremental. Y el estado
	 * de toda solicitud creada comienza en "Pendiente" 
	 * @return true, si la solicitud se crea correctamente y sigue la navegación. O false, y no navega.
	 */
	
	public Boolean crearSolicitud_web() {

		System.out.println("Estudiante logueado(Crear solicitud)= " + this.estudiante.getCorreoEstudiante());
		System.out.println("Vivienda seleccionada= " + this.vivi.getCodigo_Vivienda());
		
		try {
			//------------------------------------------------
			Query qD = em.createQuery("Select d FROM Solicitud d");
			List s = qD.getResultList();
			
			if(s.size() == 0) {
				this.solicitud.setCodigo_Solicitud(0+"");
			}else {
				int c = s.size()-1;
				Solicitud sa =  (Solicitud) s.get(c);
				int g = Integer.parseInt(sa.getCodigo_Solicitud());
				int h = g+1;
				String newG = String.valueOf(h);
				System.out.println("Codigo de la solicitud = " + (newG));
				this.solicitud.setCodigo_Solicitud(newG);
			}
			//-----------------------------------------------
			this.solicitud.setEstado("Pendiente");
			this.solicitud.setEstudiante(this.estudiante);
			this.solicitud.setVivienda(this.vivi);
			this.em.getTransaction().begin();
			this.em.persist(this.solicitud);
			this.em.getTransaction().commit();

			this.solicitudes.add(this.solicitud);
			this.solicitud = new Solicitud();
			System.out.println("Solicitud creada!!(True)");
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("Solicitud no creada(False)");
			return false;
		}
		
		
	}
	
	/**
	 * 
	 * @param vivi, se guarda en una variable global la vivienda la cual es estudiante la dio click para notificar.
	 * @return 1, para la navegación
	 */
	public int crearNotificar_Web(Vivienda vivi) {
		this.vivi = vivi;
		return 1;
	}
	
	

	

	
	// ------------------- METODOS LOGIN-----------------------
	
	/**
	 * Este metodo es para loguarse. El usuario ingresa sus datos en el xhtml y se guardan en las variables 'correo' y 'contraseña', se mira si estos datos en están en el 
	 * tabla dueño o la tabla estudiante, ya que es el mismo login para los dos.
	 * @return 0, si los datos no fueron encontrados en ninguna tabla y se le muestra en mensaje en el xhtml. 1, si en la tabla dueño se encontraron los datos y se guarda
	 * en una variable global llamada 'dueño'. 2, si los datos se encontraron en la tabla del estudiante y se guarda la sección en la variable 'esudiante'
	 */
	public int Login() {
		
		Query qD = em.createQuery("Select d FROM Dueño d WHERE d.correoDueño = :correo AND d.contraseña = :contraseña");
		qD.setParameter("correo", this.correo);
		qD.setParameter("contraseña", this.contraseña);
		
		List dLogin = qD.getResultList();
		
		Query qE = em.createQuery("Select e FROM Estudiante e WHERE e.correoEstudiante = :correo AND e.contraseña = :contraseña");
		qE.setParameter("correo", this.correo);
		qE.setParameter("contraseña", this.contraseña);
		
		List eLogin = qE.getResultList();
		
		int x = dLogin.size();
		int y = eLogin.size();
		
		if(x == 0 && y==0) {
			System.out.println("El registro no es correcto");
			return 0;
		}else if(x != 0){
			System.out.println("El dueño está registrado");
			this.dueño = (Dueño) dLogin.get(0);
			System.out.println("Se logueo el siguiente dueño: " + this.dueño.getCorreoDueño());
			return 1;
		}else {
			System.out.println("El estudiante está registrado");
			this.estudiante = (Estudiante) eLogin.get(0);
			return 2;
		}
		
		
		
	}
	
	/**
	 * Es un modelo en cual se impletenta en el mapa de Google.
	 * @return retorna marcadores con las coordenadas(lat,lng) que se están guardadas en la tabla 'vivienda' y como título se le coloca el código de la vivienda.
	 */
	public MapModel Mostrar() {
		this.viviendas = consultarViviendas();
		MapModel simpleModel= new DefaultMapModel();
		for (int i = 0; i < this.viviendas.size(); i++) {
			LatLng coord = new LatLng(this.viviendas.get(i).getLatitud(), this.viviendas.get(i).getLongitud());
			String x = String.valueOf(this.viviendas.get(i).getCodigo_Vivienda());
			simpleModel.addOverlay(new Marker(coord,x));
		}
		return simpleModel;
    }
	
	/**
	 * Cuando un dueño acepta una solicitud se le cambia el estado a "Aceptada" y se coloca el estudiante que está en la solicitud en el atributo de la vivienda llamado
	 * 'estudiante', que vendría a hacer el estudiante actual en esa vivienda.
	 * @param s, recibe la solicitud a la cual se le deben hacer los cambios.
	 */
	public void estadoSolicitudAceptar(Solicitud s) {
		this.solicitud = s;
		this.solicitud.setEstado("Aceptada");
		this.vivienda= this.solicitud.getVivienda(); 
		vivienda.setEstudiante(this.solicitud.getEstudiante());
		System.out.println("Cambiar estado aceptada!");

		this.em.getTransaction().begin();
		this.em.persist(this.vivienda);
		this.em.persist(this.solicitud);
		this.em.getTransaction().commit();
	}
	
	/**
	 * Si el dueño rechaza una solicitud se le cambia el estado a "Rechazar".
	 * @param s, recibe la solicitud a la cual se le deben hacer los cambios.
	 */
	public void estadoSolicitudRechazar(Solicitud s) {
		this.solicitud = s;
		this.solicitud.setEstado("Rechazar");
		this.em.getTransaction().begin();
		this.em.persist(this.solicitud);
		this.em.getTransaction().commit();
		System.out.println("Cambiar estado rechazar!");
	}
	
	
	/**
	 * Cuando a una persona está logueada y es de tipo dueño, cuando accede a su perfil se le muestran solo sus viviendas. Lo que hacemos es consultar a la base de datos
	 * y los resultados se almacenan en la variable 'viviendas'.
	 * @return true, para hacer la navegación y acceder al perfil.
	 */
	public Boolean consultarViviendasDueño(){

		Query qD = em.createQuery(" SELECT e FROM Vivienda e WHERE e.dueño = :dueño");
		qD.setParameter("dueño", this.dueño);
		
		List Cv = qD.getResultList();
		
		this.viviendas = Cv;
		
		for(int i =0;i<this.viviendas.size();i++) {
			System.out.println(this.viviendas.get(i).getCodigo_Vivienda());
		}
		
		return true;
		
	}
	
	
	/**
	 * Cuando un estudiante entra en su perfil, se le muestra las viviendas en las cuales ha estado. Se hace una cosulta a la base de datos y se guarda el resultado en la 
	 * variable 'viviendas'
	 * @return true, para hacer la navegación y acceder al perfil.
	 */
	public Boolean viviendasEstudiantes() {
		
		Query qD = em.createQuery(" SELECT e FROM Vivienda e WHERE e.estudiante = :estudiante");
		qD.setParameter("estudiante", this.estudiante);
		
		List Cv = qD.getResultList();
		
		this.viviendas = Cv;
		
		for(int i =0;i<this.viviendas.size();i++) {
			System.out.println(this.viviendas.get(i).getCodigo_Vivienda());
		}
		
		return true;
	}

	
	/**
	 * Este método modifica la variable 'solicitudes', y lo hace guardando las solicitudes que tienen el estado en "Pendiente", que es lo que se le muestra en la pestaña
	 * solicitudes al dueño logueado.
	 * @return true, para hacer la navegación.
	 */
	public Boolean consultarNotificacionesDueño() {

		String m = "Pendiente";
		Query qD = em.createQuery(" SELECT e FROM Solicitud e WHERE e.vivienda.dueño = :dueño AND e.estado = :estado ");
		qD.setParameter("dueño", this.dueño);
		qD.setParameter("estado", m);
		
		List Cv = qD.getResultList();
		this.solicitudes = Cv;
		for(int i =0;i<this.solicitudes.size();i++) {
			System.out.println(this.solicitudes.get(i).getCodigo_Solicitud()+" estado= "+this.solicitudes.get(i).getEstado());
		}
		
		return true;
	}
	
	/**
	 * Este método lo que hace es actualizar la variable 'viviendas' con todas las que hay disponibles, para mostrar en el mapa del estudiante.
	 * @return true, para hacer la navegación.
	 */
	public Boolean actualizarViviendas() {
		this.viviendas = consultarViviendas();
		System.out.println("true");
		return true;
	}
	
	/**
	 * Cuando un usuario, sea estudiante o dueño, cierra sesión las variables 'dueño' y 'estudiante' se crean de nuevo para estar lista cuando alguno se loguee
	 * @return true, para hacer la navegación
	 */
	public Boolean cerrarSesion() {
		this.dueño = new Dueño();
		this.estudiante = new Estudiante();
		return true;
	}
	
	
	
	
	
	
	public Boolean solicitudesDueño() {
		return true;
	}
	
	
	public Boolean irPerfil() {
		System.out.println("Ir al perfil");
		return true;
	}
	
	public Boolean irRegistroVivienda() {
		return true;
	}
	
	public Boolean irNotificaciones() {
		return true;
	}
	
	public Boolean irLogin() {
		return true;
	}
	
	public Boolean irRegistroDueño() {
		return true;
	}
	
	public Boolean irRegistroEstudiante() {
		System.out.println("buenas");
		return true;
	}
	
	public Boolean irInicioVolver() {
		return true;
	}
	
	public Boolean irNotifiaciones() {
		return true;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ------------------- METODOS CONSULTA-----------------------

		public List<Evento> consultarEventos() {
			// TODO Auto-generated method stub
			String jpql = " SELECT cat FROM Evento cat ";
			Query query = this.em.createQuery(jpql);
			List<Evento> p = query.getResultList();
			return p;
		}

		private List<Solicitud> consultarSolicitudes() {
			// TODO Auto-generated method stub
			String jpql = " SELECT s FROM Solicitud s ";
			Query q = this.em.createQuery(jpql);
			List<Solicitud> p = q.getResultList();
			return p;
		}
		
		public List<Dueño> consultarDueños() {
			// TODO Auto-generated method stub
			String jpql = " SELECT cat FROM Dueño cat ";
			Query query = this.em.createQuery(jpql);
			List<Dueño> p = query.getResultList();
			return p;
		}

		public List<Probar> consultarPruebas() {
			// TODO Auto-generated method stub
			String jpql = " SELECT cat FROM Probar cat ";
			Query query = this.em.createQuery(jpql);
			List<Probar> p = query.getResultList();
			return p;
		}
		
		public List<Estudiante> consultarEstudiantes() {
			// TODO Auto-generated method stub
			String jpql = " SELECT cat FROM Estudiante cat ";
			Query query = this.em.createQuery(jpql);
			List<Estudiante> p = query.getResultList();
			return p;
		}
		
		
		public List<Vivienda> consultarViviendas(){
			String jpql = " SELECT e FROM Vivienda e ";
			Query query = this.em.createQuery(jpql);
			List<Vivienda> p = query.getResultList();
			return p;
		}
		
		// ------------------- METODOS ELIMINAR-----------------------
		public void eliminarPrueba() {
			this.prueba = this.pruebas.get(0);
			if (this.prueba != null) {
				try {
					this.em.getTransaction().begin();
					this.em.remove(prueba);
					this.em.getTransaction().commit();
					System.out.println("Prueba eliminada");

					this.em = this.emf.createEntityManager();
					this.prueba = new Probar();
					this.pruebas = consultarPruebas();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}

		public void eliminarDueño(Dueño d) {
			System.out.println("Dueño a eliminar = " + d.toString());
			this.dueño = d;
			this.em.getTransaction().begin();
			this.em.remove(this.dueño);
			this.em.getTransaction().commit();
			// Reinicio todo
			this.em = this.emf.createEntityManager();
			this.dueño = new Dueño();
			this.dueños = consultarDueños();
		}

		public void eliminarEstudiante(Estudiante e) {
			System.out.println("Dueño a eliminar = " + e.toString());
			this.estudiante = e;
			this.em.getTransaction().begin();
			this.em.remove(this.estudiante);
			this.em.getTransaction().commit();
			// Reinicio todo
			this.estudiante = new Estudiante();
			this.estudiantes = consultarEstudiantes();
		}
		
		/**
		 * En este método se elimina la vivienda que el usuario desee, pero primero toca eliminar sus llaves foraneas, que en este caso se encuentra en la tabla 'solicitud'
		 * primero se eliminan las solicitudes que tienen la vivienda y posteriormente se elimina la vivienda.
		 * @param v, se recibe la vivienda que se desea eliminar.
		 */
		public void eliminarVivienda(Vivienda v) {
			this.vivienda = v;
			System.out.println("Eliminando vivienda");
			Query qD = em.createQuery("Select s FROM Solicitud s WHERE s.vivienda = :vivi");
			qD.setParameter("vivi", this.vivienda);
			
			List solicitudes = qD.getResultList();
			for(int i=0;i<solicitudes.size();i++) {
				eliminarSolicitud((Solicitud) solicitudes.get(i));
				System.out.println(solicitudes.get(i));
			}
			
			this.em.getTransaction().begin();
			this.em.remove(this.vivienda);
			this.em.getTransaction().commit();
			this.em = this.emf.createEntityManager();
			this.vivienda = new Vivienda();
			consultarViviendasDueño();
			
		}
		
		public void eliminarSolicitud(Solicitud s) {
			this.solicitud = s;
			this.em.getTransaction().begin();
			this.em.remove(this.solicitud);
			this.em.getTransaction().commit();
			//Reiniciar
			this.solicitud = new Solicitud();
			this.solicitudes = consultarSolicitudes();
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ------------------- METODOS GET Y SET-----------------------

	public List<Probar> getPruebas() {
		return pruebas;
	}

	public void setPruebas(List<Probar> pruebas) {
		this.pruebas = pruebas;
	}

	public Probar getPrueba() {
		return prueba;
	}

	public void setPrueba(Probar prueba) {
		this.prueba = prueba;
	}

	public List<Dueño> getDueños() {
		return dueños;
	}

	public void setDueños(List<Dueño> dueños) {
		this.dueños = dueños;
	}

	public Dueño getDueño() {
		return dueño;
	}

	public void setDueño(Dueño dueño) {
		this.dueño = dueño;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Historial> getHistoriales() {
		return historiales;
	}

	public void setHistoriales(List<Historial> historiales) {
		this.historiales = historiales;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	public List<SitiosInteres> getSitiosInteres() {
		return sitiosInteres;
	}

	public void setSitiosInteres(List<SitiosInteres> sitiosInteres) {
		this.sitiosInteres = sitiosInteres;
	}

	public SitiosInteres getSitioInteres() {
		return sitioInteres;
	}

	public void setSitioInteres(SitiosInteres sitioInteres) {
		this.sitioInteres = sitioInteres;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public List<Vivienda> getViviendas() {
		return viviendas;
	}

	public void setViviendas(List<Vivienda> viviendas) {
		this.viviendas = viviendas;
	}

	public Vivienda getVivienda() {
		return vivienda;
	}

	public void setVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}
		
	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public MapModel getSimpleModel() {
        return simpleModel;
    }

	
	

}
