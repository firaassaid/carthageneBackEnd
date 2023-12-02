package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.Partenaires;
import com.tta.carthagene.entities.SpecialitesHAD;


public class PartenairesRowMapper implements RowMapper<Partenaires> {

	@Override
	public Partenaires mapRow(ResultSet rs, int arg1) throws SQLException {

		
		

		Partenaires partenaires = new Partenaires();
		partenaires.setIdPartenaireHAD(rs.getString("id_partenaireHAD"));
		partenaires.setNom(rs.getString("nom"));
		partenaires.setPrenom(rs.getString("prenom"));
		partenaires.setEmail(rs.getString("email"));
		partenaires.setSpecialiteHADId(rs.getInt("specialiteHAD_id"));
		partenaires.setTelephone(rs.getString("telephone"));
	
		partenaires.setCreationDate(rs.getDate("creation_Date"));
		
		
		SpecialitesHAD specialiteHAD = new SpecialitesHAD();
		specialiteHAD.setIdSpecialiteHAD(rs.getInt("id_specialiteHAD"));
		specialiteHAD.setDescription(rs.getString("description"));
	
		partenaires.setSpecialitesHAD(specialiteHAD);
		
		return partenaires;
	}

}
