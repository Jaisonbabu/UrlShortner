package org.cisco.demo.service;

import org.cisco.demo.model.Url;
import org.springframework.stereotype.Component;

@Component
public interface UrlShortnerService {
	
	public String getFullUrl(String shortUrl);
	
	public String createShortUrl(Url fullUrl);

}
