package infsi351.gustow.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import android.content.Context;
import android.util.Log;

public class GestionnairePlat {
	private Hashtable<Integer,Plat> map;
	private ArrayList<Formule> formules;
	private Context context;

	public GestionnairePlat() {
		map=new Hashtable<Integer,Plat>();
		formules=new ArrayList<Formule>();
		//getPlatsFromServer(); TODO : à appeler directement dans le constructeur ?
	}
	
	public GestionnairePlat(Context context){
		map=new Hashtable<Integer,Plat>();
		formules=new ArrayList<Formule>();
		this.context = context;
	}

	public void loadFromFiles(){
		File[] fichiersGestionnaire = context.getDir("plats", Context.MODE_PRIVATE).listFiles();
		int i;
		for( i =0; i < fichiersGestionnaire.length ; i++){
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichiersGestionnaire[i]));
				Plat plat = (Plat) ois.readObject();
				put(plat);
				ois.close();
			}
			catch(Exception ex)
			{
				Log.v("Probleme lecture des plats ",ex.getMessage());
				ex.printStackTrace();
			}

		}
		
		fichiersGestionnaire = context.getDir("formules", Context.MODE_PRIVATE).listFiles();

		for( i =0; i < fichiersGestionnaire.length ; i++){
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichiersGestionnaire[i]));
				Formule formule = (Formule) ois.readObject();
				formules.add(formule);
				ois.close();
			}
			catch(Exception ex)
			{
				Log.v("Probleme lecture des formules ",ex.getMessage());
				ex.printStackTrace();
			}

		}
	}

	public void testBourrin() {
		Plat p1=new Plat(0,"Opera",TypePlat.Dessert, 200, "", "a");
		Plat p2=new Plat(1,"Couscous",TypePlat.PlatPrincipal, 20, "", "aaaaaa");
		Plat p3=new Plat(2,"Terrine de poisson",TypePlat.Entree, 230, "", "cccccccc");

		this.put(p1);
		this.put(p2);
		this.put(p3);

		Formule f1=new Formule();
		f1.setName("super formule");
		f1.testBourrin();
		Formule f2=new Formule();
		f2.setName("formule pas bien");
		f2.testBourrin2();
		
		formules.add(f1);
		formules.add(f2);
	}
	
	/*
	 * \brief classe qui simule une requête HTTP qui récupère les plats offerts par le restaurateur (en XML par exemple)
	 */
	public void getPlatsFromServer(){
		ArrayList<Plat> plats = new ArrayList<Plat>();
		
		//SIMULATION DE LA REQUETE POUR LES PLATS
		/*Plat p1=new Plat(0,"Opera",TypePlat.Dessert, 200, "", "L'opéra est un entremets au chocolat et au café. C'est un met de luxe.");
		Plat p2=new Plat(1,"Couscous",TypePlat.PlatPrincipal, 20, "", "Le couscous est un plat berbère à base de semoule de blé dur.");
		Plat p3=new Plat(2,"Terrine de poisson",TypePlat.Entree, 230, "", "Une délicieuse terrine de saumon, servi avec salade");
		Plat p4=new Plat(3,"Bleu Burger",TypePlat.PlatPrincipal, 3, "", "Un burger avec du bleu à la place du cheddar");
		*/
		
		// ENTREES
		Plat e1=new Plat(0,"Noix de Saint-Jacques au caramel d'orange",TypePlat.Entree, 15, "e1", "Saint-Jacques et orange, voilà un mariage parfaitement réussi. Ce plat délicieux est accompagné de feuilles de mâches ou de cresson à la saveur un peu poivrée.");
		Plat e2=new Plat(1,"Soupe de potiron, crème glacée au foie gras",TypePlat.Entree, 10, "e2", "Potage raffiné et original : ce velouté de potiron est accompagné de quenelles de crème glacée au foie gras.");
		Plat e3=new Plat(2,"Langoustines rôties sabayon de citron vert",TypePlat.Entree, 18, "e3", "De délicieuses langoustines rôties au beurre en cocotte, puis accompagnées d'un sabayon, parfumé au jus de citron vert.");
		Plat e4=new Plat(3,"Foie gras poêlé au jus de mangue et jeunes carottes",TypePlat.Entree, 15, "e4", "La réduction de jus de mangue acidulé relève de manière surprenante la douceur des jeunes carottes légèrement caramélisées.");
		Plat e5=new Plat(4,"Entrée fraîcheur au tartare",TypePlat.Entree, 8, "e5", "La simplicité est au cœur de cette recette fraîcheur, pour des bouchées apéritives toute en légèreté.");
		Plat e6=new Plat(5,"Terrine de pâté de campagne",TypePlat.Entree, 8, "e6", "Recette traditionnelle à savourer sans modération !");

		plats.add(e1);
		plats.add(e2);
		plats.add(e3);
		plats.add(e4);
		plats.add(e5);
		plats.add(e6);
		
		// PLATS : salades
		Plat s1=new Plat(6,"Salade périgourdine",TypePlat.PlatPrincipal, 8, "s1", "Good choice !");
		Plat s2=new Plat(7,"Salade de pastèque et melon au basilic et éclats de chèvre frais",TypePlat.PlatPrincipal, 10, "s2", "Pastèque et melon, fraîcheur d'été au basilic et chèvre frais.");
		Plat s3=new Plat(8,"Salade froide de penne aux tomates cerise et courgettes",TypePlat.PlatPrincipal, 10, "s3", "Une salade de pâtes froide d’inspiration méditerranéenne qui va nous régaler pendant tout l’été.");
		Plat s4=new Plat(9,"Salade césar",TypePlat.PlatPrincipal, 8, "s4", "Faites un petit voyage outre-Atlantique en prenant cette salade qui est prisée par nos amis Américains !");
		Plat s5=new Plat(20,"Salade nicoise",TypePlat.PlatPrincipal, 8, "s5", "Cette spécialité nicoise est faite à base de crudités : tomates, poivrons, oignons rouges, cébettes, févettes, céleri, petits artichauts violets, concombres, œufs durs, anchois, thon, huile d'olive et olives de Nice.");
		
		plats.add(s1);
		plats.add(s2);
		plats.add(s3);
		plats.add(s4);
		plats.add(s5);
		
		// PLATS : viandes
		Plat v1=new Plat(10,"Côte de porc ibérique avec sa brochette de cèpes de pays",TypePlat.PlatPrincipal, 22, "v1", "Ce plat raffiné sera accompagné de légumes de notre potager : petits pois, haricots verts, carottes fanes ...");
		Plat v2=new Plat(11,"Volaille croustillante, céleri confit, fruits secs et Comté",TypePlat.PlatPrincipal, 20, "v2", "Laissez-vous emporter par les saveurs délirantes que vous propose ce plat.");
		Plat v3=new Plat(12,"Pièce de boeuf de Charolle avec son concassé de tomate et anchois",TypePlat.PlatPrincipal, 25, "v3", "Un classique à savourer les yeux fermés !");
		Plat v4=new Plat(13,"Canard rôti, vinaigre au cassis et fricassée de champignons",TypePlat.PlatPrincipal, 21, "v4", "Un classique à savourer les yeux fermés !");
		Plat v5=new Plat(14,"Tartare de boeuf charolais, glace à la moutarde et toast au vougeot, radis roses",TypePlat.PlatPrincipal, 20, "v5", "Savourez la fraîcheur d'un bon tartare accompagné de son toast Vougeot.");
		
		plats.add(v1);
		plats.add(v2);
		plats.add(v3);
		plats.add(v4);
		plats.add(v5);
		
		// PLATS : poissons
		Plat p1=new Plat(15,"Pavé de saumon au bouillon de boutons de rose",TypePlat.PlatPrincipal, 20, "p1", "A la dégustation, vous pourrez apprécier le délicat parfum de rose qui sublimera votre saumon !");
		Plat p2=new Plat(16,"Bar de ligne avec son risotto de chorizo ibérique",TypePlat.PlatPrincipal, 20, "p2", "L'onctuosité du risotto accompagnera la délicatesse du bar pour vous faire vivre une expérience que vous n'oublierez pas de si tôt !");
		Plat p3=new Plat(17,"Ecrevisses et anguille fumée en mille-feuilles de pain d'épices",TypePlat.PlatPrincipal, 20, "p3", "N'hésitez pas à prendre ce mets surprenant qui sera accompagné de crème d'anchois et d'oeuf de caille confit.");
		Plat p4=new Plat(18,"Langoustine rafraîchie aux herbes et fleurs, royale de champignons",TypePlat.PlatPrincipal, 20, "p4", "Un classique à savourer les yeux fermés !");
		Plat p5=new Plat(19,"Homard rôti, petits légumes nouveaux & velouté de tomates à la Vodka",TypePlat.PlatPrincipal, 20, "p5", "N'ayez pas peur de cette touche de Vodka ! Elle est utilisée de manière à relever les saveurs de ce plat pour vous donner encore plus de plaisir lors de la dégustation !");
		
		plats.add(p1);
		plats.add(p2);
		plats.add(p3);
		plats.add(p4);
		plats.add(p5);
		
		// DESSERTS
		Plat d1=new Plat(20,"Sablé framboise, ganache chocolat, jus de framboise au Mosto Cotto",TypePlat.Dessert, 8, "d1", "Le mosto cotto est un délice de jus de raisin concentré (et donc sucré !).");
		Plat d2=new Plat(21,"Biscuit praliné à la noisette, mousse de chocolat Grand Cru",TypePlat.Dessert, 7, "d2", "Dessert gourmand ! Avis aux amateurs !");
		Plat d3=new Plat(22,"Tarte citron contemporaine",TypePlat.Dessert, 6, "d3", "La classique tarte aux citrons revisitée !");
		Plat d4=new Plat(23,"Panna cotta avec son coulis de framboise",TypePlat.Dessert, 6, "d4", "La classique tarte aux citrons revisitée !");
		Plat d5=new Plat(24,"Café gourmand",TypePlat.Dessert, 7, "d5", "En choisissant ce dessert, vous pourrez goûter à tous nos merveilleux desserts et ce, accompagné d'un délicieux café !");
				
		plats.add(d1);
		plats.add(d2);
		plats.add(d3);
		plats.add(d4);
		plats.add(d5);
		
		
		
		//FIN DE LA REQUETE ET DE SON TRAITEMENT (parsage XML) --> les plats sont stockés dans la liste plats
		
		File fichiersPlat = context.getDir("plats", Context.MODE_PRIVATE);
		
		for( Plat plat : plats){
			try
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichiersPlat.getAbsolutePath() +"/"+ plat.getId()));
				oos.writeObject(plat);
				oos.close();
			}
			catch(Exception ex)
			{
				Log.v("Probleme à l'écriture des plats ",ex.getMessage());
				ex.printStackTrace();
			}

		}
	}

	/*
	 * Meme chose que précédemment. On sépare en deux fonctions car il n'est peut-être pas nécessaire
	 * de recharger les formules aussi souvent que les plats (et réciproquement).
	 */
	public void getFormulesFromServer(){
		ArrayList<Formule> formules = new ArrayList<Formule>();
		
		//SIMULATION DE LA REQUETE POUR LES PLATS
		Formule f1=new Formule();
		f1.setName("Le Classique");
		f1.addEntree(1);
		f1.addEntree(2);
		f1.addEntree(4);
		f1.addPlat(6);
		f1.addPlat(12);
		f1.addPlat(15);
		f1.addDessert(22);
		f1.addDessert(23);
		f1.addDessert(24);
		f1.setId(0);
		
		Formule f2=new Formule();
		f2.setName("Le Gourmand");
		f2.addEntree(3);
		f2.addEntree(4);
		f2.addEntree(5);
		f2.addPlat(10);
		f2.addPlat(11);
		f2.addPlat(12);
		f2.addDessert(20);
		f2.addDessert(21);
		f2.addDessert(24);
		f2.setId(1);
		
		Formule f3=new Formule();
		f3.setName("Le Léger");
		f3.addEntree(0);
		f3.addEntree(2);
		f3.addEntree(4);
		f3.addPlat(7);
		f3.addPlat(8);
		f3.addPlat(20);
		f3.addDessert(22);
		f3.addDessert(23);
		f3.setId(2);
		
		Formule f4=new Formule();
		f4.setName("L'Atlantide");
		f4.addEntree(0);
		f4.addEntree(2);
		f4.addPlat(19);
		f4.addPlat(16);
		f4.addPlat(17);
		f4.addDessert(22);
		f4.addDessert(23);
		f4.addDessert(24);
		f4.setId(3);
		
		formules.add(f1);
		formules.add(f3);
		formules.add(f2);
		formules.add(f4);
		
		//FIN DE LA REQUETE ET DE SON TRAITEMENT (parsage XML) --> les formules sont stockées dans la liste formules
		
		File fichiersFormules = context.getDir("formules", Context.MODE_PRIVATE);
		
		for( Formule formule : formules){
			try
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichiersFormules.getAbsolutePath() +"/"+ formule.getId()));
				oos.writeObject(formule);
				oos.close();
			}
			catch(Exception ex)
			{
				Log.v("Probleme à l'écriture des formules ",ex.getMessage());
				ex.printStackTrace();
			}

		}
	}

	public Plat get(int i) {
		return map.get( i);
	}

	public void put(Plat p) {
		map.put(p.getId(), p);
	}
	
	public ArrayList<Formule> getFormules() {
		return formules;
	}
}
