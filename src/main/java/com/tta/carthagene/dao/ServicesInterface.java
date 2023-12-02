package com.tta.carthagene.dao;

import java.util.List;

import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.entities.Services;
import com.tta.carthagene.response.BasicResponse;

public interface ServicesInterface {


	List<Services> findAll();
	List<Services> findbyCategorie(int categorie);
	BasicResponse save(Services services);
	BasicResponse update(Services services);
	BasicResponse delete(Services services);
	List<Services> findByid(String id);
}
