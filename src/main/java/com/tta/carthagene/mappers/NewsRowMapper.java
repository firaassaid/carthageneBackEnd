package com.tta.carthagene.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tta.carthagene.entities.News;
import com.tta.carthagene.entities.News;


public class NewsRowMapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs, int arg1) throws SQLException {

		
		

		News news = new News();
		news.setIdNews(rs.getString("id_news"));
		news.setTitle(rs.getString("title"));
		news.setDescription(rs.getString("description"));
		news.setCreationDate(rs.getDate("creation_date"));
		news.setDateNews(rs.getString("date_news"));
		news.setAttachment(rs.getString("attachment"));
		
		return news;
	}

}
