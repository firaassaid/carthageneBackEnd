package com.tta.carthagene.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.tta.carthagene.dao.MedecinsInterface;
import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.Specialites;
import com.tta.carthagene.mappers.MedecinsRowMapper;
import com.tta.carthagene.mappers.SpecialitesRowMapper;
import com.tta.carthagene.response.BasicResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MedecinsRepository implements MedecinsInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Medecins> findAll() {
		return jdbcTemplate.query("SELECT * FROM medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE medecins.disabled = false;", new MedecinsRowMapper());

	}

	@Override
	public List<Medecins> findByIDSpecialite(int idSpecialite) {
		return jdbcTemplate.query("select * from  medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE medecins.disabled = false and specialite_id=?", new Object[] { idSpecialite },new MedecinsRowMapper());
	}

	@Override
	public BasicResponse save(Medecins medecins) {
	    try {
	        medecins.setIdMedecin(UUID.randomUUID().toString().replace("-", ""));
	        medecins.setCreationDate(new Date());
	        medecins.setDisabled(false);
	        jdbcTemplate.update("INSERT INTO public.medecins(\r\n"
	                + "    id_medecin, nom, prenom, telephone, email, specialite_id, convention_cnam, specialite_medicales, exploration_fonctionelle, specialite_chirurgicales, \"creation_Date\", disabled, image)\r\n"
	                + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
	                + "", medecins.getIdMedecin(), medecins.getNom(), medecins.getPrenom(), medecins.getTelephone(), medecins.getEmail(), medecins.getSpecialiteId(), medecins.isConventionCnam(), medecins.getSpecialiteMedicales(), medecins.getExplorationFonctionelle(), medecins.getSpecialiteChirurgicales(), medecins.getCreationDate(), medecins.isDisabled(), medecins.getImage());

	        return new BasicResponse("Medecin ajouté: " + medecins.toString(), HttpStatus.OK);

	    } catch (org.springframework.dao.DuplicateKeyException ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Élément existant!", HttpStatus.BAD_REQUEST);

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Erreur!", HttpStatus.BAD_REQUEST);

	    }
	}


	@Override
	public BasicResponse update(Medecins medecins) {
	    try {
	        List<Medecins> med = this.getById(medecins.getIdMedecin());
	        if (!med.isEmpty()) {
	            jdbcTemplate.update("UPDATE public.medecins\r\n"
	                    + "    SET nom=?, prenom=?, telephone=?, email=?, specialite_id=?, convention_cnam=?, specialite_medicales=?, exploration_fonctionelle=?, specialite_chirurgicales=?, \"creation_Date\"=?, disabled=?, image=?\r\n"
	                    + "    WHERE id_medecin=?;",
	                    medecins.getNom(), medecins.getPrenom(), medecins.getTelephone(), medecins.getEmail(), medecins.getSpecialiteId(), medecins.isConventionCnam(), medecins.getSpecialiteMedicales(),
	                    medecins.getExplorationFonctionelle(), medecins.getSpecialiteChirurgicales(), medecins.getCreationDate(), medecins.isDisabled(), medecins.getImage(), medecins.getIdMedecin());
	            return new BasicResponse("Médecin mis à jour: " + medecins.toString(), HttpStatus.OK);
	        } else {
	            return new BasicResponse("Élément introuvable!", HttpStatus.BAD_REQUEST);

	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Erreur!", HttpStatus.BAD_REQUEST);

	    }
	}

	@Override
	public BasicResponse delete(Medecins medecins) {
		// 
		try {
			List<Medecins> med = this.getById(medecins.getIdMedecin());
			if(!med.isEmpty()) {
				jdbcTemplate.update("UPDATE public.medecins\r\n"
						+ "		SET disabled=true\r\n"
						+ "				WHERE id_medecin=?;",
						medecins.getIdMedecin());
				return new BasicResponse("medecin updated: " + medecins.toString(), HttpStatus.OK);
			} else {
				return new BasicResponse("Element introuvable!", HttpStatus.BAD_REQUEST);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}

	@Override
	public List<Medecins> findByIDService(int categorie, String idService) {
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

	@Override
	public List<Specialites> findAllSpecialite() {
		return jdbcTemplate.query("select * from  specialite;", new SpecialitesRowMapper());
	}

	@Override
	public List<Medecins> getById(String id) {
		return jdbcTemplate.query("select * from medecins INNER JOIN specialite ON specialite.id_specialite = medecins.specialite_id WHERE id_medecin=?",new Object[]{id},  new MedecinsRowMapper());
	}

}
