package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Persona.ExcepcionValidacion;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
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
	
	public Persona ingresoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		Long dni;
		Persona persona = new Persona();

		try {
			while (true) {
				try {
					System.out.print("Ingrese Apellido: ");
					texto = scanner.nextLine();
					persona.setApellido(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}

			while (true) {
				try {
					System.out.print("Ingrese Nombre: ");
					texto = scanner.nextLine();
					persona.setNombre(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}

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
			
			while (true) {
				try {
					System.out.print("Ingrese Sexo [m=Masculino f=Femenino]: ");
					scanner = new Scanner (System.in);
					texto = scanner.nextLine();
					persona.setSexo(texto.toLowerCase());
					break;
				} catch (Persona.ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			
			while (true) {
				try {
					System.out.print("Ingrese fecha de Nacimiento [DD/MM/AAAA]: ");
					texto = scanner.nextLine();
					persona.setFechaNacimiento(texto);
					break;
				} catch (Persona.ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

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
				ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
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
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
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
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
		
		List <Persona> personas = db.query(new Predicate<Persona>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 189422016090086047L;

			public boolean match(Persona persona) {
				return persona.getDni().equals(dni) ;
			}
		});
		
		existe = !personas.isEmpty();
		db.close();
		return existe;
	}
	
	public Persona getPersonaByDNI(final Long dni) {
		Persona persona = null;
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
		
		List <Persona> personas = db.query(new Predicate<Persona>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 189422016090086047L;

			public boolean match(Persona persona) {
				return persona.getDni().equals(dni) ;
			}
		});
		
		if (!personas.isEmpty()) {
			persona = personas.get(0);
		}
		db.close();
		return persona;
	}
}
