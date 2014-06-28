package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Causa.ExcepcionValidacion;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

public class Causas {
	
	public class ExcepcionCausaDuplicada extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 4L;

		public ExcepcionCausaDuplicada(String msg) {
	        super(msg);
	    }
	}

	
	public Causa ingresoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int numero;
		int expediente;
		Long dni;
		Causa causa = new Causa();

		try {
			while (true) {
				try {
					System.out.print("Ingrese Expediente: ");
					expediente = scanner.nextInt();
					causa.setExpediente(expediente);
					break;
				} catch (RangeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.print("Ingrese Numero de Juzgado: ");
					numero = scanner.nextInt();
					causa.setJuzgado(numero);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("A continuacion ingrese los imputados de la causa o [0 (cero)] para terminar");
			dni = 1L;
			while (!dni.equals(0L)) {
				try {
					System.out.print("Ingrese DNI del imputado: ");
					dni = scanner.nextLong();
					if (dni.equals(0L))
						break;
					causa.addImputado(dni);
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("A continuacion ingrese los testigos de la causa o [0 (cero)] para terminar");
			dni = 1L;
			while (!dni.equals(0L)) {
				try {
					System.out.print("Ingrese DNI del testigo: ");
					dni = scanner.nextLong();
					if (dni.equals(0L))
						break;
					causa.addTestigo(dni);
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.print("Ingrese Sentencia [o ENTER si aun no tiene sentencia]: ");
					scanner = new Scanner (System.in);
					texto = scanner.nextLine();
					causa.setSentencia(texto);
					break;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			return causa;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Boolean add(Causa causa) throws ExcepcionCausaDuplicada {
		
		try {

			//Me fijo que no exista la Causa
			if (!this.exists(causa.getExpediente())) {
				ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
				db.store(causa);
				db.close();
			} else {
				throw new ExcepcionCausaDuplicada("Ya existe una causa con Numero de Expediente " .concat(Integer.toString(causa.getExpediente())));
			}
			
		} catch (Exception e) {
			throw e;
        } finally {
        	
        }
		return true;
	}
	
	public List<Causa> listar (){
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
		List <Causa> causas =db.query(Causa.class);
		System.out.println("-----------------------");
		System.out.println("| Listado de Causas |");
		System.out.println("-----------------------");
		
		for (Causa causa : causas) {
			System.out.println(causa);
		}
		System.out.println("-----------------------");
		System.out.printf("Total %d Causas\n", causas.size());
		db.close();
		return causas;
	}
	
	public Boolean exists(final int i) {
		Boolean existe;
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
		
		List <Causa> causas = db.query(new Predicate<Causa>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2269157464132657832L;

			public boolean match(Causa causa) {
				return (causa.getExpediente() == i);
			}
		});
		
		existe = !causas.isEmpty();
		db.close();
		return existe;
	}
	
	public Causa getCausaByNumero(final int numero) {
		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
		Causa causa = null;
		
		List <Causa> causas = db.query(new Predicate<Causa>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1566089738197908362L;

			public boolean match(Causa causa) {
				return (causa.getExpediente() == numero);
			}
		});
		
		if (!causas.isEmpty()) {
			causa = causas.get(0);
		}
		db.close();
		return causa;
	}
}
