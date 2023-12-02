package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.Services;

public class ServicesRowMapper implements RowMapper<Services> {

    @Override
    public Services mapRow(ResultSet rs, int arg1) throws SQLException {

        Services service = new Services();
        service.setIdService(rs.getString("id_service"));
        service.setDescription(rs.getString("description"));
        service.setDescriptionArab(rs.getString("descriptionArab")); // Map to descriptionAarb field
        service.setDescriptionEng(rs.getString("descriptionEng"));   // Map to descriptionEng field
        service.setCategorie(rs.getInt("categorie"));
        service.setCreationDate(rs.getDate("creation_date"));
        return service;
    }

}

