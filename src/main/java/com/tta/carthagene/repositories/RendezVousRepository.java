package com.tta.carthagene.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tta.carthagene.dao.RendezVousInterface;
import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.mappers.RendezVousRowMapper;
import com.tta.carthagene.response.BasicResponse;

@Repository
public class RendezVousRepository implements RendezVousInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RendezVous> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * FROM rendez_vous order by  creation_date desc;", new RendezVousRowMapper());
	}

	@Override
	public List<Medecins> findByIDRdv(int idRdv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResponse save(RendezVous rendezVous) {
	    try {
	        System.out.println("rendezVous " + rendezVous.toString());
	        rendezVous.setCreationDate(new Date());
	        rendezVous.setIdRdv(UUID.randomUUID().toString().replace("-", ""));
	        rendezVous.setStatus("Encours");
	        // Set the rating value
	       // rendezVous.setRating(0.0); // Assuming the default rating is 0.0

	        jdbcTemplate.update(
	            "INSERT INTO rendez_vous(\r\n"
	                + "id_patient, id_rdv, nom, prenom, date_naissance, email, numero_tel, \r\n"
	                + "specioalite_id, medecin_id, date_rdv, creation_date, status, service_id, rating)\r\n"
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, \r\n" + "?, ?, ?, ?, ?, ?, ?);\r\n" + " ",
	            rendezVous.getIdPatient(), rendezVous.getIdRdv(), rendezVous.getNom(), rendezVous.getPrenom(),
	            rendezVous.getDateNaissance(), rendezVous.getEmail(), rendezVous.getNumeroTel(),
	            rendezVous.getSpecialiteId(), rendezVous.getIdMedecin(), rendezVous.getDateRdv(),
	            rendezVous.getCreationDate(), rendezVous.getStatus(), rendezVous.getServiceId(),
	            rendezVous.getRating());

	        return new BasicResponse("La Création effectuée avec succes", HttpStatus.OK);
	    } catch (org.springframework.dao.DuplicateKeyException ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);
	    }
	}

	@Override
	public List<RendezVous> getRendezVousByIdPatient(String idPatient) {
	    String query = "SELECT * FROM rendez_vous WHERE id_patient = ?";
	    return jdbcTemplate.query(query, new Object[]{idPatient}, new RendezVousRowMapper());
	}

	public BasicResponse saveRating(RendezVous rendezVous) {
	    try {
	        System.out.println("rendezVous " + rendezVous.toString());

	        // Check if the idRdv is provided
	        if (rendezVous.getIdRdv() == null || rendezVous.getIdRdv().isEmpty()) {
	            return new BasicResponse("idRdv is required for updating the record", HttpStatus.BAD_REQUEST);
	        }

	        // Set the rating value
	        Double rating = rendezVous.getRating();
	        if (rating == null) {
	            rating = 0.0; // Assuming the default rating is 0.0
	        }

	        // Update the record with the rating value
	        jdbcTemplate.update(
	            "UPDATE rendez_vous SET rating = ? WHERE id_rdv = ?",
	            rating,
	            rendezVous.getIdRdv()
	        );

	        return new BasicResponse("Rating updated successfully", HttpStatus.OK);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Error updating rating", HttpStatus.BAD_REQUEST);
	    }
	}

	@Override
	public BasicResponse update(RendezVous rendezVous) {
	    try {
	        System.out.println("rendezVous " + rendezVous.toString());
	        rendezVous.setCreationDate(new Date());
	        rendezVous.setStatus("Encours");

	        jdbcTemplate.update(
	            "UPDATE rendez_vous SET\r\n"
	                + "nom = ?, prenom = ?, date_naissance = ?, email = ?, numero_tel = ?, \r\n"
	                + "specioalite_id = ?, medecin_id = ?, date_rdv = ?, creation_date = ?, status = ?, service_id = ?, rating = ?\r\n"
	                + "WHERE id_rdv = ?;\r\n",
	            rendezVous.getNom(), rendezVous.getPrenom(), rendezVous.getDateNaissance(),
	            rendezVous.getEmail(), rendezVous.getNumeroTel(), rendezVous.getSpecialiteId(),
	            rendezVous.getIdMedecin(), rendezVous.getDateRdv(), rendezVous.getCreationDate(),
	            rendezVous.getStatus(), rendezVous.getServiceId(), rendezVous.getRating(),
	            rendezVous.getIdRdv());

	        return new BasicResponse("La mise à jour effectuée avec succès", HttpStatus.OK);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Erreur lors de la mise à jour", HttpStatus.BAD_REQUEST);
	    }
	}


	@Override
	public BasicResponse delete(String id) {
		try {
			List<RendezVous> toDelete = getById(id);
			if (!toDelete.isEmpty()) {

				System.out.println("rendezVous " + id);
				//String id = rendezVous.getIdRdv();
				String sql = "DELETE FROM rendez_vous where id_rdv=? ";
				Object[] args = new Object[] { id };
				jdbcTemplate.update(sql, args);
				System.out.println("Deleted Record with ID = " + id);

			}
			return new BasicResponse("Rendez Vous supprimé : ", HttpStatus.OK);

		} catch (org.springframework.dao.DuplicateKeyException ex) {

			ex.printStackTrace();
			return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}

	private List<RendezVous> getById(String id) {
		return jdbcTemplate.query("select * from rendez_vous  where id_rdv=? ", new Object[] { id },
				new RendezVousRowMapper());
	}

	@Override
	public BasicResponse updateStatus(String idRdv , String status, String description ) {
		
		try {
			List<RendezVous> toUpdate = getById(idRdv);
			if (!toUpdate.isEmpty()) {
				jdbcTemplate.update("UPDATE public.rendez_vous\r\n"
						+ "	SET  status=?\r\n"
						+ "	WHERE id_rdv=?;", 
						new Object[] { status , idRdv });
				
				Notifications notification = new Notifications();
				notification.setCreationDate(new Date());
				notification.setIdNotif(UUID.randomUUID().toString().replace("-", ""));
				notification.setDescription(description);
				notification.setType("RDV");
				jdbcTemplate.update("INSERT INTO public.notifications(\r\n"
						+ "	id_notif, id_rdv, description, id_patient, creation_date, type)\r\n"
						+ "	VALUES (?, ?, ?, ?, ?, ?);",notification.getIdNotif(), idRdv, description, toUpdate.get(0).getIdPatient(),
						notification.getCreationDate(), notification.getType());
				
				return new BasicResponse("Succès", HttpStatus.OK);
			} else {
				return new BasicResponse("Erreur", HttpStatus.OK);
			}

		} catch (org.springframework.dao.DuplicateKeyException ex) {

			ex.printStackTrace();
			return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}

	@Override
	public List<RendezVous> getNewRdv(String status) {
		return jdbcTemplate.query("SELECT *\r\n"
				+ "	FROM public.rendez_vous\r\n"
				+ "	WHERE status=?;", new Object[] { status },
				new RendezVousRowMapper());
	}
	

}
