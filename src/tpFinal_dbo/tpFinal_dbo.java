package tpFinal_dbo;

import java.util.Random;

import tpFinal_dbo.Personas.ExcepcionPersonaDuplicada;

public class tpFinal_dbo {

private static Long obtenerDNIalAzar() {
	Random r = new Random();
	int minDNI = 10000;
	int maxDNI = 99000;
	int random = r.nextInt(maxDNI-minDNI)+minDNI;
	Long result = new Long(random) * 1000 + random/10;
	
	return result;
}
	public static void main(String[] args) {
		Personas personas = new Personas();
		Jueces jueces = new Jueces();
		Juzgados juzgados = new Juzgados();
		Causas causas = new Causas();
		Persona persona;
		Juez juez;

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
					
				case PersonaAddRandom:
					System.out.println("A continuacion se crearan 10 personas al azar");
					for (int i = 0; i < 10; i++) {
						persona = new Persona("Nombre " + i, "Apellido " + 1, obtenerDNIalAzar(), "m", "01/01/1980");
						personas.add(persona);
					}
					System.out.println("Se han creado 10 personas al azar");
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
					
				case JuezAddRandom:
					System.out.println("A continuacion se crearan 10 jueces al azar");
					for (int i = 0; i < 10; i++) {
						juez = new Juez("Juez " + i, obtenerDNIalAzar(), "Trayectoria del Juez " + i);
						jueces.add(juez);
					}
					System.out.println("Se han creado 10 jueces al azar");
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
					
				case CausaAlta:		
					if (causas.add(causas.ingresoPorTeclado())) {
						System.out.println("Causa Guardada con Exito");
					} else {
						System.out.println("Error al Guardar la Causa");
					}
					break;
					
				case CausaModificacion:
					System.out.println("Operacion aun no soportada");
					break;
					
				case CausaBaja:
					System.out.println("Operacion aun no soportada");
					break;
					
				case CausaListar:
					causas.listar();
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
