package tpFinal_dbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class tpFinal_dbo {

	

	// date validation using SimpleDateFormat
	// it will take a string and make sure it's in the proper 
	// format as defined by you, and it will also make sure that
	// it's a legal date

	public  boolean isValidDate(String date)
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


	

	public Boolean PersonaAlta(){
		Scanner scanner = new Scanner (System.in);
		String texto;
		Personas personas = new Personas();
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

			personas.add(persona);

			System.out.println("Persona guardada con exito");

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.printf("Error de Sistema: %s", e.getMessage());
			return false;
		} finally {
			scanner.close();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//this.personas = new Personas();
		//Persona persona = new Persona("Iglesias", "Matias", "26192645", "m", "29/09/1977");
		//personas.add(persona);
		MainMenu menu = new MainMenu();
		MainMenu.retorno retorno;
		
		retorno = menu.show();
		switch (retorno) {
		case PersonaAlta:
			
			break;

		default:
			break;
		}
	}

}
