package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Persona.ExcepcionValidacion;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

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
	
	public class ExcepcionPersonaInexistente extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 10L;

		public ExcepcionPersonaInexistente(String msg) {
	        super(msg);
	    }
	}
	
	public class ExcepcionPersonaViolaIntegridadReferencial extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 20L;

		public ExcepcionPersonaViolaIntegridadReferencial(String msg) {
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
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Persona modificacionPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		Long dni;
		Persona persona = new Persona();

		try {
			System.out.println("Modificacion de Personas");
			System.out.println("------------------------");
			while (true) {
				try {
					System.out.print("Ingrese DNI: ");
					dni = scanner.nextLong();
					persona = this.getPersonaByDNI(dni);
					if (persona == null) {
						System.out.println("No existe ninguna persona con DNI " + dni.toString() + ".Intente nuevamente");
					} else {
						break;
					}
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				}
			}
			
			scanner = new Scanner (System.in);
			while (true) {
				try {
					System.out.println("Apellido actual: " + persona.getApellido());
					System.out.print("Ingrese nuevo Apellido o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
						persona.setApellido(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}

			while (true) {
				try {
					System.out.println("Nombre actual: " + persona.getNombre());
					System.out.print("Ingrese nuevo Nombre o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
						persona.setNombre(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	
			
			while (true) {
				try {
					System.out.println("Sexo actual: " + persona.getSexo());
					System.out.print("Ingrese nuevo Sexo [m=Masculino f=Femenino] o presione [ENTER] para continuar: ");
					scanner = new Scanner (System.in);
					texto = scanner.nextLine();
					if (texto.length()>0)
						persona.setSexo(texto.toLowerCase());
					break;
				} catch (Persona.ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			
			while (true) {
				try {
					System.out.println("Fecha de Nacimiento actual: " + persona.getFechaNacimiento());
					System.out.print("Ingrese nueva fecha de Nacimiento [DD/MM/AAAA] o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
						persona.setFechaNacimiento(texto);
					break;
				} catch (Persona.ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			return persona;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Persona eliminacionPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		Long dni;
		Persona persona = new Persona();

		try {
			System.out.println("Baja de Personas");
			System.out.println("----------------");
			while (true) {
				try {
					System.out.print("Ingrese DNI: ");
					dni = scanner.nextLong();
					persona = this.getPersonaByDNI(dni);
					if (persona == null) {
						System.out.println("No existe ninguna persona con DNI " + dni.toString() + ".Intente nuevamente");
					} else {
						break;
					}
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				}
			}
			
			scanner = new Scanner (System.in);
			while (true) {
				try {
					texto = "";
					while (!(texto.equalsIgnoreCase("s") || texto.equalsIgnoreCase("n"))) {
						System.out.print("Esta seguro que desea eliminar a la persona: " + persona + "? [s=Si, n=No]:" );
						texto = scanner.nextLine();
						if (texto.equalsIgnoreCase("s")) {
							return persona;
						} else if (texto.equalsIgnoreCase("n")) {
							return null;
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Boolean add(Persona persona) throws ExcepcionPersonaDuplicada {
		
		try {

			//Me fijo que no exista la Persona
			if (!this.exists(persona.getDni())) {
				Db.getInstance();
				Db.getConnection().store(persona);
			} else {
				throw new ExcepcionPersonaDuplicada("Ya existe una persona con DNI " .concat(persona.getDni().toString()));
			}

			
		} catch (Exception e) {
			throw e;
        } finally {
        	
        }
		return true;
	}
	
	public Boolean update(Persona persona) throws ExcepcionPersonaInexistente, Exception {
		
		try {
			//Me fijo que  exista la Persona
			if (this.exists(persona.getDni())) {
				Db.getInstance();
				Db.getConnection().store(persona);
			} else {
				throw new ExcepcionPersonaInexistente("No existe una persona con DNI " .concat(persona.getDni().toString()));
			}
			
		} catch (Exception e) {
			throw e;
        } 
		return true;
	}
	
	public Boolean delete(Persona persona) throws ExcepcionPersonaInexistente, ExcepcionPersonaViolaIntegridadReferencial {
		
		try {
			if (persona == null) 
				throw new ExcepcionPersonaInexistente("Persona inexistente"); 
			
			//Me fijo que  exista la Persona
			if (!this.exists(persona.getDni())) 
				throw new ExcepcionPersonaInexistente("No existe una persona con DNI " .concat(persona.getDni().toString()));

			//Me fijo que no exista como Testigo
			if (this.isTestigo(persona.getDni()))
				throw new ExcepcionPersonaViolaIntegridadReferencial("La persona con DNI " + persona.getDni().toString() + " es Testigo de una causa. No puede ser Eliminado");

			if (this.isImputado(persona.getDni()))
				throw new ExcepcionPersonaViolaIntegridadReferencial("La persona con DNI " + persona.getDni().toString() + " es Imputado de una causa. No puede ser Eliminado");
			
			Db.getConnection().delete(persona);
			return true;
			
			//Db.getInstance();
			//Db.getConnection().store(persona);
		} catch (Exception e) {
			throw e;
        } 
		//return true;
	}
	
	public List<Persona> listar (){
		Db.getInstance();
		List <Persona> personas =Db.getConnection().query(Persona.class);
		System.out.println("-----------------------");
		System.out.println("| Listado de Personas |");
		System.out.println("-----------------------");
		
		for (Persona persona : personas) {
			System.out.println(persona);
		}
		System.out.println("-----------------------");
		System.out.printf("Total %d Personas\n", personas.size());
		return personas;
	}
	
	public Boolean exists(final Long dni) {
		Boolean existe;
		
		Db.getInstance();
		List <Persona> personas = Db.getConnection().query(new Predicate<Persona>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 189422016090086047L;

			public boolean match(Persona persona) {
				return persona.getDni().equals(dni) ;
			}
		});
		
		existe = !personas.isEmpty();
		//db.close();
		return existe;
	}
	
	//Se fija si un DNI es testigo de alguna causa
	public Boolean isTestigo(final Long dni) {
		Db.getInstance();
		List <Causa> causas = Db.getConnection().query(new Predicate<Causa>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 189422016090086047L;

			public boolean match(Causa causa) {
				return causa.tieneTestigo(dni) ;
			}
		});
		
		return !causas.isEmpty();
	}
	
	//Se fija si un DNI es testigo de alguna causa
		public Boolean isImputado(final Long dni) {
			Db.getInstance();
			List <Causa> causas = Db.getConnection().query(new Predicate<Causa>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 189422016090086047L;

				public boolean match(Causa causa) {
					return causa.tieneImputado(dni) ;
				}
			});
			
			return !causas.isEmpty();
		}
	
	public Persona getPersonaByDNI(final Long dni) {
		Persona persona = null;
		Db.getInstance();
		
		List <Persona> personas = Db.getConnection().query(new Predicate<Persona>() {
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
		return persona;
	}
}
