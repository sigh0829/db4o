package tpFinal_dbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class Personas {
	
	public class ExcepcionPersonaDuplicada extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExcepcionPersonaDuplicada(String msg) {
	        super(msg);
	    }
	}
	
	// date validation using SimpleDateFormat
	// it will take a string and make sure it's in the proper 
	// format as defined by you, and it will also make sure that
	// it's a legal date

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
	
	public Persona ingresoPorTeclado(){
		Scanner scanner = new Scanner (System.in);
		String texto;
		Long dni;
		Persona persona = new Persona();

		try {
			System.out.print("Ingrese Apellido: ");
			texto = scanner.nextLine();
			persona.setApellido(texto);

			System.out.print("Ingrese Nombre: ");
			texto = scanner.nextLine();
			persona.setNombre(texto);

			
			while (true) {
				try {
					System.out.print("Ingrese DNI: ");
					dni = scanner.nextLong();
					persona.setDni(dni);
					break;
				} catch (RangeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			
			scanner.reset();
			while (true) {
				try {
					System.out.print("Ingrese Sexo [m=Masculino f=Femenino]: ");
					texto = scanner.nextLine();
					persona.setSexo(texto);
					break;
				} catch (Persona.ExcepcionSexo e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}	


			System.out.print("Ingrese fecha de Nacimiento [DD/MM/AAAA]: ");
			texto = scanner.nextLine();
			while (!isValidDate(texto)){ 
				System.out.print("Fecha incorrecta! Escriba nuevamente la fecha de Nacimiento [DD/MM/AAAA]: "); 
				texto=scanner.nextLine(); 
			}
			persona.setFechaNacimiento(texto);

			return persona;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Boolean add(Persona persona) throws ExcepcionPersonaDuplicada {
		
		try {

			//Me fijo que no exista la Persona
			if (!this.exists(persona.getDni())) {
				ObjectContainer db = Db4oEmbedded.openFile("db/databaseFile.db4o");
				db.store(persona);
				db.close();
			} else {
				throw new ExcepcionPersonaDuplicada("Ya existe una persona con DNI " .concat(persona.getDni().toString()));
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
        } finally {
        	
        }
		return true;
	}
	
	public List<Persona> listar (){
		ObjectContainer db = Db4oEmbedded.openFile("db/databaseFile.db4o");
		List <Persona> personas =db.query(Persona.class);
		System.out.println("-----------------------");
		System.out.println("| Listado de Personas |");
		System.out.println("-----------------------");
		
		for (Persona persona : personas) {
			System.out.println(persona);
		}
		System.out.println("-----------------------");
		System.out.printf("Total %d Personas\n", personas.size());
		db.close();
		return personas;
	}
	
	public Boolean exists(final Long dni) {
		Boolean existe;
		ObjectContainer db = Db4oEmbedded.openFile("db/databaseFile.db4o");
		
		List <Persona> personas = db.query(new Predicate<Persona>() {
			public boolean match(Persona persona) {
				return persona.getDni() == dni;
			}
		});
		
		existe = !personas.isEmpty();
		db.close();
		return existe;
	}
}
