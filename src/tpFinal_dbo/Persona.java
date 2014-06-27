package tpFinal_dbo;

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
	private String dni;
	private String sexo;
	//private Date fechaNacimiento;
	private String fechaNacimiento;
	
	public Persona(String nombre, String apellido, String dni, String sexo,
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
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
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
	
	
	
	
}
