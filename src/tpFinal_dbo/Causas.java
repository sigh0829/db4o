package tpFinal_dbo;

import java.util.List;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Causa.ExcepcionValidacion;

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
	
	public class ExcepcionCausaInexistente extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 14L;

		public ExcepcionCausaInexistente(String msg) {
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
	
	public Causa ingresoImputadoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int expediente;
		Long dni;
		Causa causa = new Causa();

		try {
			System.out.println("Agregar Imputado a Causa");
			System.out.println("------------------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Expediente: ");
					expediente = scanner.nextInt();
					causa = this.getCausaByNumero(expediente);
					if (causa == null) {
						System.out.println("No existe ninguna Causa con Expediente " + expediente + ".Intente nuevamente");
					} else {
						break;
					}
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("Datos de la Causa");
			System.out.println("-----------------");
			System.out.println(causa);
			scanner = new Scanner (System.in);
			
			while (true) {
				try {
					System.out.print("Ingrese el DNI del Imputado a agregar a la Causa o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0) {
						dni=Long.parseLong(texto);
						if (dni>0)
							causa.addImputado(dni);
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("El DNI del imputado debe ser numerico");
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			return causa;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Causa eliminacionImputadoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int expediente;
		Long dni;
		Causa causa = new Causa();

		try {
			System.out.println("Eliminar Imputado de Causa");
			System.out.println("--------------------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Expediente: ");
					expediente = scanner.nextInt();
					causa = this.getCausaByNumero(expediente);
					if (causa == null) {
						System.out.println("No existe ninguna Causa con Expediente " + expediente + ".Intente nuevamente");
					} else {
						break;
					}
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("Datos de la Causa");
			System.out.println("-----------------");
			System.out.println(causa);
			scanner = new Scanner (System.in);
			
			while (true) {
				try {
					System.out.print("Ingrese el DNI del Imputado a eliminar de la Causa o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0) {
						dni=Long.parseLong(texto);
						if (dni>0)
							causa.removeImputado(dni);
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("El DNI del imputado debe ser numerico");
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			return causa;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Causa ingresoTestigoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int expediente;
		Long dni;
		Causa causa = new Causa();

		try {
			System.out.println("Agregar Testigo a Causa");
			System.out.println("-----------------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Expediente: ");
					expediente = scanner.nextInt();
					causa = this.getCausaByNumero(expediente);
					if (causa == null) {
						System.out.println("No existe ninguna Causa con Expediente " + expediente + ".Intente nuevamente");
					} else {
						break;
					}
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("Datos de la Causa");
			System.out.println("-----------------");
			System.out.println(causa);
			scanner = new Scanner (System.in);
			
			while (true) {
				try {
					System.out.print("Ingrese el DNI del Testigo a agregar a la Causa o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0) {
						dni=Long.parseLong(texto);
						if (dni>0)
							causa.addTestigo(dni);
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("El DNI del Testigo debe ser numerico");
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			return causa;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Causa eliminacionTestigoPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int expediente;
		Long dni;
		Causa causa = new Causa();

		try {
			System.out.println("Eliminar Testigo de la Causa");
			System.out.println("----------------------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Expediente: ");
					expediente = scanner.nextInt();
					causa = this.getCausaByNumero(expediente);
					if (causa == null) {
						System.out.println("No existe ninguna Causa con Expediente " + expediente + ".Intente nuevamente");
					} else {
						break;
					}
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("Datos de la Causa");
			System.out.println("-----------------");
			System.out.println(causa);
			scanner = new Scanner (System.in);
			
			while (true) {
				try {
					System.out.print("Ingrese el DNI del Testigo a eliminar de la Causa o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0) {
						dni=Long.parseLong(texto);
						if (dni>0)
							causa.removeTestigo(dni);
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("El DNI del Testigo debe ser numerico");
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			return causa;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Causa eliminacionPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int expediente;
		Causa causa = new Causa();

		try {
			System.out.println("Baja de Causas");
			System.out.println("--------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Expediente: ");
					expediente = scanner.nextInt();
					causa = this.getCausaByNumero(expediente);
					if (causa == null) {
						System.out.println("No existe ninguna Causa con Expediente " + expediente + ".Intente nuevamente");
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
					System.out.println("Datos de la Causa");
					System.out.println("-----------------");
					System.out.println(causa);
					texto = "";
					while (!(texto.equalsIgnoreCase("s") || texto.equalsIgnoreCase("n"))) {
						System.out.print("Esta seguro que desea eliminar la Causa con Expediente: " + expediente + "? [s=Si, n=No]:" );
						texto = scanner.nextLine();
						if (texto.equalsIgnoreCase("s")) {
							return causa;
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
	
	public Causa ingresoSentenciaPorTeclado(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		String texto;
		int expediente;
		Causa causa = new Causa();

		try {
			System.out.println("Agregar Sentencia a Causa");
			System.out.println("-------------------------");
			while (true) {
				try {
					System.out.print("Ingrese Numero de Expediente: ");
					expediente = scanner.nextInt();
					causa = this.getCausaByNumero(expediente);
					if (causa == null) {
						System.out.println("No existe ninguna Causa con Expediente " + expediente + ".Intente nuevamente");
					} else {
						break;
					}
				} catch (RangeException e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("Datos de la Causa");
			System.out.println("-----------------");
			System.out.println(causa);
			scanner = new Scanner (System.in);
			while (true) {
				try {
					System.out.print("Ingrese nueva Sentencia o presione [ENTER] para continuar: ");
					texto = scanner.nextLine();
					if (texto.length()>0) {
							causa.setSentencia(texto);
					}
					break;
				} catch (ExcepcionValidacion e) {
					System.out.println(e.getMessage());
				}
			}	

			return causa;
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
			return null;
		} 
	}
	
	public Boolean add(Causa causa) throws ExcepcionCausaDuplicada, Exception {
		
		try {
			//Me fijo que no exista la Causa
			if (!this.exists(causa.getExpediente())) {
				Db.getInstance();
				Db.getConnection().store(causa);
				
				//Agrego la causa al Juzgado
				causa.getJuzgado().addCausa(causa.getExpediente());
				Db.getConnection().store(causa.getJuzgado());
				
			} else {
				throw new ExcepcionCausaDuplicada("Ya existe una causa con Numero de Expediente " .concat(Integer.toString(causa.getExpediente())));
			}
			
		} catch (Exception e) {
			throw e;
        } finally {
        	
        }
		return true;
	}
	
	public Boolean update(Causa causa) throws ExcepcionCausaInexistente, Exception {
		
		try {
			//Me fijo que  exista la Causa
			if (this.exists(causa.getExpediente())) {
				Db.getInstance();
				Db.getConnection().store(causa);
			} else {
				throw new ExcepcionCausaInexistente("No existe una Causa con Expediente " + causa.getExpediente());
			}
		} catch (Exception e) {
			throw e;
        } 
		return true;
	}
	
	public Boolean delete(Causa causa) throws ExcepcionCausaInexistente {	
		try {
			if (causa == null) 
				throw new ExcepcionCausaInexistente("Causa inexistente"); 
			
			Db.getConnection().delete(causa);
			return true;
			
		} catch (Exception e) {
			throw e;
        } 
	}
	
	public List<Causa> listar (){
		Db.getInstance();
		List <Causa> causas =Db.getConnection().query(Causa.class);
		System.out.println("-----------------------");
		System.out.println("| Listado de Causas |");
		System.out.println("-----------------------");
		
		for (Causa causa : causas) {
			System.out.println(causa);
		}
		System.out.println("-----------------------");
		System.out.printf("Total %d Causas\n", causas.size());
		return causas;
	}
	
	public Boolean exists(final int expediente) {
		Boolean existe;
		Db.getInstance();
		List <Causa> causas = Db.getConnection().query(new Predicate<Causa>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2269157464132657832L;

			public boolean match(Causa causa) {
				return (causa.getExpediente() == expediente);
			}
		});
		
		existe = !causas.isEmpty();
		return existe;
	}
	
	public Causa getCausaByNumero(final int expediente) {
		Db.getInstance();
		Causa causa = null;
		
		List <Causa> causas = Db.getConnection().query(new Predicate<Causa>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1566089738197908362L;

			public boolean match(Causa causa) {
				return (causa.getExpediente() == expediente);
			}
		});
		
		if (!causas.isEmpty()) {
			causa = causas.get(0);
		}
		return causa;
	}
}
