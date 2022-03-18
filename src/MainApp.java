
import java.util.ArrayList;	
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * Application permettant de commander des formations.
 * Lister, Ajouter au panier, Modifier panier, Valider la commande.
 * Petit jeu en bonus.
 * 
 * @author Jean-Charles Duffau
 * @version 4.0
 * 
 * 
 * */
public class MainApp {

	//  hashmap liste présentation des formations
	protected static Map<Integer,ArrayList<String>> formationList = new HashMap<>();
	//  hashmap du choix des formations
	protected static Map<Integer,ArrayList<String>> formationChoix = new HashMap<>();

	//  hashmap  formations futures
	protected static Map<Integer,ArrayList<String>> futur= new HashMap<>();

	//  hashmap du panier
	protected static Map<Integer,ArrayList<String>> caddyList = new HashMap<>();

	protected static Scanner scan = new Scanner(System.in); 



/**
 * Main principal
 * 
 * @param args execution des fonctions principales
 */
	public static void main(String[] args) {

		insertFormationList();
		formationChoix();
		insertFormationFutur();
		mainFunction();
		scan.close();
	}




	/** Méthode principale qui s'execute dans le main */
	private static void mainFunction() {

		System.out.println("	Bonjour, bienvenue dans mon application Fulltrainings.");
		System.out.println("Nous allons vous proposer une liste de formations actuellement disponible.");

		int ans=0;

		while(ans != 7) {
			// Affichage du menu
			showMenu();

			while(!scan.hasNextInt()) {
				System.out.println("La valeur rentrée n'était pas du type voulu");
				scan.next();
			}

			ans = scan.nextInt();

			switch(ans) {
			case 1 : // Lecture de la liste des formations
				displayFormations();
				break;

			case 2 : // Ajouter une formation par nom du cours  ex : Java
				findFormation();
				break;

			case 3 : 	// Afficher et modifier le panier
				displayCaddy();
				break;

			case 4 : 	// Valider la commande
				displayCommand();
				break;

			case 5 : 	// Prochainement disponible
				inTheFutur();
				break;

			case 6 : 	//Envie de jouer
				game();
				break;
				
			case 7 : // Exit application
				System.out.println("Good Bye.");
				break;

			default : System.out.println("mauvaise saisie, votre choix : "+ans+" est inexistant dans le menu");
			}	
		}


	}

	/** Méthode qui affiche le menu  */
	public static void showMenu() {
		System.out.println("Menu :\n");
		System.out.println("1.Voulez-vous voir la formations ?");
		System.out.println("2.Ajouter une formation au panier en fonction du cours.");
		System.out.println("3.Afficher/Modifier le panier.");
		System.out.println("4.Valider la commande.");
		System.out.println("5.Prochainement....");
		System.out.println("6.Envie de jouer....");
		System.out.println("7.Sortir de l'application.\n");
	}

	/** Méthode qui recherche une formation dans la liste contenant un mot clé saisi au clavier */
	public static void findFormation() {
		displayFormationsChoix();

		// array tampon 
		ArrayList<ArrayList<String>> tmp = new ArrayList<>();

		System.out.println("Entrée le choix du cours ex : 1");
		String keyword=scan.next();

		formationChoix.forEach((key,value)->{	
			if(value.get(0).contains(keyword)) {
				// ajout la value dans le tampon
				tmp.add(value);
			}					
		});
		// le tampon est vide -> message
		if (tmp.isEmpty()) {
			System.out.println("La formation choisie n'existe pas dans la liste.");

			// le tampon est plein.
		} else {

			// ajout de la quantité dans l'arraylist avant ajout au caddy
			System.out.println("Entrée la quantité ex : 6");
			String qty=scan.next();
			tmp.get(0).add(qty);

			addFormationCaddy(tmp.get(0));
		}

	}

	/** Méthode qui ajoute une formation au panier
	*
	 * @param value , valeur à ajouter au panier
	 */
	public static void addFormationCaddy(ArrayList<String> value) {

		int key=1;
		int size;

		// le caddy est-il vide ?
		if (caddyList.isEmpty()){ 
			caddyList.put(key,value); 
		}
		// sinon on récupère la taille du hashmap + incrémentation 
		else {
			size=caddyList.size(); 
			key=size+1; 
			caddyList.put(key, value); 
		} 
	}

