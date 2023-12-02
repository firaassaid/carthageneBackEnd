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

import com.tta.carthagene.dao.PartenairesInterface;
import com.tta.carthagene.entities.Partenaires;
import com.tta.carthagene.entities.SpecialitesHAD;
import com.tta.carthagene.response.BasicResponse;
@CrossOrigin("*")
@RestController
@RequestMapping("/partenaires")
public class PartenairesController {
	@Autowired
	PartenairesInterface partenaireInterface;

	@GetMapping
	public List<Partenaires> findAll() {

		return partenaireInterface.findAll();
	}

	@GetMapping("/specialiteHAD/{id}")
	public List<Partenaires> findByIDSpecialiteHAD(@PathVariable("id") int idSpecialiteHAD) {

		return partenaireInterface.findByIDSpecialiteHAD(idSpecialiteHAD);
	}
	
	@GetMapping("/specialiteHAD")
	public List<SpecialitesHAD> findAllSpecialite() {

		return partenaireInterface.findAllSpecialiteHAD();
	}
	@PostMapping("/save")
	public BasicResponse save(@RequestBody Partenaires partenaires) {
		return partenaireInterface.save(partenaires);
	}

	@PutMapping("/update")
	public BasicResponse update(@RequestBody Partenaires partenaires) {
		return partenaireInterface.update(partenaires);
	}
	
	@PutMapping("/supprimer")
	public BasicResponse delete(@RequestBody Partenaires partenaires) {
		return partenaireInterface.delete(partenaires);
	}
	
	@GetMapping("/getbyid/{id}")
	public List<Partenaires> getbyid(@PathVariable("id") String id){
		return partenaireInterface.getById(id);
		
	}
	
	// categorie 1 ou 2 ou 3 idSpecialite exemple "9ef9bb4a9aff49259acf95306f42886b"
	/*
	@GetMapping("/service/{categorie}/{idSpecialite}")
	public List<Partenaires> findByIDService(@PathVariable("categorie") int categorie,@PathVariable("idSpecialite") String idSpecialite) {

		return partenaireInterface.findByIDService(categorie,idSpecialite);
	}

*/
}
