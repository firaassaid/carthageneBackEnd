package com.tta.carthagene.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.tta.carthagene.dao.PartenairesInterface;
import com.tta.carthagene.entities.Partenaires;
import com.tta.carthagene.entities.Partenaires;
import com.tta.carthagene.entities.SpecialitesHAD;
import com.tta.carthagene.mappers.PartenairesRowMapper;
import com.tta.carthagene.mappers.PartenairesRowMapper;
import com.tta.carthagene.mappers.SpecialitesHADRowMapper;
import com.tta.carthagene.response.BasicResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PartenairesRepository implements PartenairesInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Partenaires> findAll() {
		return jdbcTemplate.query("SELECT * FROM medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE medecins.disabled = false;", new PartenairesRowMapper());

	}

	@Override
	public List<Partenaires> findByIDSpecialiteHAD(int idSpecialiteHAD) {
		return jdbcTemplate.query("select * from  medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE medecins.disabled = false and specialite_id=?", new Object[] { idSpecialiteHAD },new PartenairesRowMapper());
	}

	@Override
	public BasicResponse save(Partenaires partenaires) {
			try {
		
		
				partenaires.setIdPartenaireHAD(UUID.randomUUID().toString().replace("-", ""));
				partenaires.setCreationDate(new Date());
				//partenaires.setDisabled(false);
			jdbcTemplate.update("INSERT INTO public.medecins(\r\n"
					+ "	id_medecin, nom, prenom, telephone, email, specialite_id, convention_cnam, specialite_medicales, exploration_fonctionelle, specialite_chirurgicales, \"creation_Date\", disabled)\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
					+ "",partenaires.getIdPartenaireHAD(),partenaires.getNom(),partenaires.getPrenom(),partenaires.getTelephone(),partenaires.getEmail(),partenaires.getSpecialiteHADId());
			
			return new BasicResponse("partenaire ajouter: " + partenaires.toString(), HttpStatus.OK);
			
		} catch (org.springframework.dao.DuplicateKeyException ex) {
			ex.printStackTrace();
			return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}

	@Override
	public BasicResponse update(Partenaires partenaires) {
		try {
		List<Partenaires> part = this.getById(partenaires.getIdPartenaireHAD());
		if(!part.isEmpty()) {
			jdbcTemplate.update("UPDATE public.medecins\r\n"
					+ "	SET nom=?, prenom=?, telephone=?, email=?, specialite_id=?, convention_cnam=?, specialite_medicales=?, exploration_fonctionelle=?, specialite_chirurgicales=?, \"creation_Date\"=?, disabled=?\r\n"
					+ "	WHERE id_medecin=?;",
					partenaires.getNom(),partenaires.getPrenom(),partenaires.getTelephone(),partenaires.getEmail(),partenaires.getSpecialiteHADId(),
					partenaires.getCreationDate(),partenaires.getIdPartenaireHAD());
			return new BasicResponse("partenaire updated: " + partenaires.toString(), HttpStatus.OK);
		} else {
			return new BasicResponse("Element introuvable!", HttpStatus.BAD_REQUEST);

		}
	} catch (Exception ex) {
		ex.printStackTrace();
		return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

	}
	}

	@Override
	public BasicResponse delete(Partenaires partenaires) {
		// 
		try {
			List<Partenaires> part = this.getById(partenaires.getIdPartenaireHAD());
			if(!part.isEmpty()) {
				jdbcTemplate.update("UPDATE public.medecins\r\n"
						+ "		SET disabled=true\r\n"
						+ "				WHERE id_medecin=?;",
						partenaires.getIdPartenaireHAD());
				return new BasicResponse("partenaire updated: " + partenaires.toString(), HttpStatus.OK);
			} else {
				return new BasicResponse("Element introuvable!", HttpStatus.BAD_REQUEST);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}
/*
	@Override
	public List<Partenaires> findByIDService(int categorie, String idService) {
		System.out.println("categorie "+categorie);
		System.out.println("idService "+idService);
		// TODO Auto-generated method stub
		if (categorie==1) {
			return jdbcTemplate.query("select * from  medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE medecins.disabled = false and specialite_medicales=?", new Object[] { idService },
					new MedecinsRowMapper());
		}else if (categorie==2) {
			return jdbcTemplate.query("select * from  medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE medecins.disabled = false and specialite_chirurgicales=?", new Object[] { idService },
					new MedecinsRowMapper());
		}else {
			return jdbcTemplate.query("select * from  medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE medecins.disabled = false and exploration_fonctionelle=?", new Object[] { idService },
					new MedecinsRowMapper());
		}
	
	}
*/
	@Override
	public List<SpecialitesHAD> findAllSpecialiteHAD() {
		return jdbcTemplate.query("select * from  specialite;", new SpecialitesHADRowMapper());
	}

	@Override
	public List<Partenaires> getById(String id) {
		return jdbcTemplate.query("select * from medecins INNER JOIN specialiteHAD ON specialiteHAD.id_specialite = partenaire.specialite_id WHERE id_partenaire=?",new Object[]{id},  new PartenairesRowMapper());
	}
}
