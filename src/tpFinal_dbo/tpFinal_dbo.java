package tpFinal_dbo;

import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class tpFinal_dbo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        ObjectContainer container = Db4oEmbedded.openFile("db/databaseFile.db4o");
        try {
    		Causa causa = new Causa();
    		Persona persona = new Persona("matias", "iglesias", "26192645", Persona.Sexo.masculino,new Date(1977, 9, 29));
    		container.store(persona);
    		causa.imputados.add(persona);
    		causa.testigos.add(persona);
    		container.store(causa);       
        } finally {
            container.close();
        }
	}

}
