package com.tta.carthagene.repositories;

import java.util.Date;
import java.util.Random;
import org.json.JSONObject;
import org.json.JSONObject;
import com.tta.carthagene.mappers.HadRowMapper;
import java.util.List;
import java.util.UUID;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.OutputStream;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.HashMap; // Import the HashMap class
import com.tta.carthagene.dao.HadInterface;
import com.tta.carthagene.entities.Had;
import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.mappers.HadRowMapper;
import com.tta.carthagene.mappers.RendezVousRowMapper;
import com.tta.carthagene.response.BasicResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Date;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Date;
import java.util.UUID;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
@Repository
public class HadRepository implements HadInterface {
	private static final String UPLOAD_FOLDER = "C:\\Users\\Attachments"; // Update with your folder path

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Had> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from  had;", new HadRowMapper());

	}

	
	public List<Had> getHadByIdPatient(String idPatient) {
	    return jdbcTemplate.query("SELECT * FROM had WHERE id_patient = ?", new Object[]{idPatient}, new HadRowMapper());
	}

	public ResponseEntity<BasicResponse> save(@RequestParam("data") String data,
	        @RequestParam(value = "image", required = false) MultipartFile imageFile) {
	    try {
	    	 String staticNumeroTel = "1111111";
	         String uploadsDir = "C:\\Users\\Attachement"; // Change to your desired directory
	         String filePath = null; // Default to null

	         if (imageFile != null) {
	             // Process the image file only if it's not null
	             String originalFilename = imageFile.getOriginalFilename();
	             String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	             String filename = UUID.randomUUID().toString().replace("-", "") + "_" + StringUtils.cleanPath(originalFilename);
	             filePath = Paths.get(uploadsDir, filename).toString();
	             File file = new File(filePath);
	             file.getParentFile().mkdirs();
	             imageFile.transferTo(file);
	         }
	         
	         // Parse 'data' JSON string and process other fields
	         JSONObject jsonData = new JSONObject(data);
	         String besoin = jsonData.getString("besoin");
	         String address = jsonData.getString("address");
	         String telephone = jsonData.getString("numeroTel");
	         String idPatient = jsonData.getString("idPatient");
	         if (idPatient == null || idPatient.trim().isEmpty()) {
	             return new ResponseEntity<>(new BasicResponse("idPatient cannot be empty!", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	         }
	    
	        // Date Daterdv = jsonData.getDate("date_rdv_had");
	       //  String numeroTel = staticNumeroTel;

	        // Extract other fields as needed

	        // Create a new 'Had' object and set its fields
	         Had had = new Had();
	         had.setBesoin(besoin);
	         had.setAddress(address);
	         had.setNumeroTel(telephone);
	         had.setImage(filePath);  // Set the image file path here
	         had.setIdPatient(idPatient);
	         
	         had.setDateRdvHad(null);
	         // Set creation date, generate code, and other fields
	         had.setCreationDate(new Date());
	         String randomCode = generateRandomCode(5);
	         had.setCode(randomCode);
	         had.setIdHad(UUID.randomUUID().toString().replace("-", ""));
	         had.setStatus("Encours");

	        jdbcTemplate.update(
	            "INSERT INTO had( had_id, besoin, address, numero_tel, date_rdv_had, creation_date, status, id_patient, code, materiel, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
	            had.getIdHad(), had.getBesoin(), had.getAddress(), had.getNumeroTel(), had.getDateRdvHad(), had.getCreationDate(), had.getStatus(), had.getIdPatient(), had.getCode(), had.getMateriel(), had.getImage()
	        );

	        return new ResponseEntity<>(new BasicResponse("Un RDV HAD a été envoyé", HttpStatus.OK), HttpStatus.OK);
	    } catch (org.springframework.dao.DuplicateKeyException ex) {
	        ex.printStackTrace();
	        return new ResponseEntity<>(new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new ResponseEntity<>(new BasicResponse("Error!", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	    }
	}


	private String generateRandomCode(int length) {
	    // Define characters that can be used in the random code
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();
	    StringBuilder code = new StringBuilder(length);
	    
	    // Generate random characters for the code
	    for (int i = 0; i < length; i++) {
	        int randomIndex = random.nextInt(characters.length());
	        char randomChar = characters.charAt(randomIndex);
	        code.append(randomChar);
	    }
	    
	    return code.toString();
	}

	public BasicResponse saveRatingHAD(Had had) {
	    try {
	        System.out.println("haad " + had.toString());

	        // Check if the idRdv is provided
	        if (had.getIdHad() == null || had.getIdHad().isEmpty()) {
	            return new BasicResponse("getIdHad is required for updating the record", HttpStatus.BAD_REQUEST);
	        }

	        // Set the rating value
	        Double rating = had.getRating();
	        if (rating == null) {
	            rating = 0.0; // Assuming the default rating is 0.0
	        }

	        // Update the record with the rating value
	        jdbcTemplate.update(
	            "UPDATE had SET rating = ? WHERE had_id = ?",
	            rating,
	            had.getIdHad()
	        );

	        return new BasicResponse("Rating updated successfully", HttpStatus.OK);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return new BasicResponse("Error updating rating", HttpStatus.BAD_REQUEST);
	    }
	}
	@Override
	public BasicResponse update(Had had) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public BasicResponse delete(String id) {
		try {
			List<Had> toDelete = getById(id);
			if (!toDelete.isEmpty()) {

				System.out.println("had " + id);
				//String id = rendezVous.getIdRdv();
				String sql = "DELETE FROM had where had_id=? ";
				Object[] args = new Object[] { id };
				jdbcTemplate.update(sql, args);
				System.out.println("Deleted Record with ID = " + id);

			}
			return new BasicResponse("Rendez Vous had supprimé : ", HttpStatus.OK);

		} catch (org.springframework.dao.DuplicateKeyException ex) {

			ex.printStackTrace();
			return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}


	private List<Had> getById(String id) {
		return jdbcTemplate.query("select * from had  where had_id=? ", new Object[] { id },
				new HadRowMapper());
	}
	
	@Override
	public BasicResponse changeStatus(String idHad, String status, String description) {
		try {
			List<Had> toUpdate = getbyId(idHad);
			if (!toUpdate.isEmpty()) {
				jdbcTemplate.update("UPDATE public.had\r\n"
						+ "	SET status=?\r\n"
						+ "	WHERE had_id=?;", 
						new Object[] { status , idHad });
				
				Notifications notification = new Notifications();
				notification.setCreationDate(new Date());
				notification.setIdNotif(UUID.randomUUID().toString().replace("-", ""));
				notification.setDescription(description);
				notification.setType("HAD");
				jdbcTemplate.update("INSERT INTO public.notifications(\r\n"
						+ "	id_notif, id_rdv, description, id_patient, creation_date, type)\r\n"
						+ "	VALUES (?, ?, ?, ?, ?, ?);",
						notification.getIdNotif(), idHad, description,
						toUpdate.get(0).getIdPatient(), notification.getCreationDate(), notification.getType());
				
				return new BasicResponse("Succès", HttpStatus.OK);
			} else {
				return new BasicResponse("Erreur", HttpStatus.OK);
			}

		} catch (org.springframework.dao.DuplicateKeyException ex) {

			ex.printStackTrace();
			return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}



	@Override
	public List<Had> getbyId(String id) {
		return jdbcTemplate.query("SELECT * FROM public.had\r\n"
				+ "	Where had_id=?; ", new Object[] { id },
				new HadRowMapper());
	}



	@Override
	public List<Had> getNewhad(String status) {
		return jdbcTemplate.query("SELECT *\r\n"
				+ "	FROM public.had\r\n"
				+ "	where status=?; ", new Object[] { status },
				new HadRowMapper());
	}
	
	public Map<String, Long> getCountByBesoin() {
	    String query = "SELECT besoin, COUNT(*) FROM had GROUP BY besoin";

	    // Execute the query and get the counts
	    Map<String, Long> besoinCounts = jdbcTemplate.query(query, (rs, rowNum) -> {
	        String besoin = rs.getString("besoin");
	        Long count = rs.getLong("count");
	        return new AbstractMap.SimpleEntry<>(besoin, count);
	    }).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	    // Define equivalent mappings
	    Map<String, String> equivalentMappings = new HashMap<>();
	    equivalentMappings.put("Pansemant", "ضمادة");
	    equivalentMappings.put("Ventilation", "تنفس");
	    equivalentMappings.put("Perfusion", "تشريب");
	    equivalentMappings.put("Prelevement", "عينة دم");
	    equivalentMappings.put("Kinesitherapie", "العلاج الطبيعي");

	    // Combine equivalent terms and calculate the new total count
	    long total = 0;
	    for (Map.Entry<String, String> entry : equivalentMappings.entrySet()) {
	        String term1 = entry.getKey();
	        String term2 = entry.getValue();
	        
	        Long count1 = besoinCounts.getOrDefault(term1, 0L);
	        Long count2 = besoinCounts.getOrDefault(term2, 0L);
	        Long combinedCount = count1 + count2;

	        // Update the map with the combined count for the first term
	        besoinCounts.put(term1, combinedCount);
	        
	        // Remove the second term from the map
	        besoinCounts.remove(term2);

	        // Add the combined count to the total
	        total += combinedCount;
	    }

	    // Add the "total" field to the map
	    besoinCounts.put("total", total);

	    return besoinCounts;
	}


	// Helper method to combine equivalent terms
	private void combineEquivalentTerms(Map<String, Long> counts, Map<String, String> equivalentMappings) {
	    for (Map.Entry<String, String> entry : equivalentMappings.entrySet()) {
	        String term1 = entry.getKey();
	        String term2 = entry.getValue();
	        
	        Long count1 = counts.getOrDefault(term1, 0L);
	        Long count2 = counts.getOrDefault(term2, 0L);
	        Long combinedCount = count1 + count2;

	        // Update the map with the combined count for both terms
	        counts.put(term1, combinedCount);
	        counts.put(term2, combinedCount);
	    }
	}
	public Map<String, Long> getCountByBesoinAndStatus() {
	    String query = "SELECT besoin, status, COUNT(*) FROM had GROUP BY besoin, status";

	    // Execute the query and get the counts
	    Map<String, Long> besoinCounts = jdbcTemplate.query(query, (rs, rowNum) -> {
	        String besoin = rs.getString("besoin");
	        String status = rs.getString("status");
	        Long count = rs.getLong("count");
	        return new AbstractMap.SimpleEntry<>(besoin + " (" + status + ")", count);
	    }).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	    // Define equivalent mappings
	    Map<String, String> equivalentMappings = new HashMap<>();
	    equivalentMappings.put("Pansemant", "ضمادة");
	    equivalentMappings.put("Ventilation", "تنفس");
	    equivalentMappings.put("Perfusion", "تشريب");
	    equivalentMappings.put("Prelevement", "عينة دم");
	    equivalentMappings.put("Kinesitherapie", "العلاج الطبيعي");

	    // Combine equivalent terms and calculate the new total counts for each status
	    Map<String, Long> statusCounts = new HashMap<>();
	    long totalEncours = 0;
	    long totalAnnule = 0;
	    long totalConfirme = 0;

	    for (Map.Entry<String, String> entry : equivalentMappings.entrySet()) {
	        String term1 = entry.getKey();
	        String term2 = entry.getValue();

	        Long count1Encours = besoinCounts.getOrDefault(term1 + " (Encours)", 0L);
	        Long count2Encours = besoinCounts.getOrDefault(term2 + " (Encours)", 0L);
	        Long combinedCountEncours = count1Encours + count2Encours;
	        totalEncours += combinedCountEncours;

	        Long count1Annule = besoinCounts.getOrDefault(term1 + " (Annulé)", 0L);
	        Long count2Annule = besoinCounts.getOrDefault(term2 + " (Annulé)", 0L);
	        Long combinedCountAnnule = count1Annule + count2Annule;
	        totalAnnule += combinedCountAnnule;

	        Long count1Confirme = besoinCounts.getOrDefault(term1 + " (Confirmé)", 0L);
	        Long count2Confirme = besoinCounts.getOrDefault(term2 + " (Confirmé)", 0L);
	        Long combinedCountConfirme = count1Confirme + count2Confirme;
	        totalConfirme += combinedCountConfirme;
	    }

	    // Add the counts for Encours, Annulé, and Confirmé to the statusCounts map
	    statusCounts.put("TotalEncours", totalEncours);
	    statusCounts.put("TotalAnnule", totalAnnule);
	    statusCounts.put("TotalConfirme", totalConfirme);

	    return statusCounts;
	}


}
