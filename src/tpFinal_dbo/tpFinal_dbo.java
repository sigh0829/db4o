package tpFinal_dbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import tpFinal_dbo.Personas.ExcepcionPersonaDuplicada;

public class tpFinal_dbo {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Personas personasAux = new Personas();
		//Persona persona = new Persona("Iglesias", "Matias", "26192645", "m", "29/09/1977");
		//personasAux.add(persona);
		MainMenu menu = new MainMenu();
		MainMenu.retorno retorno = null;
		while (retorno != MainMenu.retorno.Salir) {
			try {
				retorno = menu.show();
				switch (retorno) {
				case PersonaAlta:
					Personas personas = new Personas();
					personas.add(personas.ingresoPorTeclado());
					break;

				default:
					break;
				}

			} catch (ExcepcionPersonaDuplicada e) {
				System.out.printf("Validacion de datos: %s\n",e.getMessage());
			}catch (Exception e) {
				// TODO: handle exception
				System.out.printf("ERROR EN EL SISTEMA: %s\n",e);
			}
		}

	}

}
