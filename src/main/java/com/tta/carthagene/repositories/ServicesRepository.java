package com.tta.carthagene.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tta.carthagene.dao.RendezVousInterface;
import com.tta.carthagene.dao.ServicesInterface;
import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.entities.Services;
import com.tta.carthagene.mappers.MedecinsRowMapper;
import com.tta.carthagene.mappers.RendezVousRowMapper;
import com.tta.carthagene.mappers.ServicesRowMapper;
import com.tta.carthagene.response.BasicResponse;

@Repository
public class ServicesRepository implements ServicesInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Services> findAll() {
		return jdbcTemplate.query("select * FROM services order by categorie ASC;", new ServicesRowMapper());
	}

	@Override
	public BasicResponse save(Services services) {

		try {
if (services.getCategorie()==1||services.getCategorie()==2 ||services.getCategorie()==3 ) {
	System.out.println("services " + services.toString());
	services.setCreationDate(new Date());
	services.setIdService(UUID.randomUUID().toString().replace("-", ""));
	jdbcTemplate.update(
			"INSERT INTO services(\r\n"
			+ "            id_service, description, categorie, creation_date)\r\n"
			+ "    VALUES (?, ?, ?, ?);\r\n", services.getIdService(),services.getDescription(),
			services.getCategorie(),services.getCreationDate())
			;

	return new BasicResponse("La Création effectuée avec succes", HttpStatus.OK);
}else {

	return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);
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
	public BasicResponse update(Services services) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResponse delete(Services services) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Services> findbyCategorie(int categorie) {
		return jdbcTemplate.query("select * from  services where categorie=? order by description ASC", new Object[] { categorie },
				new ServicesRowMapper());
	}

	@Override
	public List<Services> findByid(String id) {
		return jdbcTemplate.query("SELECT * FROM public.services where id_service=?;", new Object[] { id },
				new ServicesRowMapper());
	}


	

}
