package tpFinal_dbo;

import java.util.ArrayList;

public class Causa {
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
		this.sentencia = sentencia;
	}
}
