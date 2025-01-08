package fr.eni.projet_enchere.bo;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Retrait {

	@NotBlank(message = "La rue ne doit pas être vide.")
	private String rue;
	@NotBlank(message = "le code Postal ne doit pas être vide.")
	@Pattern(regexp = "^\\d{5}$", message = "Le code postal doit contenir 5 chiffres.")
	private String code_postal;
	@NotBlank(message = "la ville ne doit pas être vide.")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'-]{1,30}$", message = "La ville ne doit contenir que des lettres, des espaces ou des traits d'union (max 30 caractères).")
	private String ville;
	
	public Retrait(String rue, String code_postal, String ville) {
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}
	
	public Retrait( ) {
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return rue +" "+ code_postal+ " " + ville;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code_postal, rue, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Retrait other = (Retrait) obj;
		return code_postal == other.code_postal && Objects.equals(rue, other.rue) && Objects.equals(ville, other.ville);
	}
	
	
}
