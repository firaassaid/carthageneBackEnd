package com.tta.carthagene.dao;

import java.util.List;

import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.response.BasicResponse;

public interface NotificationsInterface {


	List<Notifications> findAll();
	BasicResponse save(Notifications notifications);
	BasicResponse update(Notifications notifications);
	BasicResponse delete(Notifications notifications);
	List<Notifications> getByRendezVous(String idRdv);
	List<Notifications> getByType(String type);
	public List<Notifications> getByPatientId(String idPatient);

}
