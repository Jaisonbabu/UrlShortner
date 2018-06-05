package org.cisco.demo.controllers;

import javax.servlet.http.HttpServletResponse;

import org.cisco.demo.model.Url;
import org.cisco.demo.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
public class UrlController {

	@Autowired
	private UrlShortnerService urlShortnerservice;

	@GetMapping(value = "/{shortUrl}")
	public void redirectShortUrl(@PathVariable String shortUrl, HttpServletResponse resp) throws Exception {
		final String url = urlShortnerservice.getFullUrl(shortUrl);
		if (url != null)
			resp.sendRedirect(url);
		else
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<String> createShortUrl(@RequestBody Url fullUrl) {
		final String shorturl = urlShortnerservice.createShortUrl(fullUrl);
		if (shorturl != null) {
			return new ResponseEntity<>(shorturl, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
