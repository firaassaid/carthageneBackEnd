package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.Had;
import com.tta.carthagene.entities.Had;


public class HadRowMapper implements RowMapper<Had> {

	@Override
	public Had mapRow(ResultSet rs, int arg1) throws SQLException {

		
		

		Had had = new Had();
		had.setIdHad(rs.getString("had_id"));
		had.setBesoin(rs.getString("besoin"));
		had.setAddress(rs.getString("address"));
		had.setNumeroTel(rs.getString("numero_tel"));
		had.setStatus(rs.getString("status"));
		had.setIdPatient(rs.getString("id_patient"));
		had.setType(rs.getString("type"));
		
		had.setCreationDate(rs.getDate("creation_date"));
		had.setDateRdvHad(rs.getDate("date_rdv_had"));
		had.setRating(rs.getDouble("rating"));
		had.setCode(rs.getString("code"));
		had.setMateriel(rs.getString("materiel"));
		had.setImage(rs.getString("image"));
		
		return had;
	}

}
