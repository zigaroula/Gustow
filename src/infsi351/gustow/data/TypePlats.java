package infsi351.gustow.data;

public enum TypePlats {
	Entree ("Entrée"),
	PlatPrincipal ("Plat principal"),
	Dessert ( "Dessert"),
	Aperitif ("Apéritif");

	private String name = "";

	//Constructeur
	TypePlats(String name){
		this.name = name;
	}

	public String toString(){
		return name;
	}
}
