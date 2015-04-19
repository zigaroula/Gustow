package infsi351.gustow.data;

import android.content.Context;

// it's much easier to declare the "global" classes here rather than pass them through the new intents

public class Globals {
	public static GestionnairePlat plats;
	public static Cart cart;
	
	public static void init(Context appContext) {
		plats=new GestionnairePlat(appContext);
		cart=new Cart();
		
		//tests
		plats.getPlatsFromServer();
		plats.getFormulesFromServer();
		plats.loadFromFiles();
		//plats.testBourrin();
		
		//cart.testBourrin();
	}
}
