package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Juez.ExcepcionValidacion;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class Jueces {
	
	public class ExcepcionJuezDuplicado extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExcepcionJuezDuplicado(String msg) {
	        super(msg);
	    }
	}
	
	public class ExcepcionJuezInexistente extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 11L;

		public ExcepcionJuezInexistente(String msg) {
	        super(msg);
	    }
	}
	
	public class ExcepcionJuezViolaIntegridadReferencial extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 21L;

		public ExcepcionJuezViolaIntegridadReferencial(String msg) {
	        super(msg);
	    }
	}

	
	public Juez ingresoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int matricula;
		Juez juez = new Juez();

		try {
			while (true) {
				try {
					System.out.print("Ingrese Nombre: ");
					texto = scanner.nextLine();
					juez.setNombre(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}

			while (true) {
				try {
					System.out.print("Ingrese Matricula Profesional: ");
					matricula = scanner.nextInt();
					juez.setMatricula(matricula);
					break;
				} catch (RangeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.print("Ingrese Trayectoria: ");
					scanner = new Scanner (System.in);
					texto = scanner.nextLine();
					juez.setTrayectoria(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}


			return juez;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Juez modificacionPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int matricula;
		Juez juez = new Juez();

		try {
			System.out.println("Modificacion de Jueces");
			System.out.println("----------------------");
			while (true) {
				try {
					System.out.print("Ingrese Matricula: ");
					matricula = scanner.nextInt();
					juez = this.getJuezByMatricula(matricula);
					if (juez == null) {
						System.out.println("No existe ningun juez con Matricula " + matricula + ".Intente nuevamente");
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
					System.out.println("Nombre actual: " + juez.getNombre());
					System.out.print("Ingrese nuevo Nombre o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
						juez.setNombre(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}

			while (true) {
				try {
					System.out.println("Trayectoria actual: " + juez.getTrayectoria());
					System.out.print("Ingrese nueva Trayectoria o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
						juez.setTrayectoria(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			return juez;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Juez eliminacionPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int matricula;
		Juez juez = new Juez();

		try {
			System.out.println("Baja de Jueces");
			System.out.println("--------------");
			while (true) {
				try {
					System.out.print("Ingrese Matricula: ");
					matricula = scanner.nextInt();
					juez = this.getJuezByMatricula(matricula);
					if (juez == null) {
						System.out.println("No existe ningun Juez con Matricula " + matricula + ".Intente nuevamente");
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
						System.out.print("Esta seguro que desea eliminar al Juez: " + juez + "? [s=Si, n=No]:" );
						texto = scanner.nextLine();
						if (texto.equalsIgnoreCase("s")) {
							return juez;
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
	
	public Boolean add(Juez juez) throws ExcepcionJuezDuplicado {
		
		try {

			//Me fijo que no exista el Juez
			if (!this.exists(juez.getMatricula())) {
				Db.getInstance();
				Db.getConnection().store(juez);
			} else {
				throw new ExcepcionJuezDuplicado("Ya existe un juez con Matricula Profesional " + juez.getMatricula());
			}

			
		} catch (Exception e) {
			throw e;
        } finally {
        	
        }
		return true;
	}
	
public Boolean update(Juez juez) throws ExcepcionJuezInexistente, Exception {
		
		try {
			//Me fijo que  exista el Juez
			if (this.exists(juez.getMatricula())) {
				Db.getInstance();
				Db.getConnection().store(juez);
			} else {
				throw new ExcepcionJuezInexistente("No existe un Juez con Matricula " + juez.getMatricula());
			}
			
		} catch (Exception e) {
			throw e;
        } 
		return true;
	}
	
	public Boolean delete(Juez juez) throws ExcepcionJuezInexistente, ExcepcionJuezViolaIntegridadReferencial {
		
		try {
			if (juez == null) 
				throw new ExcepcionJuezInexistente("Juez inexistente"); 
			
			//Me fijo que  exista el Juez
			if (!this.exists(juez.getMatricula())) 
				throw new ExcepcionJuezInexistente("No existe un Juez con Matricula " + juez.getMatricula());

			//Me fijo que no exista como Juez de algun juzgado
			if (this.isJuezACargoDeJuzgado(juez.getMatricula()))
				throw new ExcepcionJuezViolaIntegridadReferencial("el Juez con Matricula " + juez.getMatricula() + " es Juez a cargo de un Juzgado. No puede ser Eliminado");
			
			Db.getConnection().delete(juez);
			return true;
			
		} catch (Exception e) {
			throw e;
        } 
	}
	
	public List<Juez> listar (){
		Db.getInstance();
		List <Juez> jueces =Db.getConnection().query(Juez.class);
		System.out.println("-----------------------");
		System.out.println("| Listado de Jueces |");
		System.out.println("-----------------------");
		
		for (Juez juez : jueces) {
			System.out.println(juez);
		}
		System.out.println("-----------------------");
		System.out.printf("Total %d Jueces\n", jueces.size());
		return jueces;
	}
	
	public Boolean exists(final int matricula) {
		Boolean existe;
		
		Db.getInstance();
		List <Juez> jueces = Db.getConnection().query(new Predicate<Juez>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2269157464132657832L;

			public boolean match(Juez juez) {
				return juez.getMatricula() == matricula;
			}
		});
		
		existe = !jueces.isEmpty();
		return existe;
	}
	
	public Juez getJuezByMatricula(final int matricula) {
		Juez juez = null;
		
		Db.getInstance();
		List <Juez> jueces = Db.getConnection().query(new Predicate<Juez>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7121429855479120572L;

			public boolean match(Juez juez) {
				return juez.getMatricula() == matricula;
			}
		});
		
		if (!jueces.isEmpty()) {
			juez = jueces.get(0);
		}
		return juez;
	}
	
	public Boolean isJuezACargoDeJuzgado(int matricula) {
		Db.getInstance();

    	Query query=Db.getConnection().query();
    	query.constrain(Juzgado.class);
    	
    	query.descend("juez").descend("matricula").constrain(matricula);
    	
    	ObjectSet<Juzgado> juzgados=query.execute();
    	
    	return !juzgados.isEmpty();
		
	}
}
