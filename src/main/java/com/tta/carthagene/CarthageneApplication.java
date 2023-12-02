package com.tta.carthagene;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import com.tta.carthagene.controllers.EmailSenderService;
import javax.mail.MessagingException;

@SpringBootApplication
public class CarthageneApplication {

	@Autowired
	private EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(CarthageneApplication.class, args);
	}
/*
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("firas.said@esprit.tn",
				"This is you new password 'firas1994'",
				"New Password");

	}
*/
}