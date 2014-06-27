package tpFinal_dbo;

import java.util.Date;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class tpFinal_dbo {
	public static int menu(){
		//Displays the main menu and handles passing off to next menu. 

		Scanner scanner = new Scanner (System.in);
		int selection=0;
		int i=0;

		while(i==0) {   //changed while(1) to a value that didn't complain in netbeans
			System.out.println("Seleccione una Opcion:"); 
			System.out.println("[1] Personas"); 
			System.out.println("[2] Causas"); 
			System.out.println("[0] Salir"); 
			System.out.println("Opcion: "); 
			selection=scanner.nextInt();     

			switch (selection){

			case 1:
				System.out.println("ABM de Personas");
				break;       

			case 2:
				System.out.println("ABM de Causas");
				i=1;
				break;

			case 0:System.out.println("Saliendo del Sistema...");
			scanner.close();
			System.exit(5);

			default:System.out.println("Opcion invalida. Intente de nuevo");

			};

		}  
		return selection;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
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
