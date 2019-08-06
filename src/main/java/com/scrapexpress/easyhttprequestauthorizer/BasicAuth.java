package com.scrapexpress.easyhttprequestauthorizer;


public class BasicAuth extends AuthService{
	
	public BasicAuth( String clientId,
			String clientSecret) {
		
		super(clientId, clientSecret);

	}
	
	public OAuthResponse fetchToken() {

		return null;
	}

}
