package tpFinal_dbo;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class Listados {

    public void causasConMas2Imputados_QBE() {
    	try {
    		int cant = 0;
    		Db.getInstance();
			//creo un arraylist  de personas imputadas
			ArrayList<Persona> imputados= new ArrayList<Persona>();
			imputados.add(new Persona(null, null, null, null, null));
			imputados.add(new Persona(null, null, null, null, null));
			
			ArrayList<Persona> testigos= new ArrayList<Persona>();

			Juez juez = new Juez(null, 0, null);
			
			//creo dos personas y las agrego al arraylist
			Juzgado juzgado = new Juzgado(0, null, juez, null, null);

			Causa causa = new Causa(0, juzgado, imputados, testigos, null);
			causa.setImputados(imputados);

			final ObjectSet<Causa> causas= Db.getConnection().queryByExample(causa);
			
			System.out.println("Listado de Causas con sentencia que tengan mas de 2 imputados (QBE)");
			System.out.println("-------------------------------------------------------------------");
			for (Causa c : causas) {
				if (c.imputados.size()>=2 && c.getSentencia() != null) { //Sino no funca
					cant++;
					System.out.println(c);
				}
					
			}
			System.out.printf("\nSe encontraron %d casos\n", cant);
			System.out.println("-------------------------------------------------------------------");

		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }

    public void juzgadosFueroCivil_QBE() {
    	try {
    		int cant=0;
    		Db.getInstance();
			Juzgado juzgadoProt = new Juzgado(0, "c", null, null, null);;

			final ObjectSet<Juzgado> juzgados= Db.getConnection().queryByExample(juzgadoProt);
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("Listado de juzgados del fuero civil con al menos una causa con sentencia y una causa sin sentencia (QBE)");
			System.out.println("--------------------------------------------------------------------------------------------------------");
			for (Juzgado juzgado : juzgados) {
				//Me fijo que tenga causa con sentencia y causa sin sentencia
				if (juzgado.getCantCausasConSentencia()>0 && juzgado.getCantCausasSinSentencia()>0) {
					cant++;
					System.out.println(juzgado);
				}
			}
			System.out.printf("\nSe encontraron %d casos\n", cant);
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }
    
    public void causasConMas2Imputados_NQ() {
    	try {
    		Db.getInstance();

    		List <Causa> causas = Db.getConnection().query(new Predicate<Causa>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = -5928039920141808584L;

				public boolean match(Causa causa) {
					return (causa.getImputados().size() >= 2 && !causa.getSentencia().equals(null));
				}
			});
			System.out.println("------------------------------------------------------------------");
			System.out.println("Listado de Causas con sentencia que tengan mas de 2 imputados (NQ)");
			System.out.println("------------------------------------------------------------------");
			for (Causa c : causas) {
				System.out.println(c);
			}
			System.out.printf("\nSe encontraron %d casos\n", causas.size());
			System.out.println("-------------------------------------------------------------------");

			
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }
    
    public void juzgadosFueroCivil_NQ() {
    	try {
    		Db.getInstance();

			List <Juzgado> juzgados = Db.getConnection().query(new Predicate<Juzgado>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 5406016042927709882L;

				public boolean match(Juzgado juzgado) {
					return (juzgado.getFuero().equalsIgnoreCase("c") && juzgado.getCantCausasConSentencia() >0 && juzgado.getCantCausasSinSentencia() >0 );
				}
			});
			System.out.println("-------------------------------------------------------------------------------------------------------");
			System.out.println("Listado de juzgados del fuero civil con al menos una causa con sentencia y una causa sin sentencia (NQ)");
			System.out.println("-------------------------------------------------------------------------------------------------------");
			for (Juzgado juzgado : juzgados) {
				System.out.println(juzgado);
			}
			System.out.printf("\nSe encontraron %d casos\n", juzgados.size());
			System.out.println("-------------------------------------------------------------------------------------------------------");

		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }
    
    public void causasConMas2Imputados_SODA() {
    	int cant=0;
    	Db.getInstance();

    	Query query=Db.getConnection().query();
    	query.constrain(Causa.class);
    	
    	query.descend("sentencia").constrain(null).not().and(query.descend("imputados").descend("size").constrain(new Integer(2)).greater());
    	
    	ObjectSet<Causa> causas=query.execute();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Listado de Causas con sentencia que tengan mas de 2 imputados (SODA)");
		System.out.println("--------------------------------------------------------------------");
		for (Causa c : causas) {
			if (c.imputados.size()>=2) { //Sino no funciona!
				cant++;
				System.out.println(c);
			}
		}
		System.out.printf("\nSe encontraron %d casos\n", cant);
		System.out.println("--------------------------------------------------------------------");
    	
    }
    
    public void juzgadosFueroCivil_SODA() {
    	int cant=0;
    	Db.getInstance();

    	Query query=Db.getConnection().query();
    	query.constrain(Juzgado.class);
    	
    	query.descend("fuero").constrain("c");
    	
    	ObjectSet<Juzgado> juzgados=query.execute();
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("Listado de juzgados del fuero civil con al menos una causa con sentencia y una causa sin sentencia (SODA)");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		for (Juzgado juzgado: juzgados) {
			if (juzgado.getCantCausasConSentencia()>0 && juzgado.getCantCausasSinSentencia()>0) { //Sino no funca
				cant++;
				System.out.println(juzgado);
			}
		}
		System.out.printf("\nSe encontraron %d casos\n", cant);
		System.out.println("---------------------------------------------------------------------------------------------------------");
    	
    }
}
