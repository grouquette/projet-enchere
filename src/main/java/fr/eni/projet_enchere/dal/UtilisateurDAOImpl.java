package fr.eni.projet_enchere.dal;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet_enchere.bo.Utilisateur;
import fr.eni.projet_enchere.dal.rowmapper.UtilisateurRowMapper;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private static final String INSERT = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :mdp, :credit, :admin)";
	private static final String FIND_ALL = "SELECT id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM Utilisateurs WHERE id = :id";
	private static final String FIND_BY_ID = "SELECT id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM Utilisateurs WHERE id = :id";
	
	private NamedParameterJdbcTemplate jdbcTemplate;

	public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void creer(Utilisateur utilisateur) {
		// Manipulation de la clef primaire auto-générée : IDENTIY
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int numAdmin;
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", utilisateur.getPseudo());
		map.addValue("nom", utilisateur.getNom());
		map.addValue("prenom", utilisateur.getPrenom());
		map.addValue("email", utilisateur.getEmail());
		map.addValue("telephone", utilisateur.getTelephone());
		map.addValue("rue", utilisateur.getRue());
		map.addValue("codePostal", utilisateur.getCodePostal());
		map.addValue("ville", utilisateur.getVille());
		map.addValue("mdp", utilisateur.getMotDePasse());
		map.addValue("credit", utilisateur.getCredit());

		if (utilisateur.isAdministrateur()) {
			numAdmin = 1;
		} else {
			numAdmin = 0;
		}
		map.addValue("admin", numAdmin);

		this.jdbcTemplate.update(INSERT, map, keyHolder);

		// Mise à jour de l'id du film avec celui généré par la BDD
		if (keyHolder != null && keyHolder.getKey() != null) {
			utilisateur.setNoUtilisateur(keyHolder.getKey().longValue());
		}
	}

	@Override
	public Utilisateur read(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return this.jdbcTemplate.queryForObject(FIND_BY_ID, params, new UtilisateurRowMapper());
	}
}
