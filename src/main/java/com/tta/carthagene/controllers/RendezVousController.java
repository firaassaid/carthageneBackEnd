package com.tta.carthagene.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tta.carthagene.dao.RendezVousInterface;
import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.response.BasicResponse;
@CrossOrigin("*")
@RestController
@RequestMapping("/rendezVous")
public class RendezVousController {
	@Autowired
	RendezVousInterface 	rendezVousInterface;

	@GetMapping
	public List<RendezVous> findAll() {

		return rendezVousInterface.findAll();
	}
	
	@GetMapping("/newRdv/{status}")
	public List<RendezVous> getNewRdv(@PathVariable("status") String status) {

		return rendezVousInterface.getNewRdv(status);
	}
	
	@GetMapping("/rendezvous/{id}")
	public List<Medecins> findByIDRdv(@PathVariable("id") int idRDV) {

		return rendezVousInterface.findByIDRdv(idRDV);
	}
	
	@PostMapping("/save")
	public BasicResponse save(@RequestBody RendezVous rendezVous) {
	    return rendezVousInterface.save(rendezVous);
	}
	@GetMapping("/byPatient/{idPatient}")
	public List<RendezVous> getRendezVousByPatient(@PathVariable("idPatient") String idPatient) {
	    return rendezVousInterface.getRendezVousByIdPatient(idPatient);
	}

	





	@PutMapping("/update")
	public BasicResponse update(@RequestBody RendezVous rendezVous) {
		return rendezVousInterface.update(rendezVous);
	}
	
	@PutMapping("/changeStatus/{idRdv}/{status}/{description}")
	public BasicResponse changeStatus(@PathVariable("idRdv") String idRdv, @PathVariable("status") String status ,@PathVariable("description") String description ) {
		return rendezVousInterface.updateStatus(idRdv, status , description);
	}
	@DeleteMapping("/delete/{id}")
	public BasicResponse delete(@PathVariable("id") String idRDV) {
		return rendezVousInterface.delete(idRDV);
	}
	@PostMapping("/saveRating")
    public BasicResponse saveRating(@RequestBody RendezVous rendezVous) {
        return rendezVousInterface.saveRating(rendezVous);
    }
}
