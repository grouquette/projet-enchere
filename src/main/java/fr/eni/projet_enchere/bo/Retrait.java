package fr.eni.projet_enchere.bo;

import java.util.Objects;

public class Retrait {

	private String rue;
	private int code_postal;
	private String ville;
	
	public Retrait(String rue, int code_postal, String ville) {
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(int code_postal) {
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
		return "Retrait [rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + "]";
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
