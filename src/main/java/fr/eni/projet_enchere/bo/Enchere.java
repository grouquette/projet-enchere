package fr.eni.projet_enchere.bo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enchere {
	
	private LocalDateTime dateEnchere;
	private int montant_enchere;
	
	public Enchere(LocalDateTime dateEnchere, int montant_enchere) {
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	
	public int getMontant_enchere() {
		return montant_enchere;
	}
	
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateEnchere, montant_enchere);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enchere other = (Enchere) obj;
		return dateEnchere == other.dateEnchere && montant_enchere == other.montant_enchere;
	}
	
	

}
