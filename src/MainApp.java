
import java.util.ArrayList;	
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MainApp {

	//  hashmap des formations
	protected static Map<Integer,ArrayList<String>> formationList = new HashMap<>();
	//  hashmap du panier
	protected static Map<Integer,ArrayList<String>> caddyList = new HashMap<>();

	protected static Scanner scan = new Scanner(System.in); 




	public static void main(String[] args) {

		insertFormationList();
		mainFunction();
		scan.close();
	}




	/** M�thode principale qui s'execute dans le main */
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
	/** M�thode qui affiche le menu  */
	public static void showMenu() {
		System.out.println("Menu :\n");
		System.out.println("1.Voulez-vous voir la formations ?");
		System.out.println("2.Ajouter une formation au panier en fonction du cours.");
		System.out.println("3.Afficher le panier.");
		System.out.println("4.Sortir de l'application.\n");
	}

	/** M�thode qui recherche une formation dans la liste contenant un mot cl� saisi au clavier */
	public static void findFormation() {

		displayFormations();
		System.out.println("Entr�e le nom du cours ex : Java");
		String keyword = scan.next();

		String java="Java";
		String javaAvance="Java-avanc�";

		/*
		 * formationList.forEach((key,value)->{ if(value.get(0).contains(keyword)) {
		 * addFormationCaddy(value); } });
		 */
		// comparaison de chaine  java/java avance

		if (keyword==java) { formationList.forEach((key,value)->{
			if(value.get(0).contains(java)) { 
				addFormationCaddy(value); 
				} 
			});

		} else if(keyword==javaAvance) { 
			formationList.forEach((key,value)->{
			if(value.get(0).contains(javaAvance)) {
				
				addFormationCaddy(value); } }); }
		else
			{ formationList.forEach((key,value)->{ 
				if(value.get(0).contains(keyword)) {
				addFormationCaddy(value); } 
				}); 
			}

	}

	/** M�thode qui ajoute une formation au panier
	 * @param value  */
	public static void addFormationCaddy(ArrayList<String> value) {

		int key=1;
		// le caddy est-il vide ? si oui cl� initialis� � 1 
		if (caddyList.isEmpty()) {
			caddyList.put(key, value);

			// sinon on r�cup�re la taille du hashmap + incr�mentation
		}else {
			key=caddyList.size()+1;
			caddyList.put(key, value);
			System.out.println("size :"+caddyList.size());
		}

	}

	/** M�thode qui affiche le panier  */
	public static void displayCaddy() {

		System.out.println("Caddy.\n");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("| %-5s| %-15s | %-5s | %-40s |%-5s |%n", "No", "COURS", "NB/JOURS", "DESCRIPTION"," PRIX");
		System.out.println("|------|-----------------|----------|------------------------------------------|------|");


		caddyList.forEach((key,value)->{	
			for (int i = 1; i < caddyList.size()+1; i++) {
				System.out.printf("| %-5s| %-15s | %-8s | %-40s | %-3s |%n",key, caddyList.get(i).get(0), 
						caddyList.get(i).get(1),
						caddyList.get(i).get(2),caddyList.get(i).get(3));

			}			
		});

		System.out.println("---------------------------------------------------------------------------------------");

	}

	/** M�thode qui affiche la liste de toutes les formations  */
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

	/** M�thode qui initialise la liste des formations */
	private static void insertFormationList() {
		formationList.put(1, new ArrayList<String>());
		formationList.get(1).add("Java"); 	
		formationList.get(1).add("20");		
		formationList.get(1).add("Jaca SE 8 : Syntaxe et POO");
		formationList.get(1).add("3000");

		formationList.put(2, new ArrayList<String>());
		formationList.get(2).add("Java-avanc�"); 	
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

}
