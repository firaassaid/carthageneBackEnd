package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.Specialites;

public class SpecialitesRowMapper implements RowMapper<Specialites> {

    @Override
    public Specialites mapRow(ResultSet rs, int arg1) throws SQLException {
        Specialites specialite = new Specialites();
        specialite.setIdSpecialite(rs.getInt("id_specialite"));
        specialite.setDescription(rs.getString("description"));
        
        // Use the correct column name "descriptionArab" to retrieve the value.
        specialite.setDescriptionArab(rs.getString("descriptionArab"));
        
        // Assuming you have a similar field for descriptionEng, you can add it here.
        specialite.setDescriptionEng(rs.getString("descriptionEng"));
        
        return specialite;
    }
}
