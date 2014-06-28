package tpFinal_dbo;

import tpFinal_dbo.Personas.ExcepcionPersonaDuplicada;

public class tpFinal_dbo {


	public static void main(String[] args) {
		//Personas personasAux = new Personas();
		//Persona persona = new Persona("Iglesias", "Matias", "26192645", "m", "29/09/1977");
		//personasAux.add(persona);
		Personas personas = new Personas();
		Jueces jueces = new Jueces();
		Juzgados juzgados = new Juzgados();
		
		MainMenu menu = new MainMenu();
		MainMenu.retorno retorno = null;
		while (retorno != MainMenu.retorno.Salir) {
			try {
				retorno = menu.show();
				switch (retorno) {
				case PersonaAlta:
					
					if (personas.add(personas.ingresoPorTeclado())) {
						System.out.println("Persona Guardada con Exito");
					} else {
						System.out.println("Error al Guardar Persona");
					}
					break;
					
				case PersonaModificacion:
					System.out.println("Operacion aun no soportada");
					break;
					
				case PersonaBaja:
					System.out.println("Operacion aun no soportada");
					break;
					
				case PersonaListar:
					personas.listar();
					break;
					
				case JuezAlta:		
					if (jueces.add(jueces.ingresoPorTeclado())) {
						System.out.println("Juez Guardado con Exito");
					} else {
						System.out.println("Error al Guardar Juez");
					}
					break;
					
				case JuezModificacion:
					System.out.println("Operacion aun no soportada");
					break;
					
				case JuezBaja:
					System.out.println("Operacion aun no soportada");
					break;
					
				case JuezListar:
					jueces.listar();
					break;
					
				case JuzgadoAlta:		
					if (juzgados.add(juzgados.ingresoPorTeclado())) {
						System.out.println("Juzgado Guardado con Exito");
					} else {
						System.out.println("Error al Guardar Juzgado");
					}
					break;
					
				case JuzgadoModificacion:
					System.out.println("Operacion aun no soportada");
					break;
					
				case JuzgadoBaja:
					System.out.println("Operacion aun no soportada");
					break;
					
				case JuzgadoListar:
					juzgados.listar();
					break;
					
				case Salir:
					break;
					
				default:
					System.out.println("Operacion aun no soportada");
					break;
				}

			} catch (ExcepcionPersonaDuplicada e) {
				System.out.printf("Validacion de datos: %s\n",e.getMessage());
			}catch (Exception e) {
				// TODO: handle exception
				System.out.printf("ERROR EN EL SISTEMA: %s\n",e);
			}
		}

	}

}
