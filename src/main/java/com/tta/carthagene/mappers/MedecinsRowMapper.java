package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.Specialites;

public class MedecinsRowMapper implements RowMapper<Medecins> {

    @Override
    public Medecins mapRow(ResultSet rs, int arg1) throws SQLException {
        Medecins medecins = new Medecins();
        medecins.setIdMedecin(rs.getString("id_medecin"));
        medecins.setNom(rs.getString("nom"));
        medecins.setPrenom(rs.getString("prenom"));
        medecins.setNom_arab(rs.getString("nom_arab")); // Map Arabic name
        medecins.setPrenom_arab(rs.getString("prenom_arab")); // Map Arabic name
        medecins.setEmail(rs.getString("email"));
        medecins.setSpecialiteId(rs.getInt("specialite_id"));
        medecins.setTelephone(rs.getString("telephone"));
        medecins.setConventionCnam(rs.getBoolean("convention_cnam"));
        medecins.setSpecialiteMedicales(rs.getString("specialite_medicales"));
        medecins.setSpecialiteChirurgicales(rs.getString("specialite_chirurgicales"));
        medecins.setExplorationFonctionelle(rs.getString("exploration_fonctionelle"));
        medecins.setCreationDate(rs.getDate("creation_Date"));
        medecins.setDisabled(rs.getBoolean("disabled"));
        medecins.setImage(rs.getString("image"));
        Specialites specialite = new Specialites();
        specialite.setIdSpecialite(rs.getInt("id_specialite"));
        specialite.setDescription(rs.getString("description"));

        medecins.setSpecialites(specialite);

        return medecins;
    }
}
