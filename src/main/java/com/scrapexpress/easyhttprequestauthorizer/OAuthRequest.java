package com.scrapexpress.easyhttprequestauthorizer;

import com.scrapexpress.entity.PostBoardRequest;

public class OAuthRequest implements PostBoardRequest{
	
	private String url;
	
	@Override
	public MethodType getMethodType() {
		
		return MethodType.POST;
	}

	@Override
	public String getContentType() {
		
		return CONTENT_TYPE_URLENCODED;
	}

	@Override
	public String getUrl() {
		
		return this.url;
	}

	@Override
	public String getAccept() {
		
		return ACCEPT_JSON;
	}
	
	

	@Override
	public void setUrl(String url) {
		this.url = url;
		
	}

	@Override
	public Object getData() {
		
		return null;
	}

}
