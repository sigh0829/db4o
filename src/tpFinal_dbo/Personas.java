package tpFinal_dbo;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Personas {
	public Boolean add(Persona persona) {
		ObjectContainer container = Db4oEmbedded.openFile("db/databaseFile.db4o");
		try {

			//Me fijo que no exista la Persona
			if (!this.exists(persona.getDni())) {
				container.store(persona);
			} else {
				throw new Exception("Ya existe una persona con DNI " .concat(persona.getDni()));
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
        } finally {
            container.close();
        }
		return true;
	}
	
	public Boolean exists(String dni) {
		return false;
	}
}
