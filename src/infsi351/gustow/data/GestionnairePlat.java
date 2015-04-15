package infsi351.gustow.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import android.content.Context;
import android.util.Log;

public class GestionnairePlat {
	private Hashtable<Integer,Plat> map;
	private ArrayList<Formule> formules;
	private Context context;


	public GestionnairePlat(Context context){
		map=new Hashtable<Integer,Plat>();
		formules=new ArrayList<Formule>();
		context = this.context;
	}

	public void loadFromFiles(){
		File[] fichiersPlat = context.getDir("plats", Context.MODE_PRIVATE).listFiles();
		int i;
		for( i =0; i < fichiersPlat.length ; i++){
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichiersPlat[i]));
				Plat plat = (Plat) ois.readObject();
				put(plat);
			}
			catch(Exception ex)
			{
				Log.v("Probleme lecture des plats ",ex.getMessage());
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
