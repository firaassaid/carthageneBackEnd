package com.tta.carthagene.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tta.carthagene.response.BasicResponse;
import com.tta.carthagene.dao.HadInterface;
import com.tta.carthagene.entities.Had;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.response.BasicResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tta.carthagene.entities.Had;
import com.tta.carthagene.response.BasicResponse;

@RestController
@RequestMapping("/had")
public class HadController {
	@Autowired
	HadInterface hadInterface;

	@GetMapping
	public List<Had> findAll() {

		return hadInterface.findAll();
	}
	
	@GetMapping("/newHad/{status}")
	public List<Had> getNewRdv(@PathVariable("status") String status) {

		return hadInterface.getNewhad(status);
	}

	@PostMapping("/save")
	public ResponseEntity<BasicResponse> save(
	        @RequestParam("data") String jsonData,
	        @RequestParam(value = "image", required = false) MultipartFile imageFile
	) {
	    return hadInterface.save(jsonData, imageFile);
	}

		@DeleteMapping("/delete/{id}")
		public BasicResponse delete(@PathVariable("id") String idHad) {
			return hadInterface.delete(idHad);
		}

	@PutMapping("/update")
	public BasicResponse update(@RequestBody Had had) {
		return hadInterface.update(had);
	}
	
	@PutMapping("/changeStatus/{idHad}/{status}/{description}")
	public BasicResponse changeStatus(@PathVariable("idHad") String idHad, @PathVariable("status") String status ,@PathVariable("description") String description ) {
		return hadInterface.changeStatus(idHad, status, description);
	}

	@PostMapping("/saveRatingHad")
    public BasicResponse saveRatingHAD(@RequestBody Had had) {
        return hadInterface.saveRatingHAD(had);
}
	@GetMapping("/countByBesoin")
	public ResponseEntity<Map<String, Long>> getCountByBesoin() {
	    Map<String, Long> besoinCounts = hadInterface.getCountByBesoin();
	    return ResponseEntity.ok(besoinCounts);
	}

	@GetMapping("/CountByBesoinAndStatus")
	public ResponseEntity<Map<String, Long>> getCountByBesoinAndStatus(){
	    Map<String, Long> besoinCounts = hadInterface.getCountByBesoinAndStatus();
	    return ResponseEntity.ok(besoinCounts);
	}

	@GetMapping("/byPatient/{idPatient}")
	public List<Had> getRendezVousByPatient(@PathVariable("idPatient") String idPatient) {
	    return hadInterface.getHadByIdPatient(idPatient);
	}

	
}