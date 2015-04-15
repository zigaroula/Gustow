package infsi351.gustow.data;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Formule> menus; //formules avec un plat dans chaque catégorie
	private ArrayList<Integer> plats; //id de plats individuels
	
	public Cart() {
		menus=new ArrayList<Formule>();
		plats=new ArrayList<Integer>();
	}
	
	public void add(int idPlat) {
		plats.add((Integer) idPlat);
	}
	
	public void add(Formule f) {
		menus.add(f);
	}
	
	public void rm(int idPlat) {
		plats.remove((Integer) idPlat);
	}
	
	public void rm(Formule f) {
		plats.remove(f);
	}
	
	public void testBourrin() {
		Formule f=new Formule();
		f.setName("trop cool");
		f.addEntree(0);
		f.addPlat(1);
		f.addDessert(2);
		add(f);
		
		add(1);
		add(2);
	}

	public ArrayList<Formule> getMenus() {
		return menus;
	}

	public ArrayList<Integer> getPlats() {
		return plats;
	}
	
	
	
}
