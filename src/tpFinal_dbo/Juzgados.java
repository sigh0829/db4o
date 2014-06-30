package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Jueces.ExcepcionJuezInexistente;
import tpFinal_dbo.Jueces.ExcepcionJuezViolaIntegridadReferencial;
import tpFinal_dbo.Juzgado.ExcepcionValidacion;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

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
	
	public class ExcepcionJuzgadoInexistente extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 13L;

		public ExcepcionJuzgadoInexistente(String msg) {
	        super(msg);
	    }
	}
	
	public class ExcepcionJuzgadoViolaIntegridadReferencial extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 23L;

		public ExcepcionJuzgadoViolaIntegridadReferencial(String msg) {
	        super(msg);
	    }
	}

	
	public Juzgado ingresoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int numero;
		int matricula;
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
					matricula = scanner.nextInt();
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
	
	public Juzgado modificacionPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int numero, matricula;
		Juzgado juzgado = new Juzgado();

		try {
			System.out.println("Modificacion de Juzgados");
			System.out.println("------------------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Juzgado: ");
					numero = scanner.nextInt();
					juzgado = this.getJuzgadoByNumero(numero);
					if (juzgado == null) {
						System.out.println("No existe ningun Juzgado con Numero de Juzgado " + numero + ".Intente nuevamente");
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
					System.out.println("Fuero actual: " + juzgado.getFuero());
					System.out.print("Ingrese nuevo Fuero [c=Civil o=Comercial l=Laboral p=Penal] o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
						juzgado.setFuero(texto.toLowerCase());
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.println("Juez actual: " + juzgado.getJuez());
					System.out.print("Ingrese Matricula del nuevo Juez o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0) {
						matricula=Integer.parseInt(texto);
						if (matricula>0)
							juzgado.setJuez(matricula);
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("La Matricula del Juez debe ser numerica");
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}

			scanner = new Scanner (System.in);
			while (true) {
				try {
					System.out.println("Domicilio actual: " + juzgado.getDomicilio());
					System.out.print("Ingrese nuevo Domicilio o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
						juzgado.setDomicilio(texto);
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try {
					System.out.println("Localidad actual: " + juzgado.getLocalidad());
					System.out.print("Ingrese nueva Localidad o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0)
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
	
	public Juzgado eliminacionPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int numero;
		Juzgado juzgado = new Juzgado();

		try {
			System.out.println("Baja de Juzgados");
			System.out.println("----------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Juzgado: ");
					numero = scanner.nextInt();
					juzgado = this.getJuzgadoByNumero(numero);
					if (juzgado == null) {
						System.out.println("No existe ningun Juzgado con Numero " + numero + ".Intente nuevamente");
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
						System.out.print("Esta seguro que desea eliminar el Juzgado: " + juzgado + "? [s=Si, n=No]:" );
						texto = scanner.nextLine();
						if (texto.equalsIgnoreCase("s")) {
							return juzgado;
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
	
	public Boolean update(Juzgado juzgado) throws ExcepcionJuzgadoInexistente, Exception {
		
		try {
			//Me fijo que  exista el Juzgado
			if (this.exists(juzgado.getNumero())) {
				Db.getInstance();
				Db.getConnection().store(juzgado);
			} else {
				throw new ExcepcionJuzgadoInexistente("No existe un Juzgado con Numero " + juzgado.getNumero());
			}
			
		} catch (Exception e) {
			throw e;
        } 
		return true;
	}
	
	public Boolean delete(Juzgado juzgado) throws ExcepcionJuzgadoInexistente, ExcepcionJuzgadoViolaIntegridadReferencial {
		
		try {
			if (juzgado == null) 
				throw new ExcepcionJuzgadoInexistente("Juzgado inexistente"); 
			
			//Me fijo que  exista el Juzgado
			if (!this.exists(juzgado.getNumero())) 
				throw new ExcepcionJuzgadoInexistente("No existe un Juzgado con Numero " + juzgado.getNumero());

			//Me fijo que no tenga Causas
			if (this.juzgadoTieneCausas(juzgado.getNumero()))
				throw new ExcepcionJuzgadoViolaIntegridadReferencial("El Juzgado Numero " + juzgado.getNumero() + " tiene Causas. No puede ser Eliminado");
			
			Db.getConnection().delete(juzgado);
			return true;
			
		} catch (Exception e) {
			throw e;
        } 
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
	
	public Boolean juzgadoTieneCausas(int numero) {
		Db.getInstance();

    	Query query=Db.getConnection().query();
    	query.constrain(Causa.class);
    	
    	query.descend("juzgado").descend("numero").constrain(numero);
    	
    	ObjectSet<Juzgado> causas=query.execute();
    	
    	return !causas.isEmpty();
		
	}
}
