package tpFinal_dbo;

public class Juzgado {
	enum Fuero {civil, comercial, laboral, penal}
	
	private Fuero fuero;
	private Juez juez;
	private String domicilio;
	private String localidad;
	/**
	 * @return the fuero
	 */
	public Fuero getFuero() {
		return fuero;
	}
	/**
	 * @param fuero the fuero to set
	 */
	public void setFuero(Fuero fuero) {
		this.fuero = fuero;
	}
	/**
	 * @return the juez
	 */
	public Juez getJuez() {
		return juez;
	}
	/**
	 * @param juez the juez to set
	 */
	public void setJuez(Juez juez) {
		this.juez = juez;
	}
	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	
}
