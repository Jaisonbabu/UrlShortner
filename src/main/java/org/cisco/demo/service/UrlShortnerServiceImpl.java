package org.cisco.demo.service;

import java.nio.charset.StandardCharsets;

import org.apache.commons.validator.routines.UrlValidator;
import org.cisco.demo.model.Url;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {

	@Autowired
	private StringRedisTemplate redis;

	@Autowired
	private PhishingService phishingService;

	@Override
	public String getFullUrl(String shortUrl) {
		final String url = redis.opsForValue().get(shortUrl);
		return url;
	}

	@Override
	public String createShortUrl(Url fullUrl) {
		String shortUrl = null;
		final String url = fullUrl.getRequestUrl();
		final UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });
		try {
			if (urlValidator.isValid(url) && !phishingService.isPhishUrl(url)) {
				final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
				redis.opsForValue().set(id, url);
				shortUrl = "http://localhost:9000/api/" + id;
			} else {
				return "The url is a phish url";
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shortUrl;
	}
}
