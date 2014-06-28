package tpFinal_dbo;

import org.w3c.dom.ranges.RangeException;

import tpFinal_dbo.Juez.ExcepcionValidacion;

public class Juzgado {
	public class ExcepcionValidacion extends Exception {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 3L;

		public ExcepcionValidacion(String msg) {
	        super(msg);
	    }
	}
	
	enum Fuero {civil, comercial, laboral, penal}
	
	private int numero;
	private Fuero fuero;
	private Juez juez;
	private String domicilio;
	private String localidad;
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
	public Fuero getFuero() {
		return fuero;
	}
	/**
	 * @param fuero the fuero to set
	 * @throws ExcepcionValidacion 
	 */
	public void setFuero(Fuero fuero) throws ExcepcionValidacion {
		if (fuero==null)
			throw new ExcepcionValidacion("Debe especificar un Fuero");
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
	 * @param matrocula la metricula del Juez to set
	 * @throws ExcepcionValidacion 
	 */
	public void setJuez(Long matricula) throws ExcepcionValidacion {
		Jueces jueces = new Jueces();
		Juez juez = jueces.getJuezByMatricula(matricula);
		
		if (juez == null) {
			throw new ExcepcionValidacion("No Existe un Juez con Matricula " .concat(matricula.toString()));
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
			case civil:
				fuero = "Civil";
				break;
			case comercial:
				fuero = "Comercial";
				break;
			case laboral:
				fuero = "Laboral";
				break;
			case penal:
				fuero = "Penal";
				break;
			default:
				fuero = "xxx";
			}
		return ("Juzgado Nro " + this.getNumero() + " en lo " + fuero + " - " + this.getLocalidad() + " - Juez " + this.getJuez().getNombre());
	}
	
}
