package com.tta.carthagene.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tta.carthagene.dao.NotificationsInterface;
import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.response.BasicResponse;
@CrossOrigin("*")
@RestController
@RequestMapping("/notifications")
public class NotificationsController {
	@Autowired
	NotificationsInterface notificationsInterface;

	@GetMapping
	public List<Notifications> findAll() {

		return notificationsInterface.findAll();
	}
	
	@GetMapping("/type/{type}")
	public List<Notifications> getbyType(@PathVariable("type") String type) {

		return notificationsInterface.getByType(type);
	}
	
	@GetMapping("/byRdv/{idRdv}")
	public List<Notifications> getByidRdv(@PathVariable("idRdv") String idRdv) {

		return notificationsInterface.getByRendezVous(idRdv);
	}


	@PostMapping("/save")
	public BasicResponse save(@RequestBody Notifications notifications) {
		return notificationsInterface.save(notifications);
	}

	@PutMapping("/update")
	public BasicResponse update(@RequestBody Notifications notifications) {
		return notificationsInterface.update(notifications);
	}

	@GetMapping("/byPatient/{idPatient}")
	public List<Notifications> getByPatientId(@PathVariable("idPatient") String idPatient) {
	    return notificationsInterface.getByPatientId(idPatient);
	}


}
