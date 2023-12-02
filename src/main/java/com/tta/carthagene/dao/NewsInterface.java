package com.tta.carthagene.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.tta.carthagene.entities.News;
import com.tta.carthagene.response.BasicResponse;

public interface NewsInterface {


	List<News> findAll();
	BasicResponse save(MultipartFile fichier,News news);
	BasicResponse update(News news);
	BasicResponse delete(String id);
	List<News> getById(String id);
	 ResponseEntity<InputStreamResource> getImage(String image)	throws IOException ;
}
