package com.praveenmitian.urlshortner;

/**
 * This class used to set and get the url and count values.
 * 
 * @author PRAVEEN
 *
 */
public class Url {
	private String shortUrl;
	private String longUrl;
	private int count;

	public Url() {
	}

	/**
	 * Constructor used to initialize the value.
	 * 
	 * @param shortUrl
	 * @param longUrl
	 */
	public Url(String shortUrl, String longUrl) {
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
	}

	/**
	 * Setter method used to set shorturl value.
	 * 
	 * @param shortUrl
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	/**
	 * Getter method used to get shorturl.
	 * 
	 * @return String
	 */
	public String getShortUrl() {
		return shortUrl;
	}

	/**
	 * Setter method used to set count value.
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Getter method used to get count value.
	 * 
	 * @return int
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Getter method used to get long url.
	 * 
	 * @return String
	 */
	public String getLongUrl() {
		return longUrl;
	}

	/**
	 * Setter method used to set long url.
	 * 
	 * @param longUrl
	 */
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	@Override
	public String toString() {
		return String.format("Url[shortUrl='%s', longUrl='%s']", shortUrl,
				longUrl);
	}

}
