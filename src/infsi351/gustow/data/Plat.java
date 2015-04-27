package infsi351.gustow.data;

import java.io.Serializable;

public class Plat implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private double prix;
	private TypePlat type;
	private String pathImage;
	private String description;

	public Plat() {

	}

	public Plat(int id, String nom, TypePlat type, double prix,
			String pathImage, String description) {
		super();
		this.type = type;
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.pathImage = pathImage;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public TypePlat getType() {
		return type;
	}

	public void setType(TypePlat type) {
		this.type = type;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return nom + " à " + prix + " euro ";
	}

}
