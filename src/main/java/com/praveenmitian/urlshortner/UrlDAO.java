package com.praveenmitian.urlshortner;

import java.util.List;
import java.util.Map;

/**
 *This interface define method to implement by the class which implements this interface.  
 * 
 * @author PRAVEEN
 *
 */
public interface UrlDAO {

	/**
	 * This is the method to be used to create a record in the Student table.
	 */
	public void create(String longUrl, String shortUrl);

	/**
	 * This is the method to be used to list down a record from the Student
	 * table corresponding to a passed student id.
	 */
	public int count(String shortUrl);
	
	/**
	 * This method returns total count of url shorten till date.
	 * 
	 * @return int
	 */
	public int totalUrlsShortened();
	
	/**
	 * 
	 * This method update(increase) the count when specific url is hit.
	 * 
	 * @param shortUrl
	 * @param count
	 */
	public void update(String shortUrl, int count);
	
	/**
	 * This method returns top 5 most viewed shorten urls. 
	 * 
	 * @return List<Url>
	 */
	public List<Url> mostViewedUrls();
	
	/**
	 * This method returns top 5 recently viewed shorten url.
	 * 
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> recentlyViewedUrls();
	
	/**
	 * This method returns top 5 recently shorten url.
	 * 
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> recentlyShortenUrls();
	
	/**
	 * This method returns the total number of url shortened for the past 5 days.
	 * 
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> last5dayscount();
	
	/**
	 * Returns list of all  Url that is shortned.
	 * 
	 * @return List<String>
	 */
	public List<String> listOfShortenUrls();
	
	/**
	 * This is the method to be used to display the long Url for the given short Url id.
	 * 
	 * @param shortUrl
	 * @return List<Url>
	 */
	public List<Url> getUrl(String shortUrl);
	
	/**
	 * This method used to display display list of url shorten and its count.
	 * 
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> urlList();
}
