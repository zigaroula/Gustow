package infsi351.gustow.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;

import android.content.Context;
import android.util.Log;

public class GestionnairePlat {
	private Hashtable<Integer,Plat> map;
	private Context context;


	public GestionnairePlat(Context context){
		map=new Hashtable<Integer,Plat>();
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
		Plat p1=new Plat(1,"Opera",TypePlat.Dessert, 200, "", "a");
		Plat p2=new Plat(2,"Couscous",TypePlat.PlatPrincipal, 20, "", "aaaaaa");
		Plat p3=new Plat(3,"Terrine de poisson",TypePlat.Entree, 230, "", "cccccccc");

		this.put(p1);
		this.put(p2);
		this.put(p3);
	}

	public Plat get(int i) {
		return map.get( i);
	}

	public void put(Plat p) {
		map.put(p.getId(), p);

	}
}
