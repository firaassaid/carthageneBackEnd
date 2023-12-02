package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.Notifications;


public class NotificationsRowMapper implements RowMapper<Notifications> {

	@Override
	public Notifications mapRow(ResultSet rs, int arg1) throws SQLException {

		
		

		Notifications notifications = new Notifications();
		notifications.setIdNotif(rs.getString("id_notif"));;
		notifications.setIdPatient(rs.getString("id_patient"));
		notifications.setIdRdv(rs.getString("id_rdv"));
		notifications.setDescription(rs.getString("description"));
		notifications.setCreationDate(rs.getDate("creation_date"));
		notifications.setType(rs.getString("type"));
		
		
		return notifications;
	}

}
