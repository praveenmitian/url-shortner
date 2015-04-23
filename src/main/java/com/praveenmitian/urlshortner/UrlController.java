package com.praveenmitian.urlshortner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class process the request based on the query.
 * 
 * @author PRAVEEN
 *
 */
@RestController
@RequestMapping("/api/v1")
public class UrlController {

	@Autowired
	UrlJDBC urlJDBC;

	/**
	 * Controller process the request and post method used to give the short url
	 * for given long url.
	 * 
	 * @param url
	 * @return Url
	 */
	@RequestMapping(value = "/shorten", method = RequestMethod.POST)
	public Url shortenUrl(@RequestBody Url url) {
		long id = new Date().getTime();
		String encodedId = UrlShortner.encode(id);
		urlJDBC.create(url.getLongUrl(), encodedId);
		return new Url(encodedId, url.getLongUrl());
	}

	/**
	 * Controller process the request and get method used to expand the short url.
	 * 
	 * @param urlId
	 * @return String
	 */
	@RequestMapping(value = "/expand/{urlId}", method = RequestMethod.GET)
	public String expandUrl(@PathVariable String urlId) {
		int count = urlJDBC.count(urlId);
		urlJDBC.update(urlId, count + 1);
		List<Url> urlList = urlJDBC.getUrl(urlId);
		if (urlList != null && urlList.size() > 0)
			return urlList.get(0).getLongUrl();
		else
			return "Match not found for \'" + urlId + "\'";
	}

	/**
	 * Controller process the request and get method display top 5 most viewed
	 * shorten urls.
	 * 
	 * @return List<Url>
	 */
	@RequestMapping(value = "/mostpopular", method = RequestMethod.GET)
	public List<Url> mostViewedUrls() {
		return urlJDBC.mostViewedUrls();
	}

	/**
	 * Controller process the request and get method display top 5 recently
	 * viewed shorten urls.
	 * 
	 * @return List<Map<String, Object>>
	 */
	@RequestMapping(value = "/recentlyviewed", method = RequestMethod.GET)
	public List<Map<String, Object>> latest() {
		return urlJDBC.recentlyViewedUrls();
	}

	/**
	 * Controller process the request and get method display list of shorten
	 * urls till date.
	 * 
	 * @return List<String>
	 */
	@RequestMapping(value = "/shorturllist", method = RequestMethod.GET)
	public List<String> all() {
		return urlJDBC.listOfShortenUrls();
	}

	/**
	 * Controller process the request and get method display total count of Url
	 * shorten till date.
	 * 
	 * @return int
	 */
	@RequestMapping(value = "/urlstotalcount", method = RequestMethod.GET)
	public int totalUrlsShortened() {
		return urlJDBC.totalUrlsShortened();
	}

	/**
	 * Controller process the request and get method display top 5 recently
	 * shorten urls.
	 * 
	 * @return List<Map<String, Object>>
	 */
	@RequestMapping(value = "/recentlyshorten", method = RequestMethod.GET)
	public List<Map<String, Object>> recentlyShortenUrls() {
		return urlJDBC.recentlyShortenUrls();
	}

	/**
	 * Controller process the request and get method returns each day count of
	 * shorten url for past 5 days.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/last5dayscount", method = RequestMethod.GET)
	public List<Map<String, Object>> chart() {
		List<Map<String, Object>> lm = urlJDBC.last5dayscount();
		Calendar c = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> dayCountMap = null;
		String date = null;
		for (int i = 0; i < 5; i++) {
			c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -i);
			date = sdf.format(c.getTime());
			dayCountMap = new HashMap<String, Object>();
			dayCountMap.put("DATE", c.getTime().getTime());
			dayCountMap.put("COUNT", 0);
			result.add(dayCountMap);
			for (Map<String, Object> map : lm) {
				if (((java.sql.Date) map.get("DATE")).toString().equals(date)) {
					dayCountMap.put("COUNT", map.get("COUNT"));
					break;
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/listurl", method = RequestMethod.GET)
	public List<Map<String, Object>> urlList() {
		return urlJDBC.urlList();
	}
}