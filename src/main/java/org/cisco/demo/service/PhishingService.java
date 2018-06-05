package org.cisco.demo.service;

import org.json.JSONException;
import org.springframework.stereotype.Component;

@Component
public interface PhishingService {

	public boolean isPhishUrl(String url) throws JSONException;
}
