package tpFinal_dbo;

import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class Listados {

    public void causasConMas2Imputados_QBE() {
    	try {
    		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
			//creo un arraylist  de personas imputadas
			ArrayList<Persona> imputados= new ArrayList<Persona>();
			imputados.add(new Persona());
			imputados.add(new Persona());

			//Juez juez = new Juez(null, null, null);
			
			//creo dos personas y las agrego al arraylist
			//Juzgado juzgado = new Juzgado(0, null, juez, null, null);

			Causa causa = new Causa();
			causa.setImputados(imputados);

			final ObjectSet<Causa> causas= db.queryByExample(causa);
			
			System.out.println("Listado de Causas con sentencia que tengan mas de 2 imputados (QBE)");
			System.out.println("-------------------------------------------------------------------");
			for (Causa c : causas) {
				if (c.imputados.size()>=2 && c.getSentencia() != null) //Sino no funca
				System.out.println(c);
			}
			//System.out.printf("\nSe encontraron %d casos\n", causas.size());
			System.out.println("-------------------------------------------------------------------");

			
    		db.close();
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }

    public void juzgadosFueroCivil_QBE() {
    	try {
    		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
			Juzgado juzgadoProt = new Juzgado(0, "c", null, null, null);;

			final ObjectSet<Juzgado> juzgados= db.queryByExample(juzgadoProt);
			db.close();
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("Listado de juzgados del fuero civil con al menos una causa con sentencia y una causa sin sentencia (QBE)");
			System.out.println("--------------------------------------------------------------------------------------------------------");
			for (Juzgado juzgado : juzgados) {
				//Me fijo que tenga causa con sentencia y causa sin sentencia
				System.out.println(juzgado);
				System.out.println("Causas con sentencia: " + juzgado.getCantCausasConSentencia());
				System.out.println("Causas sin sentencia: " + juzgado.getCantCausasSinSentencia());
			}
			System.out.printf("\nSe encontraron %d casos\n", juzgados.size());
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
    		db.close();
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }
    
    public void causasConMas2Imputados_NQ() {
    	try {
    		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");

    		List <Causa> causas = db.query(new Predicate<Causa>() {
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

			
    		db.close();
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }
    
    public void juzgadosFueroCivil_NQ() {
    	try {
    		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");


			List <Juzgado> juzgados = db.query(new Predicate<Juzgado>() {


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

    		db.close();
		} catch (Exception e) {
			System.out.printf("ERROR EN EL SISTEMA: %s",e);
		}
    }
    
    public void causasConMas2Imputados_SODA() {
    	ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");

        // You can simple filter objects which have a certain field
		//Constraint constr=q.descend("sentencia").constrain(null).not;
        //query.descend("sentencia").constrain(null).not().and(arg0);
        //query.descend("imputados").descend("size").constrain(3).greater().and(query.descend("sentencia").constrain(null).not());
        //query.descend("imputados").descend("size").constrain(new Integer(3)).greater();

    	Query query=db.query();
    	query.constrain(Causa.class);
    	Query pointQuery=query.descend("imputados").descend("modCount");
    	query.descend("sentencia").constrain(null).not()
    	.and(pointQuery.constrain(new Integer(2)).greater());
    	ObjectSet<Causa> causas=query.execute();
		System.out.println("------------------------------------------------------------------");
		System.out.println("Listado de Causas con sentencia que tengan mas de 2 imputados (SODA)");
		System.out.println("------------------------------------------------------------------");
		for (Causa c : causas) {
			if (c.imputados.size()>=2) //Sino no funciona!
			System.out.println(c);
		}
		//System.out.printf("\nSe encontraron %d casos\n", causas.size());
		System.out.println("-------------------------------------------------------------------");

		
		db.close();
    	
    }
    
    public void juzgadosFueroCivil_SODA() {
    	
    }
}
