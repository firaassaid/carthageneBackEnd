package com.tta.carthagene.dao;

import java.util.List;
import java.util.Map;  
import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.entities.Had;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.tta.carthagene.response.BasicResponse;

public interface HadInterface {


	List<Had> findAll();
	List<Had> getbyId(String id);
	List<Had> getNewhad(String status);
    ResponseEntity<BasicResponse> save(String jsonData, MultipartFile imageFile);


	BasicResponse update(Had had);
	BasicResponse changeStatus(String idHad , String status, String description);
	
	List<Had> getHadByIdPatient(String idPatient);
	Map<String, Long> getCountByBesoin();
	Map<String, Long> getCountByBesoinAndStatus();
	BasicResponse delete(String id);
	
	BasicResponse saveRatingHAD(Had had);
}
