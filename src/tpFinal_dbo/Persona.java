package tpFinal_dbo;

import org.w3c.dom.ranges.RangeException;

public class Persona {
	
	public class ExcepcionSexo extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExcepcionSexo(String msg) {
	        super(msg);
	    }
	}

	private String nombre;
	private String apellido;
	private Long dni;
	private String sexo;
	//private Date fechaNacimiento;
	private String fechaNacimiento;
	
	public Persona(String nombre, String apellido, Long dni, String sexo,
			String fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the dni
	 */
	public Long getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(Long dni) {
		if (dni.toString().length() > 8)
			throw new RangeException((short) 0, "El dni debe tener 8 digitos como maximo");
		this.dni = dni;
	}
	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 * @throws Exception 
	 */
	public void setSexo(String sexo) throws Exception {
		if (!(sexo.equalsIgnoreCase("m") || sexo.equalsIgnoreCase("f"))) {
			throw new ExcepcionSexo("Sexo debe ser m o f");
		}
		this.sexo = sexo;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String toString() {
		return (this.getDni().toString() + " - " + this.getApellido() + ", " + this.getNombre() + "[" + this.getSexo() + "]" );
	}
	
	
}
