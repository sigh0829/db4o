package tpFinal_dbo;

import org.w3c.dom.ranges.RangeException;

public class Juez {
	
	public class ExcepcionValidacion extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 2L;

		public ExcepcionValidacion(String msg) {
	        super(msg);
	    }
	}
	
	private String nombre;
	private Long matricula;
	private String trayectoria;
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 * @throws ExcepcionValidacion 
	 */
	public void setNombre(String nombre) throws ExcepcionValidacion {
		if (nombre.length()==0)
			throw new ExcepcionValidacion("Debe especificar un Nombre");
		this.nombre = nombre;
	}
	/**
	 * @return the matricula
	 */
	public Long getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula the matricula to set
	 * @throws RangeException 
	 */
	public void setMatricula(Long matricula) throws RangeException {
		if (matricula.toString().length() < 2)
			throw new RangeException((short) 0, "El numero de matricula debe tener 2 digitos como minimo");
		this.matricula = matricula;
	}
	/**
	 * @return the trayectoria
	 */
	public String getTrayectoria() {
		return trayectoria;
	}
	/**
	 * @param trayectoria the trayectoria to set
	 * @throws ExcepcionValidacion 
	 */
	public void setTrayectoria(String trayectoria) throws ExcepcionValidacion {
		if (trayectoria.length()==0)
			throw new ExcepcionValidacion("Debe especificar una Trayectoria");
		this.trayectoria = trayectoria;
	}
	
	public String toString() {
		return (this.getMatricula().toString() + " - " + this.getNombre());
	}
	
}
