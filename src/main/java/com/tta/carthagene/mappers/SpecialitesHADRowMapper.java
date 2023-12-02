package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.Specialites;
import com.tta.carthagene.entities.SpecialitesHAD;


public class SpecialitesHADRowMapper implements RowMapper<SpecialitesHAD> {

	@Override
	public SpecialitesHAD mapRow(ResultSet rs, int arg1) throws SQLException {

		
		

		SpecialitesHAD specialiteHAD = new SpecialitesHAD();
		specialiteHAD.setIdSpecialiteHAD(rs.getInt("id_specialiteHAD"));
		specialiteHAD.setDescription(rs.getString("description"));
		return specialiteHAD;
	}

}
