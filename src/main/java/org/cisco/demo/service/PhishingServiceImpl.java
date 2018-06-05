package org.cisco.demo.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PhishingServiceImpl implements PhishingService {

	@Override
	public boolean isPhishUrl(String url) throws JSONException, RestClientException {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

		final MultiValueMap<String, String> formVars = new LinkedMultiValueMap<>();
		formVars.add("url", url);
		formVars.add("format", "json");
		formVars.add("app_key", "49f3ad4bce551e627892beffa8e5d283d326b8a93ddc6601018b40cccc6396d5");

		String response = restTemplate.postForObject("https://checkurl.phishtank.com/checkurl/", formVars,
				String.class);
		JSONObject jsonObject = new JSONObject(response);
		boolean valid = isValidPhish(jsonObject);
		return valid;
	}

	private boolean isValidPhish(JSONObject jsonObject) {
		JSONObject results = jsonObject.getJSONObject("results");
		System.out.println(results);
		if (results.getBoolean("in_database") && results.getBoolean("valid")) {
			return true;
		} else {
			return false;
		}
			
	}

}
