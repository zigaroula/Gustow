package infsi351.gustow.data;

import java.util.ArrayList;
import java.util.List;

public class Formule {
	private List<Integer> entrees=new ArrayList<Integer>();
	private List<Integer> plats=new ArrayList<Integer>();
	private List<Integer> desserts=new ArrayList<Integer>();

	public Formule() {

	}
	
	public Formule(List<Integer> entrees, List<Integer> plats,
			List<Integer> desserts) {
		super();
		this.entrees = entrees;
		this.plats = plats;
		this.desserts = desserts;
	}
	
	public void testBourrin() {
		entrees.add(0);
		entrees.add(1);
		plats.add(1);
		desserts.add(2);
	}

	public List<Integer> getEntrees() {
		return entrees;
	}

	public List<Integer> getPlats() {
		return plats;
	}

	public List<Integer> getDesserts() {
		return desserts;
	}
	
	public void addEntree(int i) {
		entrees.add(i);
	}
	public void addPlat(int i) {
		plats.add(i);
	}
	public void addDessert(int i) {
		desserts.add(i);
	}
	
	public void rmEntree(int i) {
		entrees.remove((Integer) i);
	}
	public void rmPlat(int i) {
		plats.remove((Integer) i);
	}
	public void rmDessert(int i) {
		desserts.remove((Integer) i);
	}

}
