package tpFinal_dbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.ranges.RangeException;

public class Persona {
	
	public class ExcepcionValidacion extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExcepcionValidacion(String msg) {
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
	 * @throws ExcepcionValidacion 
	 */
	public void setNombre(String nombre) throws ExcepcionValidacion {
		if (nombre.length()==0)
			throw new ExcepcionValidacion("Debe especificar un Nombre");
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
	 * @throws ExcepcionValidacion 
	 */
	public void setApellido(String apellido) throws ExcepcionValidacion {
		if (apellido.length()==0)
			throw new ExcepcionValidacion("Debe especificar un Apellido");
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
	 * @throws RangeException 
	 */
	public void setDni(Long dni) throws RangeException {
		if (dni.toString().length() < 5)
			throw new RangeException((short) 0, "El dni debe tener 5 digitos como minimo");
		
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
	 * @throws ExcepcionValidacion 
	 */
	public void setSexo(String sexo) throws ExcepcionValidacion {
		if (!(sexo.equalsIgnoreCase("m") || sexo.equalsIgnoreCase("f"))) {
			throw new ExcepcionValidacion("Sexo debe ser m o f");
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
	 * @throws ExcepcionValidacion 
	 */
	public void setFechaNacimiento(String fechaNacimiento) throws ExcepcionValidacion {
		if (!isValidDate(fechaNacimiento))
			throw new ExcepcionValidacion("Fecha incorrecta. El Formato debe ser DD/MM/AAAA");
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String toString() {
		return (this.getDni().toString() + " - " + this.getApellido() + ", " + this.getNombre() + " [" + this.getSexo() + "]" );
	}
	
	private  boolean isValidDate(String date)
	{
		// set date format, this can be changed to whatever format
		// you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
		// you can read more about it here:
		// http://java.sun.com/j2se/1.4.2/docs/api/index.html

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// declare and initialize testDate variable, this is what will hold
		// our converted string

		Date testDate = null;

		// we will now try to parse the string into date form
		try
		{
			testDate = sdf.parse(date);
		}

		// if the format of the string provided doesn't match the format we 
		// declared in SimpleDateFormat() we will get an exception

		catch (ParseException e)
		{
			// errorMessage = "the date you provided is in an invalid date" +
			//                        " format.";
			return false;
		}

		// dateformat.parse will accept any date as long as it's in the format
		// you defined, it simply rolls dates over, for example, december 32 
		// becomes jan 1 and december 0 becomes november 30
		// This statement will make sure that once the string 
		// has been checked for proper formatting that the date is still the 
		// date that was entered, if it's not, we assume that the date is invalid

		if (!sdf.format(testDate).equals(date)) 
		{
			//errorMessage = "The date that you provided is invalid.";
			return false;
		}

		// if we make it to here without getting an error it is assumed that
		// the date was a valid one and that it's in the proper format

		return true;

	} // end isValidDate
	
}
