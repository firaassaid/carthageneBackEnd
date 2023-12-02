package com.tta.carthagene.controllers;

import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tta.carthagene.dao.NewsInterface;
import com.tta.carthagene.entities.News;
import com.tta.carthagene.response.BasicResponse;
import java.io.File;
import java.io.FileInputStream;
import javax.activation.FileTypeMap;
@CrossOrigin("*")
@RestController
@RequestMapping("/news")
public class NewsController {
	@Autowired
	NewsInterface newsInterface;

	@GetMapping
	public List<News> findAll() {

		return newsInterface.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public BasicResponse delete(@PathVariable("id") String id) {
		System.out.println(id);
		return newsInterface.delete(id);
	}
	
	@PostMapping("/save")
	public BasicResponse save(@RequestPart("fichier") MultipartFile fichier, @RequestPart("news") String news) throws JsonMappingException, JsonProcessingException {
		News newss = new ObjectMapper().readValue(news, News.class);
		return newsInterface.save(fichier,newss);
	}

	@PutMapping("/update")
	public BasicResponse update(@RequestBody News news) {
		return newsInterface.update(news);
	}
	
	
	@PostMapping("/showme")
	public ResponseEntity<byte[]> getImage() throws IOException{
	    File img = new File("C:\\Users\\TTA\\Documents\\news\\attachment\\2022-10-27.png");
	    return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<InputStreamResource> getPathByFileNameInteretCou(@PathVariable("id") String id)
			throws IOException {
		return newsInterface.getImage(id);
	}
}
