package com.praveenmitian.urlshortner;

/**
 * This class used to define constants.
 * 
 * @author PRAVEEN
 *
 */
public final class Constants {
	public static final String LONGURL = "longUrl";
	public static final String SHORTURL = "shortUrl";
	public static final String COUNT = "count";
	public static final String CREATED_AT = "createdDate";
	public static final String LAST_ACCESSED_AT = "modifiedDate";
	public static final String INSERT_QUERY = "insert into Url (" + LONGURL
			+ "," + SHORTURL + "," + CREATED_AT + "," + LAST_ACCESSED_AT + ","
			+ COUNT + ") values (?, ?, ?, ?, ?)";
	public static final String MOST_VIEWED_URLS_QUERY = "select " + SHORTURL
			+ "," + COUNT + " from Url ORDER BY " + COUNT
			+ " DESC FETCH FIRST 5 ROWS ONLY";
	public static final String RECENTLY_VIEWED_URLS_QUERY = "select "
			+ SHORTURL + ", " + LAST_ACCESSED_AT + " from Url where modifiedDate>createdDate ORDER BY "
			+ LAST_ACCESSED_AT + " DESC FETCH FIRST 5 ROWS ONLY";	public static final String ACCESS_COUNT_QUERY = "select " + COUNT
			+ " from Url where " + SHORTURL + "= ?";
	public static final String UPDATE_COUNT_QUERY = "update Url set " + COUNT
			+ " = ? where " + SHORTURL + " = ?";
	public static final String UPDATE_LAST_ACCESSED_QUERY = "update Url set "
			+ LAST_ACCESSED_AT + " = ? where " + SHORTURL + "= ?";
	public static final String LIST_OF_SHORTEN_URLS = "select " + COUNT + ","
			+ SHORTURL + " from Url";
	public static final String TOTAL_URLS_COUNT_QUERY = "select count( "
			+ SHORTURL + ") from Url";
	public static final String RECENTLY_SHORTEN_URLS_QUERY = "select "
			+ SHORTURL + ", " + CREATED_AT + " from Url ORDER BY " + CREATED_AT
			+ " DESC FETCH FIRST 5 ROWS ONLY";
	public static final String SELECT_FOR_GIVEN_URLS_QUERY = "select * from Url where "
			+ SHORTURL + " =?";
	public static final String COUNT_FOR_LAST5DAYS_QUERY = "select COUNT(CREATEDDATE)as count,DATE(CREATEDDATE)as date from Url GROUP BY DATE(CREATEDDATE) ORDER BY DATE(CREATEDDATE) DESC FETCH FIRST 5 ROWS ONLY";
}
