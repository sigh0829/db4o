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
	    CausaModificacionImputadoAlta,
	    CausaModificacionImputadoBaja,
	    CausaModificacionTestigoAlta,
	    CausaModificacionTestigoBaja,
	    CausaModificacionSentenciaModificacion,
	    ListadoCausasConMas2Imputados_QBE,
	    ListadoJuzgadosFueroCivil_QBE,
	    ListadoCausasConMas2Imputados_NQ,
	    ListadoJuzgadosFueroCivil_NQ,
	    ListadoCausasConMas2Imputados_SODA,
	    ListadoJuzgadosFueroCivil_SODA,

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
					//System.out.println("[5] Alta Automatica"); 
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
						
					//case 5:
					//	return retorno.JuzgadoAddRandom;
						
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
					//System.out.println("[5] Alta Automatica"); 
					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						return retorno.CausaAlta;

					case 2:
						CausaEditSubMenu menu;
						menu = new MainMenu.CausaEditSubMenu();
						return menu.show();

					case 3:
						return retorno.CausaBaja;

					case 4:
						return retorno.CausaListar;
						
					//case 5:
					//	return retorno.CausaAddRandom;
						
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
	
	public class CausaEditSubMenu extends MainMenu {
		public retorno show(){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner (System.in);
			int selection=0;
			int i=0;

			try {
				while(i==0) {
					System.out.println("Seleccione una Opcion:"); 
					System.out.println("[1] Agregar Imputado"); 
					System.out.println("[2] Eliminar Imputado"); 
					System.out.println("[3] Agregar Testigo"); 
					System.out.println("[4] Eliminar Testigo");
					System.out.println("[5] Cambiar sentencia");
					System.out.println("[0] Volver al Menu Anterior"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						return retorno.CausaModificacionImputadoAlta;

					case 2:
						return retorno.CausaModificacionImputadoBaja;

					case 3:
						return retorno.CausaModificacionTestigoAlta;

					case 4:
						return retorno.CausaModificacionTestigoBaja;
						
					case 5:
						return retorno.CausaModificacionSentenciaModificacion;
						
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
	
	public class ListadoMenu extends MainMenu {
		public retorno show(){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner (System.in);
			int selection=0;
			int i=0;

			try {
				while(i==0) {
					System.out.println("Seleccione una Opcion:"); 
					System.out.println("[1] Causas con sentencia que tengan mas de 2 imputados (QueryByExample)"); 
					System.out.println("[2] Juzgados del fuero civil que tengan al menos una causa CON sentencia y una causa SIN sentencia (QueryByExample)");
					System.out.println("[3] Causas con sentencia que tengan mas de 2 imputados (NativeQueries)"); 
					System.out.println("[4] Juzgados del fuero civil que tengan al menos una causa CON sentencia y una causa SIN sentencia (NativeQueries)"); 
					System.out.println("[5] Causas con sentencia que tengan mas de 2 imputados (SODA)"); 
					System.out.println("[6] Juzgados del fuero civil que tengan al menos una causa CON sentencia y una causa SIN sentencia (SODA)"); 

					System.out.println("[0] Volver al Menu Principal"); 
					System.out.print("Opcion: ");
			
					selection = scanner.nextInt();  
					//scanner2.close();
					switch (selection){

					case 1:
						return retorno.ListadoCausasConMas2Imputados_QBE;

					case 2:
						return retorno.ListadoJuzgadosFueroCivil_QBE;

					case 3:
						return retorno.ListadoCausasConMas2Imputados_NQ;

					case 4:
						return retorno.ListadoJuzgadosFueroCivil_NQ;
						
					case 5:
						return retorno.ListadoCausasConMas2Imputados_SODA;
						
					case 6:
						return retorno.ListadoJuzgadosFueroCivil_SODA;
						
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
			System.out.println("[5] Consultas");
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
				
			case 5:
				//System.out.println("Listados");
				menu = new MainMenu.ListadoMenu();
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
