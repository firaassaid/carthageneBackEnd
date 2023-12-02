package com.tta.carthagene.repositories;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.tta.carthagene.dao.NewsInterface;
import com.tta.carthagene.entities.News;
import com.tta.carthagene.entities.Notifications;
import com.tta.carthagene.entities.RendezVous;
import com.tta.carthagene.mappers.NewsRowMapper;
import com.tta.carthagene.response.BasicResponse;
import com.tta.carthagene.utils.Utils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class NewsRepository implements NewsInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<News> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from  news order By date_news DESC;", new NewsRowMapper());

	}

	

	@Override
	public BasicResponse save(MultipartFile fichier, News news) {
		try {
			String path_traitement = Utils.NewsAttachment;
			byte[] bytes = fichier.getBytes();
			// Creating the directory to store file
			String rootPath = path_traitement;
			File dir = new File(rootPath);
			final String OLD_FORMAT = "dd/MM/yyyy";
			final String NEW_FORMAT = "yyyy-MM-dd";
			String newDateString;

			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
			Date d = sdf.parse(news.getDateNews());
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(d);
			/*DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
			String strDate = dateFormat.format(news.getDateNews());*/
			System.out.println("news.getDateNews() "+news.getDateNews());
			if (!dir.exists())
				dir.mkdirs();
			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath() + "\\" + newDateString + ".png");
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			System.out.println("news " + news.toString());
			news.setAttachment(dir.getAbsolutePath() + "\\" + newDateString + ".png");
		     news.setCreationDate(new Date());
			news.setIdNews(UUID.randomUUID().toString().replace("-", ""));
			jdbcTemplate.update(
					"INSERT INTO news( id_news, title, description, date_news, attachment, creation_date) VALUES (?, ?, ?, ?, ?, ?);",
					news.getIdNews(),news.getTitle(),news.getDescription(),news.getDateNews(),news.getAttachment(),news.getCreationDate()
					);

			return new BasicResponse("Une News a été envoyée", HttpStatus.OK);
			
		} catch (org.springframework.dao.DuplicateKeyException ex) {
			ex.printStackTrace();
			return new BasicResponse("Element existant!", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}

	@Override
	public BasicResponse update(News News) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResponse delete(String id) {
		try {

			List<News> newss = this.getById(id);
			if(!newss.isEmpty()) {
			jdbcTemplate.update(
					"DELETE FROM public.news\r\n"
					+ "	WHERE id_news=?;",
					id
					);

			return new BasicResponse("suppression reussite", HttpStatus.OK);
			}else {
			return new BasicResponse("Element introuvable!", HttpStatus.BAD_REQUEST);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			return new BasicResponse("Error!", HttpStatus.BAD_REQUEST);

		}
	}



	@Override
	public List<News> getById(String id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM public.news where id_news=?;",new Object[]{id}, new NewsRowMapper());
	}

	public ResponseEntity<InputStreamResource> getImage( String id)
			throws IOException  {

		List<News> news=getById(id);
		String attachement = news.get(0).getAttachment();
	System.out.println("rootPath "+attachement);
		InputStream is = null;
	
		try {
			
			
			// asume that it was a PDF file
			//InputStreamResource inputStreamResource = new InputStreamResource(is);
		
				is = new FileInputStream(new File(attachement));
				// asume that it was a PDF file
				HttpHeaders responseHeaders = new HttpHeaders();
				InputStreamResource inputStreamResource = new InputStreamResource(is);
				responseHeaders.setContentType(MediaType.IMAGE_JPEG);
				
			return new ResponseEntity<InputStreamResource>(inputStreamResource, responseHeaders, HttpStatus.OK);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
