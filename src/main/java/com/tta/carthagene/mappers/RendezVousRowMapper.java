package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.RendezVous;

public class RendezVousRowMapper implements RowMapper<RendezVous> {

	@Override
	public RendezVous mapRow(ResultSet rs, int arg1) throws SQLException {

		RendezVous rendezVous = new RendezVous();
		rendezVous.setIdMedecin(rs.getString("medecin_id"));
		rendezVous.setNom(rs.getString("nom"));
		rendezVous.setPrenom(rs.getString("prenom"));
		rendezVous.setEmail(rs.getString("email"));
		rendezVous.setSpecialiteId(rs.getString("specioalite_id"));
		rendezVous.setNumeroTel(rs.getString("numero_tel"));
		rendezVous.setIdRdv(rs.getString("id_rdv"));
		rendezVous.setIdPatient(rs.getString("id_patient"));
		rendezVous.setDateNaissance(rs.getDate("date_naissance"));
		rendezVous.setDateRdv(rs.getDate("date_rdv"));
		rendezVous.setCreationDate(rs.getDate("creation_date"));
		rendezVous.setStatus(rs.getString("status"));
		rendezVous.setServiceId(rs.getString("service_id"));
		rendezVous.setRating(rs.getDouble("rating"));
		return rendezVous;
	}

}
