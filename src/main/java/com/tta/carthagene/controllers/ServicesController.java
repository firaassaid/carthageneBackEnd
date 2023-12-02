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
import com.tta.carthagene.dao.ServicesInterface;
import com.tta.carthagene.entities.Medecins;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.entities.Services;
import com.tta.carthagene.response.BasicResponse;
@CrossOrigin("*")
@RestController
@RequestMapping("/services")
public class ServicesController {
	@Autowired
	ServicesInterface 	serviceInterface;

	@GetMapping
	public List<Services> findAll() {

		return serviceInterface.findAll();
	}
	@GetMapping("/categorie/{id}")
	public List<Services> findByCategorie(@PathVariable("id") int categorie) {

		return serviceInterface.findbyCategorie(categorie);
	}
	
	@GetMapping("/getbyid/{id}")
	public List<Services> findByid(@PathVariable("id") String id) {

		return serviceInterface.findByid(id);
	}
	
	@PostMapping("/save")
	public BasicResponse save(@RequestBody Services services) {
		return serviceInterface.save(services);
	}

	@PutMapping("/update")
	public BasicResponse update(@RequestBody Services services) {
		return serviceInterface.update(services);
	}
	@DeleteMapping("/delete")
	public BasicResponse delete(@RequestBody Services services) {
		return serviceInterface.delete(services);
	}
}
