package org.cisco.demo.model;

public class Url {
	
	 private String requestUrl;
	 
	 public Url() {
		 
	 }
	 
	 public Url (String url) {
		 this.requestUrl = url;	
	 }

	/**
	 * @return the requestUrl
	 */
	public String getRequestUrl() {
		return requestUrl;
	}

	/**
	 * @param requestUrl the requestUrl to set
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

}
