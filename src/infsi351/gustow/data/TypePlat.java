package infsi351.gustow.data;

public enum TypePlat {
	Entree ("Entrée"),
	PlatPrincipal ("Plat principal"),
	Dessert ( "Dessert"),
	Aperitif ("Apéritif");

	private String name = "";

	//Constructeur
	TypePlat(String name){
		this.name = name;
	}

	public String toString(){
		return name;
	}
}
