package tpFinal_dbo;

import java.util.Scanner;

public class MainMenu {
	public enum retorno {
	    PersonaAlta,
	    PersonaModificacion,
	    PersonaBaja,
	    PersonaListar,
	    JuezAlta,
	    JuezModificacion,
	    JuezBaja,
	    JuezListar,
	    JuzgadoAlta,
	    JuzgadoModificacion,
	    JuzgadoBaja,
	    JuzgadoListar,
	    Salir
	}
	
	public class PersonaMenu extends MainMenu {
		public retorno show(){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner (System.in);
			int selection=0;
			int i=0;

			try {
				while(i==0) {
					System.out.println("Seleccione una Opcion:"); 
					System.out.println("[1] Alta"); 
					System.out.println("[2] Modificacion"); 
					System.out.println("[3] Baja"); 
					System.out.println("[4] Listar"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						System.out.println("Alta de Personas");
						return retorno.PersonaAlta;

					case 2:
						System.out.println("Modificacion de Personas");
						return retorno.PersonaModificacion;

					case 3:
						System.out.println("Baja de Personas");
						return retorno.PersonaBaja;

					case 4:
						System.out.println("Listado de Personas");
						return retorno.PersonaListar;
						
					case 0:
						i=1;
						break;

					default:
						System.out.println("Opcion invalida. Intente de nuevo");

					};
				} 

			} catch (Exception e) {
				// Relanzo la excepcion
				throw e;
			}
			return null;
		}
	}
	
	public class JuezMenu extends MainMenu {
		public retorno show(){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner (System.in);
			int selection=0;
			int i=0;

			try {
				while(i==0) {
					System.out.println("Seleccione una Opcion:"); 
					System.out.println("[1] Alta"); 
					System.out.println("[2] Modificacion"); 
					System.out.println("[3] Baja"); 
					System.out.println("[4] Listar"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						System.out.println("Alta de Jueces");
						return retorno.JuezAlta;

					case 2:
						System.out.println("Modificacion de Jueces");
						return retorno.JuezModificacion;

					case 3:
						System.out.println("Baja de Jueces");
						return retorno.JuezBaja;

					case 4:
						System.out.println("Listado de Jueces");
						return retorno.JuezListar;
						
					case 0:
						i=1;
						break;

					default:
						System.out.println("Opcion invalida. Intente de nuevo");

					};
				} 

			} catch (Exception e) {
				// Relanzo la excepcion
				throw e;
			}
			return null;

		}
	}
	
	public class JuzgadoMenu extends MainMenu {
		public retorno show(){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner (System.in);
			int selection=0;
			int i=0;

			try {
				while(i==0) {
					System.out.println("Seleccione una Opcion:"); 
					System.out.println("[1] Alta"); 
					System.out.println("[2] Modificacion"); 
					System.out.println("[3] Baja"); 
					System.out.println("[4] Listar"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						System.out.println("Alta de Juzgados");
						return retorno.JuzgadoAlta;

					case 2:
						System.out.println("Modificacion de Juzgados");
						return retorno.JuzgadoModificacion;

					case 3:
						System.out.println("Baja de Juzgados");
						return retorno.JuzgadoBaja;

					case 4:
						System.out.println("Listado de Juzgados");
						return retorno.JuzgadoListar;
						
					case 0:
						i=1;
						break;

					default:
						System.out.println("Opcion invalida. Intente de nuevo");

					};
				} 

			} catch (Exception e) {
				// Relanzo la excepcion
				throw e;
			}
			return null;
		}
	}

	public retorno show() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		int selection=0;
		int i=0;
		MainMenu menu;

		while(i==0) {

			System.out.println("Seleccione una Opcion:"); 
			System.out.println("[1] Personas"); 
			System.out.println("[2] Jueces");
			System.out.println("[3] Juzgados");
			System.out.println("[4] Causas");
			System.out.println("[0] Salir"); 
			System.out.print("Opcion: "); 
			
			selection=scanner.nextInt();     
			//scanner.close();

			switch (selection){

			case 1:
				//System.out.println("ABM de Personas");
				menu = new MainMenu.PersonaMenu();
				return menu.show();

			case 2:
				//System.out.println("ABM de Jueces");
				menu = new MainMenu.JuezMenu();
				return menu.show();
				
			case 3:
				//System.out.println("ABM de Juzgados");
				menu = new MainMenu.JuzgadoMenu();
				return menu.show();
				
			case 4:
				System.out.println("ABM de Causas");
				//i=1;
				break;

			case 0:
				System.out.println("Saliendo del Sistema...");
				i=1;
				break;
				//System.exit(5);

			default:
				System.out.println("Opcion invalida. Intente de nuevo");
			};

		}  
		return retorno.Salir;
	}
}
