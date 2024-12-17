package fr.eni.projet_enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

	@GetMapping("/article/creer")
	public String afficherCreerArticle() {
		return "view-article-creation";
	}

	@PostMapping("/article/creer")
	public String creerArticle() {
		return "view-encheres";
	}
}