	/** Méthode qui affiche le panier  */
	public static void displayCaddy() {

		caddy();

		int rep=1;

		while(rep != 3) {

			System.out.println("--------------------------------------------------------");			
			System.out.println("1 : Pour modifier une quantité.");
			System.out.println("2 : Supprimer une formation.");
			System.out.println("3 : Quitter le panier.");

			while(!scan.hasNextInt()) {
				System.out.println("La valeur rentrée n'était pas du type voulu");
				scan.next();
			}
			rep = scan.nextInt();

			switch(rep) {
			case 1 : // Modification d'une quantité
				System.out.println("Pour modifier une quantité taper son numéro.");
				String keyword=scan.next();
				caddyList.forEach((key,value)->{	
					if(value.get(0).contains(keyword)) {
						//System.out.println(value);

						// ajout de la quantité dans l'arraylist avant ajout au caddy
						System.out.println("Entrée la nouvelle quantité.");
						String qty=scan.next();
						value.set(5,qty);
						caddy();
					}

				});
				break;

			case 2 : // Suppression d'une formation par son numéro
				System.out.println("Pour supprimer une formation taper son numéro.");
				keyword=scan.next();

				// on supprime la cle si la valeur de la clé contient ( le scan)
				caddyList.entrySet()
				.removeIf(key -> key.getValue().contains(keyword));

				caddy();
				break;

			case  3: // Exit application
				System.out.println("Sortie panier.");
				break;

			default : System.out.println("mauvaise saisie, votre choix : "+rep+" est inexistant dans le menu");
			}	
		}

	}
	/** Méthode qui affiche le  caddy  */
	private static void caddy() {

		if (caddyList.isEmpty()) {
			System.out.println("---------------------");
			System.out.println("Votre panier est vide");
			System.out.println("---------------------");
		} else {
			System.out.println("Caddy\n");
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.printf("| %-5s| %-15s | %-5s | %-40s |%-5s | %-2s |%n", "NO.", "COURS", "NB/JOURS", "DESCRIPTION"," PRIX","QTE");
			System.out.println("|------|-----------------|----------|------------------------------------------|------|-----|");

			for (int i = 1; i < caddyList.size()+1; i++) {
				System.out.printf("| %-5s| %-15s | %-8s | %-40s | %-3s |  %-2s |%n",caddyList.get(i).get(0), 
						caddyList.get(i).get(1),
						caddyList.get(i).get(2),caddyList.get(i).get(3),caddyList.get(i).get(4),caddyList.get(i).get(5));
			}			

			System.out.println("---------------------------------------------------------------------------------------------");
		}

	}

	/** Méthode qui affiche la commande  */
	public static void displayCommand() {

		if (caddyList.isEmpty()) {
			System.out.println("-----------------------------");
			System.out.println("Vous n'avez pas de commande.");
			System.out.println("-----------------------------");
		} else {
			System.out.println("Votre commande\n");
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.printf("| %-5s| %-15s | %-5s | %-40s |%-5s | %-2s |%n", "NO.", "COURS", "NB/JOURS", "DESCRIPTION"," PRIX","QTE");
			System.out.println("|------|-----------------|----------|------------------------------------------|------|-----|");

			for (int i = 1; i < caddyList.size()+1; i++) {
				System.out.printf("| %-5s| %-15s | %-8s | %-40s | %-3s |  %-2s |%n",caddyList.get(i).get(0), 
						caddyList.get(i).get(1),
						caddyList.get(i).get(2),caddyList.get(i).get(3),caddyList.get(i).get(4),caddyList.get(i).get(5));
			}			

			System.out.println("---------------------------------------------------------------------------------------------");
			// calcul du montant de la commande
			int total=0;
			for (int i = 1; i < caddyList.size()+1; i++) {
				int prix =Integer.parseInt(caddyList.get(i).get(4));
				int qty =Integer.parseInt(caddyList.get(i).get(5));

				int subTotal=prix*qty;
				total+=subTotal;
			}
			// affichage du total de la commande
			System.out.printf("| %81s | %-2s |%n"," TOTAL COMMANDE",total);
			System.out.println("---------------------------------------------------------------------------------------------");		

			// si commande on peut valider
			System.out.println("Valider la commande Y/N ?");
			String rep = scan.next().toUpperCase();

			if (rep.equals("Y")) {
				caddyList.clear();
				System.out.println("Commande validée.");
			} else {
				System.out.println("Panier toujours valide");

			}
		}

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

	private static void inTheFutur() {
		System.out.println("Formations futures.\n");
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-5s | %-40s |%n", "COURS", "NB/JOURS", "DESCRIPTION");
		System.out.println("|-----------------|----------|------------------------------------------|");

		for (int i = 1; i < futur.size()+1; i++) {
			System.out.printf("| %-15s | %-8s | %-40s |%n", futur.get(i).get(0), futur.get(i).get(1),
					futur.get(i).get(2));
		}
		System.out.println("-------------------------------------------------------------------------");

	}
	
	/** Méthode qui permet de jouer */
	private static void game() {
		// initializing variables
				boolean saisie;
				int nbr=0;
				int count=0;
				int rep;
				int one=1;
				int random = 0;

				System.out.println("Veux-tu jouer à mon jeux ? Tapes 1 pour jouer");
				saisie = scan.hasNextInt();

				// verif si c'est un chiffre de taper
				if (saisie) {
					rep = scan.nextInt();

					if (rep == one) {

						// random
						random=(int)(Math.random()*100)+1 ;

						// choose number between 1 and 100
						System.out.println("Saisir un nombre entre 1 et 100");
						nbr=scan.nextInt();

						while (nbr != random) {

							count++; 
							if (nbr<random) {
								System.out.println("Saisir une valeur plus grande"); 
								nbr=scan.nextInt();

							} else if(nbr>random) { System.out.println("Saisir une valeur plus petite");
							nbr=scan.nextInt();
							} 
						} 
						if(nbr==random) {
							System.out.println("Vous avez trouvé en "+ count +" coups."); 
						}
					}else {
						System.out.println("Good Bye");
					}

				} else {
					System.out.println("La valeur rentrée n'était pas du type voulu");
				}
	}
	
	/** Méthode qui initialise la liste des formations */
	private static void insertFormationFutur() {
		futur.put(1, new ArrayList<String>());
		futur.get(1).add("Yoga"); 	
		futur.get(1).add("20");		
		futur.get(1).add("Le yoga pour les nuls.");
		

		futur.put(2, new ArrayList<String>());
		futur.get(2).add("Poney"); 	
		futur.get(2).add("45");	
		futur.get(2).add("Le poney pour les nuls");
	

		futur.put(3, new ArrayList<String>());
		futur.get(3).add("Sièste"); 	
		futur.get(3).add("90");		
		futur.get(3).add("La sièste pour les nuls");

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
