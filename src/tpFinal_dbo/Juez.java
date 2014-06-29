package tpFinal_dbo;

import org.w3c.dom.ranges.RangeException;

public class Juez {
	
	/**
	 * @param nombre
	 * @param matricula
	 * @param trayectoria
	 */
	public Juez(String nombre, int matricula, String trayectoria) {
		super();
		this.nombre = nombre;
		this.matricula = matricula;
		this.trayectoria = trayectoria;
	}
	/**
	 * 
	 */
	public Juez() {
		super();
	}
	/**
	 * @param nombre
	 * @param matricula
	 * @param trayectoria
	 */

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
	private int matricula;
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
	public int getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula the matricula to set
	 * @throws RangeException 
	 */
	public void setMatricula(int matricula) throws RangeException {
		if (Integer.toString(matricula).length() < 2)
			throw new RangeException((short) 0, "El numero de matricula debe tener 2 digitos como minimo");
		if (matricula>32767)
			throw new RangeException((short) 0,"Numero invalido");
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
		return (this.getMatricula() + " - " + this.getNombre());
	}
	
}
