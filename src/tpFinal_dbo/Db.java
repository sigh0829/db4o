package tpFinal_dbo;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
/*
 * Singleton con la Conexion a la Base de Datos
 * Tuve que hacer esto para evitar que se dupliquen los objetos
 * 
 * Más info de Identity Concept
 * http://community.versant.com/Documentation/Reference/db4o-8.1/java/reference/
 */

public class Db {
    private static Db INSTANCE = new Db();
    private static ObjectContainer db;

    private Db() {
    	EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
    	configuration.common().objectClass(Persona.class).objectField("dni").indexed(true);
    	configuration.common().objectClass(Juez.class).objectField("matricula").indexed(true);
    	configuration.common().objectClass(Juzgado.class).objectField("numero").indexed(true);
    	configuration.common().objectClass(Causa.class).objectField("expediente").indexed(true);
    	
    	db = Db4oEmbedded.openFile(configuration, "databaseFile.db4o"); 	
    }
    
    public static ObjectContainer getConnection () {
    	return db;
    }
 
    public static Db getInstance() {
        return INSTANCE;
    }
    
    protected void finalize() throws Throwable {
    	super.finalize();
    	db.close();
    }
}
