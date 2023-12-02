package com.tta.carthagene.dao;

import java.util.List;

import com.tta.carthagene.entities.Partenaires;
import com.tta.carthagene.entities.SpecialitesHAD;
import com.tta.carthagene.response.BasicResponse;

public interface PartenairesInterface {

	List<Partenaires> findAll();

	List<Partenaires> findByIDSpecialiteHAD(int idSpecialiteHAD);

	//List<Partenaires> findByIDService(int categorie, String idService);

	BasicResponse save(Partenaires Partenaires);

	BasicResponse update(Partenaires Partenaires);

	BasicResponse delete(Partenaires Partenaires);
	
	List<Partenaires> getById(String id);

	List<SpecialitesHAD> findAllSpecialiteHAD();
}
