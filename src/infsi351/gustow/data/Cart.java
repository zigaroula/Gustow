package infsi351.gustow.data;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Formule> formules; //formules avec un plat dans chaque catégorie
	private ArrayList<Integer> plats; //id de plats individuels
	
	public Cart() {
		formules=new ArrayList<Formule>();
		plats=new ArrayList<Integer>();
	}
	
	public void add(int idPlat) {
		plats.add((Integer) idPlat);
	}
	
	public void add(Formule f) {
		formules.add(f);
	}
	
	public void rm(int idPlat) {
		plats.remove((Integer) idPlat);
	}
	
	public void rm(Formule f) {
		plats.remove(f);
	}
	
	public void testBourrin() {
		Formule f=new Formule();
		f.setId(1);
		f.setEntree(0);
		f.setPlat(1);
		f.setDessert(2);
		add(f);
		
		add(1);
		add(2);
	}

	public ArrayList<Formule> getFormules() {
		return formules;
	}

	public ArrayList<Integer> getPlats() {
		return plats;
	}
	
	
	
}
