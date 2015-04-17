package infsi351.gustow.data;

// it's much easier to declare the "global" classes here rather than pass them through the new intents

public class Globals {
	public static GestionnairePlat plats;
	public static Cart cart;
	
	public static void init() {
		plats=new GestionnairePlat();
		cart=new Cart();
		
		//tests
		plats.testBourrin();
		//cart.testBourrin();
	}
}
