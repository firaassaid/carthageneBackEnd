package com.tta.carthagene.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tta.carthagene.dao.NotificationsInterface;
import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.mappers.NotificationsRowMapper;
import com.tta.carthagene.response.BasicResponse;

@Repository
public class NotificationsRepository implements NotificationsInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Notifications> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from  notifications;", new NotificationsRowMapper());
	}
	
	 @Override
	    public List<Notifications> getByPatientId(String idPatient) {
	        String sql = "SELECT * FROM public.notifications WHERE id_patient = ?";
	        return jdbcTemplate.query(sql, new Object[] { idPatient }, new NotificationsRowMapper());
	    }
	    

	@Override
	public BasicResponse save(Notifications notifications) {
		try {

			System.out.println("notifications " + notifications.toString());
			notifications.setCreationDate(new Date());
			notifications.setIdNotif(UUID.randomUUID().toString().replace("-", ""));
			jdbcTemplate.update(
					"INSERT INTO public.notifications(\r\n"
					+ "	id_notif, id_rdv, description, id_patient, creation_date, type)\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?);",
					notifications.getIdNotif(),notifications.getIdRdv(),notifications.getDescription(),
					notifications.getIdPatient(),notifications.getCreationDate(),notifications.getType()
					);

			return new BasicResponse("Une notification a été envoyée", HttpStatus.OK);
			
		} catch (org.springframework.dao.DuplicateKeyException ex) {
			ex.printStackTrace();
			return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}

	@Override
	public BasicResponse update(Notifications notifications) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResponse delete(Notifications notifications) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notifications> getByRendezVous(String idRdv) {
		return jdbcTemplate.query("SELECT * FROM public.notifications where id_rdv=?;",new Object[] { idRdv }, new NotificationsRowMapper());
	}

	@Override
	public List<Notifications> getByType(String type) {
		return jdbcTemplate.query("SELECT *\r\n"
				+ "	FROM public.notifications\r\n"
				+ "	WHERE type=?;",new Object[] {type}, new NotificationsRowMapper());
	}

}
