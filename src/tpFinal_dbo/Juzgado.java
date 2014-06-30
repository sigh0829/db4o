package tpFinal_dbo;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ranges.RangeException;

import com.db4o.query.Predicate;

public class Juzgado {
	/**
	 * 
	 */
	public Juzgado() {
		super();
		this.causas = new ArrayList <Causa>();
	}
	/**
	 * @param numero
	 * @param fuero
	 * @param juez
	 * @param domicilio
	 * @param localidad
	 */
	public Juzgado(int numero, String fuero, Juez juez, String domicilio,
			String localidad) {
		super();
		this.numero = numero;
		this.fuero = fuero;
		this.juez = juez;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.causas = new ArrayList <Causa>();
	}

	public class ExcepcionValidacion extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 3L;

		public ExcepcionValidacion(String msg) {
	        super(msg);
	    }
	}
		
	private int numero;
	private String fuero;
	private Juez juez;
	private String domicilio;
	private String localidad;
	ArrayList <Causa> causas;
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 * @throws ExcepcionValidacion 
	 */
	public void setNumero(int numero) throws ExcepcionValidacion {
		if (numero<=0)
			throw new ExcepcionValidacion("Debe especificar un Numero");
		if (numero>32767)
			throw new RangeException((short) 0,"Numero invalido");
		this.numero = numero;
	}
	/**
	 * @return the fuero
	 */
	public String getFuero() {
		return fuero;
	}
	/**
	 * @param fuero the fuero to set
	 * @throws ExcepcionValidacion 
	 */
	public void setFuero(String fuero) throws ExcepcionValidacion {
		if (!(fuero.equalsIgnoreCase("c") || fuero.equalsIgnoreCase("o") || fuero.equalsIgnoreCase("l") || fuero.equalsIgnoreCase("p"))) {
			throw new ExcepcionValidacion("Fuero debe ser 'c', 'o', 'l' o 'p'");
		}
		this.fuero = fuero;
	}
	/**
	 * @return the juez
	 */
	public Juez getJuez() {
		return juez;
	}
	/**
	 * @param juez the juez to set
	 */
	public void setJuez(Juez juez) {
		this.juez = juez;
	}
	/**
	 * @param matricula la matricula del Juez to set
	 * @throws ExcepcionValidacion 
	 */
	public void setJuez(int matricula) throws ExcepcionValidacion {
		Jueces jueces = new Jueces();
		Juez juez = jueces.getJuezByMatricula(matricula);
		
		if (juez == null) {
			throw new ExcepcionValidacion("No Existe un Juez con Matricula " + matricula);
		}
		this.setJuez(juez);
	}
	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio the domicilio to set
	 * @throws ExcepcionValidacion 
	 */
	public void setDomicilio(String domicilio) throws ExcepcionValidacion {
		if (domicilio.length()==0)
			throw new ExcepcionValidacion("Debe especificar un Domicilio");
		this.domicilio = domicilio;
	}
	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad the localidad to set
	 * @throws ExcepcionValidacion 
	 */
	public void setLocalidad(String localidad) throws ExcepcionValidacion {
		if (localidad.length()==0)
			throw new ExcepcionValidacion("Debe especificar una Localidad");
		this.localidad = localidad;
	}

	public String toString() {
		String fuero = "";
			switch (this.getFuero()) {
			case "c":
				fuero = "Civil";
				break;
			case "o":
				fuero = "Comercial";
				break;
			case "l":
				fuero = "Laboral";
				break;
			case "p":
				fuero = "Penal";
				break;
			default:
				fuero = "xxx";
			}
		return ("Juzgado Nro " + this.getNumero() + " en lo " + fuero + " - " + this.getDomicilio() + " - " + this.getLocalidad() + " - Juez " + this.getJuez().getNombre() + " - [CAUSAS con sentencia: " + this.getCantCausasConSentencia() + " - sin sentencia: " + this.getCantCausasSinSentencia() + "]");
	}
	/**
	 * @return the causas
	 */
	public ArrayList<Causa> getCausas() {
		return causas;
	}
	/**
	 * @param causas the causas to set
	 */
	public void setCausas(ArrayList<Causa> causas) {
		this.causas = causas;
	}
	
	/**
	 * @param expediente el numero de expediente to set
	 * @throws ExcepcionValidacion 
	 */
	public void addCausa(int expediente) throws ExcepcionValidacion   {
		Causas causas = new Causas();
		Causa causa = causas.getCausaByNumero(expediente);
		
		if (causa == null) {
			throw new ExcepcionValidacion("No Existe una Causa con expediente "  + expediente);
		}
			
		this.getCausas().add(causa);		
	}
	
	public int getCantCausasConSentencia() {
		final int numero = this.getNumero();
		int cant = 0;
		Db.getInstance();
        List<Causa> causas = Db.getConnection().query(new Predicate<Causa>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4911269996887789011L;

			public boolean match(Causa causa) {
                return (causa.getJuzgado().getNumero() == numero && causa.getSentencia() != null);
            }
        });
        
         cant=causas.size();

        return cant;
	}
	
	public int getCantCausasSinSentencia() {
		Db.getInstance();
		final int numero = this.getNumero();
		int cant = 0;
        List<Causa> causas = Db.getConnection().query(new Predicate<Causa>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7379209058218375430L;

			public boolean match(Causa causa) {
                return (causa.getJuzgado().getNumero() == numero  && causa.getSentencia() == null);
            }
        });
        
         cant=causas.size();

        return cant;
	}
	
}
