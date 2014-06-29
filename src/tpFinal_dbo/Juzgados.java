package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Juzgado.ExcepcionValidacion;

import com.db4o.query.Predicate;

public class Juzgados {
	
	public class ExcepcionJuzgadoDuplicado extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 3L;

		public ExcepcionJuzgadoDuplicado(String msg) {
	        super(msg);
	    }
	}

	
	public Juzgado ingresoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int numero;
		Long matricula;
		Juzgado juzgado = new Juzgado();

		try {
			while (true) {
				try {
					System.out.print("Ingrese Numero: ");
					numero = scanner.nextInt();
					juzgado.setNumero(numero);
					break;
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
	
			while (true) {
				try {
					System.out.print("Ingrese Fuero [c=Civil o=Comercial l=Laboral p=Penal]: ");
					scanner = new Scanner (System.in);
					texto = scanner.nextLine();
					juzgado.setFuero(texto.toLowerCase());
					break;
				} catch (Juzgado.ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	
							
							
			while (true) {
				try {
					System.out.print("Ingrese la Matricula del Juez de Instancia: ");
					matricula = scanner.nextLong();
					juzgado.setJuez(matricula);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.print("Ingrese Domicilio: ");
					scanner = new Scanner (System.in);
					texto = scanner.nextLine();
					juzgado.setDomicilio(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.print("Ingrese Localidad: ");
					texto = scanner.nextLine();
					juzgado.setLocalidad(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}


			return juzgado;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Boolean add(Juzgado juzgado) throws ExcepcionJuzgadoDuplicado {
		
		try {
			//TODO: Verificar por numero, fuero y localidad
			//Me fijo que no exista el Juzgado
			if (!this.exists(juzgado.getNumero())) {
				Db.getInstance();
				Db.getConnection().store(juzgado);
			} else {
				throw new ExcepcionJuzgadoDuplicado("Ya existe un Juzgado con Numero " .concat(Integer.toString(juzgado.getNumero())));
			}		
		} catch (Exception e) {
			throw e;
        } finally {
        	
        }
		return true;
	}
	
	public List<Juzgado> listar (){
		Db.getInstance();
		List <Juzgado> juzgados =Db.getConnection().query(Juzgado.class);
		System.out.println("-----------------------");
		System.out.println("| Listado de Juzgados |");
		System.out.println("-----------------------");
		
		for (Juzgado juzgado : juzgados) {
			System.out.println(juzgado);
		}
		System.out.println("-----------------------");
		System.out.printf("Total %d Juzgados\n", juzgados.size());
		return juzgados;
	}
	
	public Boolean exists(final int i) {
		Boolean existe;
		Db.getInstance();
		
		List <Juzgado> juzgados = Db.getConnection().query(new Predicate<Juzgado>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3074958108904637878L;

			public boolean match(Juzgado juzgado) {
				return juzgado.getNumero() == i;
			}
		});
		
		existe = !juzgados.isEmpty();
		return existe;
	}
	
	public Juzgado getJuzgadoByNumero(final int i) {
		Juzgado juzgado = null;
		Db.getInstance();
		
		List <Juzgado> juzgados = Db.getConnection().query(new Predicate<Juzgado>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3074958108904637878L;

			public boolean match(Juzgado juzgado) {
				return juzgado.getNumero() == i;
			}
		});
		
		if (!juzgados.isEmpty()) {
			juzgado = juzgados.get(0);
		}
		return juzgado;
	}
}
