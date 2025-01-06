package fr.eni.projet_enchere.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.projet_enchere.bll.CategorieService;
import fr.eni.projet_enchere.bo.Categorie;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie> {
    
    @Autowired
    private CategorieService categorieService;
    
    @Override
    public Categorie convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        return categorieService.findById(Integer.parseInt(source));
    }
}