package fr.eni.projet_enchere.bll;

import java.util.List;

import fr.eni.projet_enchere.bo.Categorie;

public interface CategorieService {

	List<Categorie> findAll();
	Categorie findById(int id);
	List<Categorie> consulterCategorie();

}
