package tpFinal_dbo;

import java.util.Scanner;

public class MainMenu {
	public enum retorno {
	    PersonaAlta,
	    PersonaModificacion,
	    PersonaBaja,
	    PersonaListar,
	    PersonaAddRandom,
	    JuezAlta,
	    JuezModificacion,
	    JuezBaja,
	    JuezListar,
	    JuezAddRandom,
	    JuzgadoAlta,
	    JuzgadoModificacion,
	    JuzgadoBaja,
	    JuzgadoListar,
	    JuzgadoAddRandom,
	    CausaAlta,
	    CausaModificacion,
	    CausaBaja,
	    CausaListar,
	    CausaAddRandom,
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
					System.out.println("[5] Alta Automatica"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						return retorno.PersonaAlta;

					case 2:
						return retorno.PersonaModificacion;

					case 3:
						return retorno.PersonaBaja;

					case 4:
						return retorno.PersonaListar;
						
					case 5:
						return retorno.PersonaAddRandom;
						
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
					System.out.println("[5] Alta Automatica"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						return retorno.JuezAlta;

					case 2:
						return retorno.JuezModificacion;

					case 3:
						return retorno.JuezBaja;

					case 4:
						return retorno.JuezListar;
						
					case 5:
						return retorno.JuezAddRandom;
						
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
					System.out.println("[5] Alta Automatica"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						return retorno.JuzgadoAlta;

					case 2:
						return retorno.JuzgadoModificacion;

					case 3:
						return retorno.JuzgadoBaja;

					case 4:
						return retorno.JuzgadoListar;
						
					case 5:
						return retorno.JuzgadoAddRandom;
						
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
	
	public class CausaMenu extends MainMenu {
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
					System.out.println("[5] Alta Automatica"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						return retorno.CausaAlta;

					case 2:
						return retorno.CausaModificacion;

					case 3:
						return retorno.CausaBaja;

					case 4:
						return retorno.CausaListar;
						
					case 5:
						return retorno.CausaAddRandom;
						
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
				//System.out.println("ABM de Causas");
				menu = new MainMenu.CausaMenu();
				return menu.show();

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
