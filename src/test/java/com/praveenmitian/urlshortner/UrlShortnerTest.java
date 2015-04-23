package com.praveenmitian.urlshortner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UrlShortnerTest {
	
	@Test
	public void encodeTest() {
		long time = 1429535409589L;
		assertEquals("zkyUT3t", UrlShortner.encode(time));
	}
}
