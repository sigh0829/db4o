package tpFinal_dbo;

import java.util.Random;

public class Utils {
	public static int getRandomNumber(int min, int max) {
		Random r = new Random();
		return r.nextInt(max-min)+min;
		
	}
	
	public static String getRandomNombre() {
		String[] nombres = {"Aarón", "Aharón", "Abel", "Abelardo", "Adelard", "Abraham", "Abram", "Ada", "Adela", "Adelaida", "Adán", "Adam", "Adamo", "Adelelmo", "Adela", "Adelaida", "Adelia", "Adelina", "Adolfo", "Ataulfo", "Adulfo", "Adoración", "Adrián", "Adriano", "Adriana", "Ágata", "Águeda", "Agnes", "Aggie", "Agustín", "Agustina", "Aitor", "Alba", "Albano", "Alban", "Alberto", "Alberta", "Adalberto", "Alegría", "Alejandra", "Alexandra", "Alejandro", "Baltasar", "Baldasar", "Basilio", "Bárbara", "Bartolomé", "Bartolo", "Bastiano", "Bastián", "Beatriz", "Beatrix", "Bea", "Beca", "Begonia", "Begoña", "Bela", "Bella", "Belén", "Benigno", "Benigna", "Benito", "Benita", "Benedicto"};
		int n = getRandomNumber(0, nombres.length);
		return nombres[n];
	}
	
	public static String getRandomApellido() {
		String[] apellidos = {"Kristel", "huanosto", "garaban", "Mir", "Adame", "terceros", "marrufo", "urdangarin", "abrego", "Superlano", "asidah", "Urízar", "manel", "Alcalá", "panadell", "La Madrid", "Hierro", "Azcon", "Larrañaga", "Go", "Florez", "L'angley", "Rezabal", "Shepard", "Waclaw", "Sierralta", "Beore", "Nassar" , "Deano", "Carmona", "Baldizón", "Crucelegui", "Rahmaoui", "Baena", "Zailachi", "Somodevilla", "Anduaga", "Hoste", "Madrera", "Puco", "Gabarro", "Gabarron", "Gabart des jammonieres", "Gabas", "Gabasa", "Gabat", "Gabazo", "Gabb", "Gabbe", "Gabbema", "Gabber", "Gabbia de marchantonio", "Gabbis", "Gabe", "Gabea"};
		
		int n = getRandomNumber(0, apellidos.length);
		return apellidos[n];
	}
	
	public static Long getRandomDNI() {
		Random r = new Random();
		int minDNI = 10000;
		int maxDNI = 99000;
		int random = r.nextInt(maxDNI-minDNI)+minDNI;
		Long result = new Long(random) * 1000 + random/10;
		return result;
	}
	
	public static String getRandomSexo() {
		String[] sexo = {"m", "f"};
		int n = getRandomNumber(0, sexo.length);
		return sexo[n];
	}
}
