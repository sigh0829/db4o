package tpFinal_dbo;

import tpFinal_dbo.Personas.ExcepcionPersonaDuplicada;

public class tpFinal_dbo {

	public static void main(String[] args) {
		Personas personas = new Personas();
		Jueces jueces = new Jueces();
		Juzgados juzgados = new Juzgados();
		Causas causas = new Causas();
		Persona persona;
		Juez juez;
		Listados listados = new Listados();

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
					if (personas.update(personas.modificacionPorTeclado())) {
						System.out.println("Persona Modificada con Exito");
					} else {
						System.out.println("Error al Modificar Persona");
					}
					break;
					
				case PersonaBaja:
					if (personas.delete(personas.eliminacionPorTeclado())) {
						System.out.println("Persona Eliminada con Exito");
					} else {
						System.out.println("Error al Eliminar Persona");
					}					break;
					
				case PersonaListar:
					personas.listar();
					break;
					
				case PersonaAddRandom:
					System.out.println("A continuacion se crearan 10 personas al azar");
					for (int i = 0; i < 10; i++) {
						persona = new Persona(Utils.getRandomNombre(), Utils.getRandomApellido(), Utils.getRandomDNI(), Utils.getRandomSexo(), "01/01/1980");
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
					if (jueces.update(jueces.modificacionPorTeclado())) {
						System.out.println("Juez Modificado con Exito");
					} else {
						System.out.println("Error al Modificar Juez");
					}
					break;
					
				case JuezBaja:
					if (jueces.delete(jueces.eliminacionPorTeclado())) {
						System.out.println("Juez Eliminado con Exito");
					} else {
						System.out.println("Error al Eliminar Juez");
					}
					break;
					
				case JuezListar:
					jueces.listar();
					break;
					
				case JuezAddRandom:
					System.out.println("A continuacion se crearan 10 jueces al azar");
					for (int i = 0; i < 10; i++) {
						String apellido = Utils.getRandomApellido();
						juez = new Juez(apellido + ", " + Utils.getRandomNombre(), Utils.getRandomNumber(1, 32767) , "Trayectoria del Juez " + apellido);
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
					if (juzgados.update(juzgados.modificacionPorTeclado())) {
						System.out.println("Juzgado Modificado con Exito");
					} else {
						System.out.println("Error al Modificar Juzgado");
					}
					break;
					
				case JuzgadoBaja:
					if (juzgados.delete(juzgados.eliminacionPorTeclado())) {
						System.out.println("Juzgado Eliminado con Exito");
					} else {
						System.out.println("Error al Eliminar Juzgado");
					}
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
							
				//case CausaModificacion:
				//	System.out.println("Operacion aun no soportada");
				//	break;
					
				case CausaModificacionImputadoAlta:
					if (causas.update(causas.ingresoImputadoPorTeclado())) {
						System.out.println("Imputado Agregado a la causa con Exito");
					} else {
						System.out.println("Error al Agregar Imputado a la Causa");
					}
					break;
					
				case CausaModificacionImputadoBaja:
					if (causas.update(causas.eliminacionImputadoPorTeclado())) {
						System.out.println("Imputado Eliminado de la causa con Exito");
					} else {
						System.out.println("Error al Eliminar Imputado de la Causa");
					}
					break;
					
				case CausaModificacionTestigoAlta:
					if (causas.update(causas.ingresoTestigoPorTeclado())) {
						System.out.println("Testigo Agregado a la causa con Exito");
					} else {
						System.out.println("Error al Agregar Testigo a la Causa");
					}
					break;
				
				case CausaModificacionTestigoBaja:
					if (causas.update(causas.eliminacionTestigoPorTeclado())) {
						System.out.println("Testigo Eliminado de la causa con Exito");
					} else {
						System.out.println("Error al Eliminar Testigo de la Causa");
					}
					break;
					
				case CausaModificacionSentenciaModificacion:
					System.out.println("CausaModificacionSentenciaModificacion");
					break;
				
				case CausaBaja:
					if (causas.delete(causas.eliminacionPorTeclado())) {
						System.out.println("Causa Eliminada con Exito");
					} else {
						System.out.println("Error al Eliminar Causa");
					}
					break;
					
				case CausaListar:
					causas.listar();
					break;
					
				case ListadoCausasConMas2Imputados_QBE:
					listados.causasConMas2Imputados_QBE();
					break;
					
				case ListadoJuzgadosFueroCivil_QBE:
					listados.juzgadosFueroCivil_QBE();
					break;
					
				case ListadoCausasConMas2Imputados_NQ:
					listados.causasConMas2Imputados_NQ();
					break;
					
				case ListadoJuzgadosFueroCivil_NQ:
					listados.juzgadosFueroCivil_NQ();
					break;
					
				case ListadoCausasConMas2Imputados_SODA:
					listados.causasConMas2Imputados_SODA();
					break;
					
				case ListadoJuzgadosFueroCivil_SODA:
					listados.juzgadosFueroCivil_SODA();
					break;
					
				case Salir:
					//Db.getConnection().close();
					break;
					
				default:
					System.out.println("Operacion aun no soportada");
					break;
				}

			} catch (ExcepcionPersonaDuplicada e) {
				System.out.printf("Validacion de datos: %s\n",e.getMessage());
			}catch (Exception e) {
				// TODO: handle exception
				System.out.printf("ERROR EN EL SISTEMA: %s\n",e.getMessage());
			}
		}
	}
	

}
