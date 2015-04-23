package com.praveenmitian.urlshortner;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UrlJDBC implements UrlDAO {

	Logger LOGGER = Logger.getLogger(UrlJDBC.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void create(String longUrl, String shortUrl) {
		long time = System.currentTimeMillis();
		Timestamp createdDate = new java.sql.Timestamp(time);
		Timestamp modifiedDate = new java.sql.Timestamp(time);
		int count = 1;
		jdbcTemplate.update(Constants.INSERT_QUERY, longUrl, shortUrl,
				createdDate, modifiedDate, count);
	}

	@Override
	public int count(String shortUrl) {
		int count = jdbcTemplate.queryForObject(Constants.ACCESS_COUNT_QUERY,
				new Object[] { shortUrl }, Integer.class);
		return count;
	}

	@Override
	public void update(String shortUrl, int count) {
		long time = System.currentTimeMillis();
		Timestamp modifiedDate = new java.sql.Timestamp(time);
		jdbcTemplate.update(Constants.UPDATE_COUNT_QUERY, count, shortUrl);
		jdbcTemplate.update(Constants.UPDATE_LAST_ACCESSED_QUERY, modifiedDate,
				shortUrl);
	}

	@Override
	public List<Url> getUrl(String shortUrl) {
		LOGGER.info("Query:" + Constants.SELECT_FOR_GIVEN_URLS_QUERY + ":" + shortUrl);
		List<Url> urlList = jdbcTemplate.query(Constants.SELECT_FOR_GIVEN_URLS_QUERY, new Object[] { shortUrl },
				new UrlMapper());
		return urlList;
	}

	@Override
	public List<Url> mostViewedUrls() {
		List<Url> urlList = jdbcTemplate.query(
				Constants.MOST_VIEWED_URLS_QUERY, new RowMapper<Url>() {
					@Override
					public Url mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Url url = new Url();
						url.setShortUrl(rs.getString(Constants.SHORTURL));
						url.setCount(rs.getInt(Constants.COUNT));
						return url;
					}
				});
		return urlList;
	}
	
	@Override
	public List<Map<String, Object>> recentlyViewedUrls() {
		List<Map<String, Object>> urlList = jdbcTemplate
				.queryForList(Constants.RECENTLY_VIEWED_URLS_QUERY);
		return urlList;
	}

	@Override
	public List<String> listOfShortenUrls() {
		List<String> urlList = jdbcTemplate.query(
				Constants.LIST_OF_SHORTEN_URLS, new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString(Constants.SHORTURL);
					}
				});
		return urlList;
	}

	@Override
	public List<Map<String, Object>> recentlyShortenUrls() {
		List<Map<String, Object>> urlList = jdbcTemplate
				.queryForList(Constants.RECENTLY_SHORTEN_URLS_QUERY);
		return urlList;
	}

	@Override
	public List<Map<String, Object>> last5dayscount() {
		List<Map<String, Object>> urlList = jdbcTemplate.queryForList(Constants.COUNT_FOR_LAST5DAYS_QUERY);
		return urlList;
	}

	@Override
	public int totalUrlsShortened() {
		int count = jdbcTemplate.queryForObject(
				Constants.TOTAL_URLS_COUNT_QUERY, new Object[] {},
				Integer.class);
		return count;
	}
	@Override
	public List<Map<String, Object>> urlList() {
		List<Map<String, Object>> urlList = jdbcTemplate.queryForList(Constants.LIST_OF_SHORTEN_URLS);
		return urlList;
	}
}