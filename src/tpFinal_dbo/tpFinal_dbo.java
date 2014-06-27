package tpFinal_dbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class tpFinal_dbo {
	
	private static Personas personas;
	
	// date validation using SimpleDateFormat
	// it will take a string and make sure it's in the proper 
	// format as defined by you, and it will also make sure that
	// it's a legal date

	public static boolean isValidDate(String date)
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

	
	public static int menu(){
		//Displays the main menu and handles passing off to next menu. 

		Scanner scanner = new Scanner (System.in);
		int selection=0;
		int i=0;

		while(i==0) {   //changed while(1) to a value that didn't complain in netbeans
			System.out.println("Seleccione una Opcion:"); 
			System.out.println("[1] Personas"); 
			System.out.println("[2] Causas"); 
			System.out.println("[0] Salir"); 
			System.out.println("Opcion: "); 
			selection=scanner.nextInt();     

			switch (selection){

			case 1:
				//System.out.println("ABM de Personas");
				menuPersona();
				break;       

			case 2:
				System.out.println("ABM de Causas");
				i=1;
				break;

			case 0:System.out.println("Saliendo del Sistema...");
			scanner.close();
			System.exit(5);

			default:System.out.println("Opcion invalida. Intente de nuevo");

			};

		}  
		return selection;
	}
	
	public static int menuPersona(){

		Scanner scanner = new Scanner (System.in);
		int selection=0;
		int i=0;

		while(i==0) {
			System.out.println("Seleccione una Opcion:"); 
			System.out.println("[1] Alta"); 
			System.out.println("[2] Modificacion"); 
			System.out.println("[3] Baja"); 
			System.out.println("[4] Listar"); 
			System.out.println("[0] Volver al Menu Principal"); 
			System.out.println("Opcion: "); 
			selection=scanner.nextInt();     

			switch (selection){

			case 1:
				System.out.println("Alta de Personas");
				PersonaAlta();
				break;       

			case 2:
				System.out.println("Modificacion de Personas");
				//i=1;
				break;
				
			case 3:
				System.out.println("Baja de Personas");
				break;
			case 4:
				System.out.println("Listado de Personas");
				break;
			case 0:
				i=1;
				break;

			default:
				System.out.println("Opcion invalida. Intente de nuevo");

			};
		}  
		scanner.close();
		return selection;
	}
	
	public static Boolean PersonaAlta(){
		Scanner scanner = new Scanner (System.in);
		String texto;
		Persona persona = new Persona();
				
		try {
			System.out.print("Ingrese Apellido: ");
			texto = scanner.nextLine();
			persona.setApellido(texto);
			
			System.out.print("Ingrese Nombre: ");
			texto = scanner.nextLine();
			persona.setNombre(texto);
			
			System.out.print("Ingrese DNI: ");
			texto = scanner.nextLine();
			persona.setDni(texto);
			
			texto="";	
			while (!(texto.equalsIgnoreCase("m") || texto.equalsIgnoreCase("f"))) {
				System.out.print("Ingrese Sexo [m=Masculino f=Femenino]: ");
				texto = scanner.nextLine();
			}
			
			if (texto == "m") {
				persona.setSexo(Persona.Sexo.masculino);
			} else {
				persona.setSexo(Persona.Sexo.femenino);
			}
			
			System.out.print("Ingrese fecha de Nacimiento [DD/MM/AAAA]: ");
			texto = scanner.nextLine();
			while (!isValidDate(texto)){ 
				System.out.print("Fecha incorrecta! Escriba nuevamente la fecha de Nacimiento [DD/MM/AAAA]: "); 
				texto=scanner.nextLine(); 
			}
			persona.setFechaNacimiento(texto);
			
			tpFinal_dbo.personas.add(persona);
			System.out.println("Persona guardada con exito");
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} finally {
			scanner.close();
		}

	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		personas = new Personas();
		menu();
	}

}
