package com.tta.carthagene.controllers;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tta.carthagene.dao.MedecinsInterface;
import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.Specialites;
import com.tta.carthagene.response.BasicResponse;
@CrossOrigin("*")
@RestController
@RequestMapping("/medecins")
public class MedecinsController {
	@Autowired
	MedecinsInterface medecinsInterface;

	@GetMapping
	public List<Medecins> findAll() {

		return medecinsInterface.findAll();
	}

	@GetMapping("/specialite/{id}")
	public List<Medecins> findByIDSpecialite(@PathVariable("id") int idSpecialite) {

		return medecinsInterface.findByIDSpecialite(idSpecialite);
	}
	
	@GetMapping("/specialites")
	public List<Specialites> findAllSpecialite() {

		return medecinsInterface.findAllSpecialite();
	}
	@PostMapping("/save")
	public BasicResponse save(@RequestBody Medecins medecins) {
		return medecinsInterface.save(medecins);
	}

	@PutMapping("/update")
	public BasicResponse update(@RequestBody Medecins medecins) {
		return medecinsInterface.update(medecins);
	}
	
	@PutMapping("/supprimer")
	public BasicResponse delete(@RequestBody Medecins medecins) {
		return medecinsInterface.delete(medecins);
	}
	
	@GetMapping("/getbyid/{id}")
	public List<Medecins> getbyid(@PathVariable("id") String id){
		return medecinsInterface.getById(id);
		
	}
	
	// categorie 1 ou 2 ou 3 idSpecialite exemple "9ef9bb4a9aff49259acf95306f42886b"
	@GetMapping("/service/{categorie}/{idSpecialite}")
	public List<Medecins> findByIDService(@PathVariable("categorie") int categorie,@PathVariable("idSpecialite") String idSpecialite) {

		return medecinsInterface.findByIDService(categorie,idSpecialite);
	}


}
