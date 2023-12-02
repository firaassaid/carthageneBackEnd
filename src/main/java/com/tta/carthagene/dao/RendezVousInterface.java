package com.tta.carthagene.dao;

import java.util.List;

import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.response.BasicResponse;

public interface RendezVousInterface {


	List<RendezVous> findAll();
	List<Medecins> findByIDRdv(int idRdv);
	List<RendezVous> getNewRdv(String status);
	BasicResponse save(RendezVous rendezVous);
	BasicResponse update(RendezVous rendezVous);
	BasicResponse updateStatus(String idRdv , String status , String description);
	BasicResponse delete(String id);
	 BasicResponse saveRating(RendezVous rendezVous);
	 List<RendezVous> getRendezVousByIdPatient(String idPatient);
	// BasicResponse updateStatus(String idRdv, String status);
	//BasicResponse saveRating(String id);
//	BasicResponse rating(String id);
}
