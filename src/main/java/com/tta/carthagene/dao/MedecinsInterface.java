package com.tta.carthagene.dao;

import java.util.List;

import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.Specialites;
import com.tta.carthagene.response.BasicResponse;

public interface MedecinsInterface {

	List<Medecins> findAll();

	List<Medecins> findByIDSpecialite(int idSpecialite);

	List<Medecins> findByIDService(int categorie, String idService);

	BasicResponse save(Medecins Medecins);

	BasicResponse update(Medecins Medecins);

	BasicResponse delete(Medecins Medecins);
	
	List<Medecins> getById(String id);

	List<Specialites> findAllSpecialite();
}
