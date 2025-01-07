package fr.eni.projet_enchere.bll;

import java.util.List;
import org.springframework.stereotype.Service;
import fr.eni.projet_enchere.bo.Categorie;
import fr.eni.projet_enchere.dal.CategorieDAO;

@Service
public class CategorieServiceImpl implements CategorieService {
	private final CategorieDAO categorieDAO;

	public CategorieServiceImpl(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	@Override
	public List<Categorie> findAll() {
		return categorieDAO.findAll();
	}

	@Override
	public Categorie findById(int id) {
		return categorieDAO.findById(id);
	}

	@Override
	public List<Categorie> consulterCategorie() {
		return categorieDAO.findAll();
	}
}
