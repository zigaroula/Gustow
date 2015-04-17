package infsi351.gustow.data;

import java.util.ArrayList;
import java.util.List;

public class Formule {
	private int id;
	private String nom;
	private List<Integer> entrees=new ArrayList<Integer>();
	private List<Integer> plats=new ArrayList<Integer>();
	private List<Integer> desserts=new ArrayList<Integer>();
	
	private int entree;
	private int plat;
	private int dessert;

	public Formule() {

	}
	
	public Formule(String name, List<Integer> entrees, List<Integer> plats,
			List<Integer> desserts) {
		super();
		this.nom=name;
		this.entrees = entrees;
		this.plats = plats;
		this.desserts = desserts;
	}
	
	public void copyFormule(Formule f) {
		this.id=f.id;
		this.nom=f.nom;
		this.entrees=f.entrees;
		this.plats=f.plats;
		this.desserts=f.desserts;
		
		this.entree=f.entree;
		this.plat=f.plat;
		this.dessert=f.dessert;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEntree() {
		return entree;
	}

	public void setEntree(int entree) {
		this.entree = entree;
	}

	public int getPlat() {
		return plat;
	}

	public void setPlat(int plat) {
		this.plat = plat;
	}

	public int getDessert() {
		return dessert;
	}

	public void setDessert(int dessert) {
		this.dessert = dessert;
	}
	
	public void testBourrin() {
		entrees.add(0);
		entrees.add(1);
		plats.add(1);
		desserts.add(2);
	}
	
	public void testBourrin2() {
		entrees.add(2);
		plats.add(1);
		plats.add(0);
		desserts.add(0);
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
	
	public String getNom() {
		return nom;
	}
	
	public void setName(String name) {
		this.nom=name;
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
	
	public List<Integer> getPlatsOfType(TypePlat type) {
		switch(type) {
			case Entree: return getEntrees();
			case PlatPrincipal: return getPlats();
			case Dessert: return getDesserts();
			default: return null;
		}
	}
	
	public void setPlatOfType(TypePlat type, int id) {
		switch(type) {
		case Entree: setEntree(id); break;
		case PlatPrincipal: setPlat(id); break;
		case Dessert: setDessert(id); break;
		default: break;
	}
	}

}
