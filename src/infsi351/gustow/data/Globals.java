package infsi351.gustow.data;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;

// it's much easier to declare the "global" classes here rather than pass them through the new intents

public class Globals {
	public static GestionnairePlat plats;
	public static Cart cart;
	public static ArrayList<Integer> couleurs;
	
	public static void init(Context appContext) {
		plats=new GestionnairePlat(appContext);
		cart=new Cart();
		
		couleurs = new ArrayList<Integer>();
		couleurs.add(Color.argb(255, 227, 238, 206));
		couleurs.add(Color.argb(255, 250, 213, 203));
		couleurs.add(Color.argb(255, 218, 175, 189));
		couleurs.add(Color.argb(255, 189, 161, 181));
		couleurs.add(Color.argb(255, 191, 185, 192));
		
		//tests
		plats.getPlatsFromServer();
		plats.getFormulesFromServer();
		plats.loadFromFiles();
		//plats.testBourrin();
		//cart.testBourrin();
	}
}
