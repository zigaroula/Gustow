package infsi351.gustow.data;


public class dbtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GestionnairePlat toto=new GestionnairePlat();
		
		toto.testBourrin();
		Plat p=toto.get(2);
		System.out.println(p.getDescription());
	}

}
