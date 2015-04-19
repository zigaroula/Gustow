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
		Plat p1=new Plat(0,"Opera",TypePlat.Dessert, 200, "", "L'opéra est un entremets au chocolat et au café. C'est un met de luxe.");
		Plat p2=new Plat(1,"Couscous",TypePlat.PlatPrincipal, 20, "", "Le couscous est un plat berbère à base de semoule de blé dur.");
		Plat p3=new Plat(2,"Terrine de poisson",TypePlat.Entree, 230, "", "Une délicieuse terrine de saumon, servi avec salade");
		Plat p4=new Plat(3,"Bleu Burger",TypePlat.PlatPrincipal, 3, "", "Un burger avec du bleu à la place du cheddar");
		
		plats.add(p1);
		plats.add(p2);
		plats.add(p3);
		plats.add(p4);
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
		f1.setName("Le Grand Chasseur");
		f1.testBourrin();
		f1.setId(0);
		Formule f2=new Formule();
		f2.setName("Le Roi du RAB");
		f2.testBourrin2();
		f2.setId(1);
		Formule f3=new Formule();
		f3.setName("Petite Faim");
		f3.addDessert(0);
		f3.addEntree(2);
		f3.addPlat(3);
		f3.setId(2);
		
		formules.add(f1);
		formules.add(f3);
		formules.add(f2);
		
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
