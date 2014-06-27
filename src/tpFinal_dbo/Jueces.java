package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Juez.ExcepcionValidacion;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

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

	
	public Juez ingresoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		Long dni;
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
					dni = scanner.nextLong();
					juez.setMatricula(dni);
					break;
				} catch (RangeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.print("Ingrese Trayectoria: ");
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
	
	public Boolean add(Juez juez) throws ExcepcionJuezDuplicado {
		
		try {

			//Me fijo que no exista el Juez
			if (!this.exists(juez.getMatricula())) {
				ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
				db.store(juez);
				db.close();
			} else {
				throw new ExcepcionJuezDuplicado("Ya existe un juez con Matricula Profesional " .concat(juez.getMatricula().toString()));
			}

			
		} catch (Exception e) {
			throw e;
        } finally {
        	
        }
		return true;
	}
	
	public List<Juez> listar (){
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
		List <Juez> jueces =db.query(Juez.class);
		System.out.println("-----------------------");
		System.out.println("| Listado de Jueces |");
		System.out.println("-----------------------");
		
		for (Juez juez : jueces) {
			System.out.println(juez);
		}
		System.out.println("-----------------------");
		System.out.printf("Total %d Jueces\n", jueces.size());
		db.close();
		return jueces;
	}
	
	public Boolean exists(final Long matricula) {
		Boolean existe;
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
		
		List <Juez> jueces = db.query(new Predicate<Juez>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2269157464132657832L;

			public boolean match(Juez juez) {
				return juez.getMatricula().equals(matricula);
			}
		});
		
		existe = !jueces.isEmpty();
		db.close();
		return existe;
	}
}
