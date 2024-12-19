package fr.eni.projet_enchere.dal;

import java.util.List;

import fr.eni.projet_enchere.bo.Categorie;

public interface CategorieDAO {

	List<Categorie> findAll();

}
