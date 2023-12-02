package com.tta.carthagene.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tta.carthagene.entities.User;
import com.tta.carthagene.repositories.UserRepository;
import com.tta.carthagene.security.jwt.JwtUtils;
import com.tta.carthagene.security.service.UserDetailsImpl;
import com.tta.cathagene.payload.request.LoginRequest;
import com.tta.cathagene.payload.request.SignupRequest;
import com.tta.cathagene.payload.response.JwtResponse;
import com.tta.cathagene.payload.response.MessageResponse;
//import com.tta.carthagene.repositories.PasswordResetService; // Import PasswordResetService

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

  //  @Autowired
   // private PasswordResetService passwordResetService; // Inject the password reset service

  //  @Autowired
  //  private JavaMailSender javaMailSender; // Inject JavaMailSender

	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		/*List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());*/

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	        return ResponseEntity
	                .badRequest()
	                .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	        return ResponseEntity
	                .badRequest()
	                .body(new MessageResponse("Error: Email is already in use!"));
	    }

	    // Create new user's account and set additional fields
	    User user = new User();
	    user.setUsername(signUpRequest.getUsername());
	    user.setEmail(signUpRequest.getEmail());
	    user.setNom(signUpRequest.getNom());
	    user.setPrenom(signUpRequest.getPrenom());
	    user.setRole(signUpRequest.getRole());
	    user.setPassword(encoder.encode(signUpRequest.getPassword()));
	    user.setAdress(signUpRequest.getAdress());

	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	/*
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPasswordRequest(@RequestBody String userEmail) {
        try {
            passwordResetService.initiatePasswordReset(userEmail);
            return ResponseEntity.ok(new MessageResponse("Password reset initiated. Check your email for instructions."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Error initiating password reset."));
        }
    }
    */
}


