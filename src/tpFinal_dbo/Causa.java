package tpFinal_dbo;

import java.util.ArrayList;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Juzgado.ExcepcionValidacion;

public class Causa {
	public class ExcepcionValidacion extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 2L;

		public ExcepcionValidacion(String msg) {
	        super(msg);
	    }
	}
	int expediente;
	private Juzgado juzgado;
	ArrayList <Persona> imputados;
	ArrayList <Persona> testigos;
	private String sentencia;
	
	public Causa() {
		super();
		this.imputados = new ArrayList <Persona>();
		this.testigos = new ArrayList <Persona>();
	}

	/**
	 * @return the juzgado
	 */
	public Juzgado getJuzgado() {
		return juzgado;
	}
	/**
	 * @param juzgado the juzgado to set
	 */
	public void setJuzgado(Juzgado juzgado) {
		this.juzgado = juzgado;
	}
	/**
	 * @param numero el numero de Juzgado to set
	 * @throws ExcepcionValidacion 
	 */
	public void setJuzgado(int numero) throws ExcepcionValidacion {
		Juzgados juzgados = new Juzgados();
		Juzgado juzgado = juzgados.getJuzgadoByNumero(numero);
		
		if (juzgado == null) {
			throw new ExcepcionValidacion("No Existe un Juzgado con Numero " .concat(Integer.toString(numero)));
		}
		this.setJuzgado(juzgado);
	}
	/**
	 * @return the imputados
	 */
	public ArrayList<Persona> getImputados() {
		return imputados;
	}
	/**
	 * @param imputados the imputados to set
	 */
	public void setImputados(ArrayList<Persona> imputados) {
		this.imputados = imputados;
	}
	/**
	 * @return the testigos
	 */
	public ArrayList<Persona> getTestigos() {
		return testigos;
	}
	/**
	 * @param testigos the testigos to set
	 */
	public void setTestigos(ArrayList<Persona> testigos) {
		this.testigos = testigos;
	}
	/**
	 * @return the sentencia
	 */
	public String getSentencia() {
		return sentencia;
	}
	/**
	 * @param sentencia the sentencia to set
	 */
	public void setSentencia(String sentencia) {
		if (sentencia.length()==0)
			sentencia=null;
		this.sentencia = sentencia;
	}

	/**
	 * @return the expediente
	 */
	public int getExpediente() {
		return expediente;
	}

	/**
	 * @param expediente the expediente to set
	 * @throws ExcepcionValidacion 
	 */
	public void setExpediente(int expediente) throws ExcepcionValidacion {
		if (expediente<=0)
			throw new ExcepcionValidacion("Debe especificar un Numero de Expediente");
		if (expediente>32767)
			throw new RangeException((short) 0,"Numero de Expediente invalido");
		this.expediente = expediente;
	}
	
	/**
	 * @param dni el DNI del imputado to set
	 * @throws ExcepcionValidacion 
	 */
	public void addImputado(Long dni) throws ExcepcionValidacion {
		Personas personas = new Personas();
		Persona persona = personas.getPersonaByDNI(dni);
		
		if (persona == null) {
			throw new ExcepcionValidacion("No Existe una Persona con DNI" .concat(dni.toString()));
		}
		
		//Me fijo si ya no existe como imputado
		for (Persona imputado : this.getImputados()) {
			if (imputado.getDni().equals(dni)) {
				throw new ExcepcionValidacion("La Persona con DNI ya existe como Imputado de la causa" .concat(dni.toString()));
			}
		}
		
		this.getImputados().add(persona);
	}
	
	/**
	 * @param dni el DNI del testigo to set
	 * @throws ExcepcionValidacion 
	 */
	public void addTestigo(Long dni) throws ExcepcionValidacion {
		Personas personas = new Personas();
		Persona persona = personas.getPersonaByDNI(dni);
		
		if (persona == null) {
			throw new ExcepcionValidacion("No Existe una Persona con DNI" .concat(dni.toString()));
		}
		
		//Me fijo si ya no existe como testigo
		for (Persona testigo : this.getTestigos()) {
			if (testigo.getDni().equals(dni)) {
				throw new ExcepcionValidacion("La Persona con DNI ya existe como Testigo de la causa" .concat(dni.toString()));
			}
		}
		
		this.testigos.add(persona);
	}
}
