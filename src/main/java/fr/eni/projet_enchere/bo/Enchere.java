package fr.eni.projet_enchere.bo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enchere {
	
	private long idEnchere;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	
	public Enchere(LocalDateTime dateEnchere, int montantEnchere) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	
	public int getMontantEnchere() {
		return montantEnchere;
	}
	
	public void setMontantEnchere(int montant_enchere) {
		this.montantEnchere = montant_enchere;
	}
	
	public long getIdEnchere() {
		return idEnchere;
	}

	public void setIdEnchere(long idEnchere) {
		this.idEnchere = idEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montantEnchere + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateEnchere, montantEnchere);
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
		return dateEnchere == other.dateEnchere && montantEnchere == other.montantEnchere;
	}

}
