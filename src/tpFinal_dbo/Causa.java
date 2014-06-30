package tpFinal_dbo;

import java.util.ArrayList;

import org.w3c.dom.ranges.RangeException;

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
	 * @param expediente
	 * @param juzgado
	 * @param imputados
	 * @param testigos
	 * @param sentencia
	 */
	public Causa(int expediente, Juzgado juzgado, ArrayList<Persona> imputados,
			ArrayList<Persona> testigos, String sentencia) {
		super();
		this.expediente = expediente;
		this.juzgado = juzgado;
		this.imputados = imputados;
		this.testigos = testigos;
		this.sentencia = sentencia;
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
	
	public String toString() {
		String retorno = "Expediente: " + this.getExpediente() + " - " +  this.getJuzgado() + " - Imputados: " + this.getImputados().size() + " - Testigos: " + this.getTestigos().size() + " - Sentencia: " + this.getSentencia();
		if (this.getImputados().size()>0) {
			retorno = retorno.concat("\nImputados:");
			for (Persona imputado : this.getImputados()) {
				retorno = retorno.concat("\n" + imputado);
			}
		}
		
		if (this.getTestigos().size()>0) {
			retorno = retorno.concat("\nTestigos:");
			for (Persona testigo : this.getTestigos()) {
				retorno = retorno.concat("\n" + testigo);
			}
		}
		
		return (retorno);
	}
	
	public Boolean tieneTestigo(Long dni) {
		for (Persona testigo : this.getTestigos()) {
			if (testigo.getDni().equals(dni))
				return true;
		}
		return false;
	}
	
	public Boolean tieneImputado(Long dni) {
		for (Persona imputado : this.getImputados()) {
			if (imputado.getDni().equals(dni))
				return true;
		}
		return false;
	}
}
