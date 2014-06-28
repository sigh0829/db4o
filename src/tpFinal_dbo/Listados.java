package tpFinal_dbo;

import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class Listados {

    public void causasConMas2Imputados_QBE() {
    	try {
    		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
			//creo un arraylist  de personas imputadas
			ArrayList<Persona> imputados= new ArrayList<Persona>();
			imputados.add(new Persona());
			imputados.add(new Persona());

			//creo dos personas y las agrego al arraylist

			Causa causa = new Causa(0, null, imputados, null, null);

			ObjectSet<Causa> causas= db.queryByExample(causa);
			
			System.out.println("Listado de Causas con sentencia que tengan mas de 2 imputados (QBE)");
			System.out.println("-------------------------------------------------------------------");
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

    public void juzgadosFueroCivil_QBE() {
    	
    }
    
    public void causasConMas2Imputados_NQ() {
    	try {
    		ObjectContainer db = Db4oEmbedded.openFile("databaseFile.db4o");
			//creo un arraylist  de personas imputadas
			ArrayList<Persona> imputados= new ArrayList<Persona>();
			imputados.add(new Persona());
			imputados.add(new Persona());

			//creo dos personas y las agrego al arraylist

			Causa causa = new Causa(0, null, imputados, null, null);

			List <Causa> causas = db.query(new Predicate<Causa>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 2269157464132657832L;

				public boolean match(Causa causa) {
					return (causa.getImputados().size() >= 2 && !causa.getSentencia().equals(null));
				}
			});
			
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
    	
    }
    
    public void causasConMas2Imputados_SODA() {
    	
    }
    
    public void juzgadosFueroCivil_SODA() {
    	
    }
}
