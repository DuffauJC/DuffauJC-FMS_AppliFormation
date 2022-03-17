
import java.util.ArrayList;	
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MainApp {

	//  hashmap liste présentation des formations
	protected static Map<Integer,ArrayList<String>> formationList = new HashMap<>();
	//  hashmap du choix des formations
	protected static Map<Integer,ArrayList<String>> formationChoix = new HashMap<>();
	//  hashmap du panier
	protected static Map<Integer,ArrayList<String>> caddyList = new HashMap<>();

	protected static Scanner scan = new Scanner(System.in); 




	public static void main(String[] args) {

		insertFormationList();
		formationChoix();
		mainFunction();
		scan.close();
	}




	/** Méthode principale qui s'execute dans le main */
	private static void mainFunction() {

		System.out.println("	Bonjour, bienvenue dans mon application Fulltrainings.");
		System.out.println("Nous allons vous proposer une liste de formations actuellement disponible.");

		int ans=0;

		while(ans != 4) {
			// Affichage du menu
			showMenu();

			while(!scan.hasNextInt())	scan.next();
			ans = scan.nextInt();

			switch(ans) {
			case 1 : // Lecture de la liste des formations
				displayFormations();
				break;

			case 2 : // Ajouter une formation par nom du cours  ex : Java
				findFormation();
				break;

			case 3 : 	// Afficher panier
				displayCaddy();
				break;

			case 4 : // Exit application
				System.out.println("Good Bye.");
				break;

			default : System.out.println("mauvaise saisie");
			}			
		}	

	}
	/** Méthode qui affiche le menu  */
	public static void showMenu() {
		System.out.println("Menu :\n");
		System.out.println("1.Voulez-vous voir la formations ?");
		System.out.println("2.Ajouter une formation au panier en fonction du cours.");
		System.out.println("3.Afficher le panier.");
		System.out.println("4.Sortir de l'application.\n");
	}

	/** Méthode qui recherche une formation dans la liste contenant un mot clé saisi au clavier */
	public static void findFormation() {
		displayFormationsChoix();

		System.out.println("Entrée le choix du cours ex : 1");
		String keyword=scan.next();

		System.out.println("Entrée la quantité ex : 6");
		String qty=scan.next();

		formationChoix.forEach((key,value)->{	
			if(value.get(0).contains(keyword)) { 
				// ajout de la quantité dans l'arraylist avant ajout au caddy
				value.add(qty);
				addFormationCaddy(value);
			}					
		});

	}

	/** Méthode qui ajoute une formation au panier
	 * @param value  */
	public static void addFormationCaddy(ArrayList<String> value) {

		
		int key=1;
		int size;
		// le caddy est-il vide ?
		if (caddyList.isEmpty()){
			caddyList.put(key,value);
		}

		// sinon on récupère la taille du hashmap + incrémentation } 
		else { 
			size=caddyList.size();
			key=size+1; 
			caddyList.put(key, value);

		}
	}

	/** Méthode qui affiche le panier  */
	public static void displayCaddy() {
		
		System.out.println("Caddy.\n");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.printf("| %-5s| %-15s | %-5s | %-40s |%-5s | %-2s |%n", "CHOIX", "COURS", "NB/JOURS", "DESCRIPTION"," PRIX","QTE");
		System.out.println("|------|-----------------|----------|------------------------------------------|------|-----|");

			for (int i = 1; i < caddyList.size()+1; i++) {
				System.out.printf("| %-5s| %-15s | %-8s | %-40s | %-3s |  %-2s |%n",caddyList.get(i).get(0), 
						caddyList.get(i).get(1),
						caddyList.get(i).get(2),caddyList.get(i).get(3),caddyList.get(i).get(4),caddyList.get(i).get(5));
			}			
		
		System.out.println("---------------------------------------------------------------------------------------------");

	}

	/** Méthode qui affiche la liste de toutes les formations  */
	public static void displayFormations() {

		System.out.println("Formations.\n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-5s | %-40s |%-5s |%n", "COURS", "NB/JOURS", "DESCRIPTION"," PRIX");
		System.out.println("|-----------------|----------|------------------------------------------|------|");

		for (int i = 1; i < formationList.size()+1; i++) {
			System.out.printf("| %-15s | %-8s | %-40s | %-3s |%n", formationList.get(i).get(0), formationList.get(i).get(1),
					formationList.get(i).get(2),formationList.get(i).get(3));
		}
		System.out.println("--------------------------------------------------------------------------------");

	}

	/** Méthode qui affiche la liste de toutes les formations pour l'ajout au panier  */
	public static void displayFormationsChoix() {

		System.out.println("Formations.\n");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("| %-5s | %-15s | %-5s | %-40s |%-5s |%n", "CHOIX", "COURS", "NB/JOURS", "DESCRIPTION"," PRIX");
		System.out.println("|-------|-----------------|----------|------------------------------------------|------|");

		for (int i = 1; i < formationChoix.size()+1; i++) {
			System.out.printf("| %-5s | %-15s | %-8s | %-40s | %-3s |%n", formationChoix.get(i).get(0), formationChoix.get(i).get(1),
					formationChoix.get(i).get(2),formationChoix.get(i).get(3),formationChoix.get(i).get(4));

		}
		System.out.println("----------------------------------------------------------------------------------------");

	}

	/** Méthode qui initialise la liste des formations */
	private static void insertFormationList() {
		formationList.put(1, new ArrayList<String>());
		formationList.get(1).add("Java"); 	
		formationList.get(1).add("20");		
		formationList.get(1).add("Java SE 8 : Syntaxe et POO");
		formationList.get(1).add("3000");

		formationList.put(2, new ArrayList<String>());
		formationList.get(2).add("Java avancé"); 	
		formationList.get(2).add("20");	
		formationList.get(2).add("Exceptions, fichiers, JDBC, thread");
		formationList.get(2).add("5000");

		formationList.put(3, new ArrayList<String>());
		formationList.get(3).add("Spring"); 	
		formationList.get(3).add("20");		
		formationList.get(3).add("Spring Core/MVC/Security");
		formationList.get(3).add("5000");

		formationList.put(4, new ArrayList<String>());
		formationList.get(4).add("Php frameworks"); 	
		formationList.get(4).add("15");		
		formationList.get(4).add("Symphony");
		formationList.get(4).add("2500");

		formationList.put(5, new ArrayList<String>());
		formationList.get(5).add("C#"); 	
		formationList.get(5).add("20");		
		formationList.get(5).add("DontNet Core");
		formationList.get(5).add("5000");
	}

	/** Méthode qui initialise la liste des formations */
	private static void formationChoix() {
		formationChoix.put(1, new ArrayList<String>());
		formationChoix.get(1).add("1");
		formationChoix.get(1).add("Java"); 
		formationChoix.get(1).add("20");		
		formationChoix.get(1).add("Java SE 8 : Syntaxe et POO");
		formationChoix.get(1).add("3000");

		formationChoix.put(2, new ArrayList<String>());
		formationChoix.get(2).add("2"); 
		formationChoix.get(2).add("Java avancé"); 	
		formationChoix.get(2).add("20");	
		formationChoix.get(2).add("Exceptions, fichiers, JDBC, thread");
		formationChoix.get(2).add("5000");

		formationChoix.put(3, new ArrayList<String>());
		formationChoix.get(3).add("3"); 
		formationChoix.get(3).add("Spring"); 	
		formationChoix.get(3).add("20");		
		formationChoix.get(3).add("Spring Core/MVC/Security");
		formationChoix.get(3).add("5000");

		formationChoix.put(4, new ArrayList<String>());
		formationChoix.get(4).add("4"); 
		formationChoix.get(4).add("Php frameworks"); 	
		formationChoix.get(4).add("15");		
		formationChoix.get(4).add("Symphony");
		formationChoix.get(4).add("2500");

		formationChoix.put(5, new ArrayList<String>());
		formationChoix.get(5).add("5"); 
		formationChoix.get(5).add("C#"); 	
		formationChoix.get(5).add("20");		
		formationChoix.get(5).add("DontNet Core");
		formationChoix.get(5).add("5000");
	}

}
