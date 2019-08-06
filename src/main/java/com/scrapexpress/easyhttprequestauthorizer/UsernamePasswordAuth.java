package com.scrapexpress.easyhttprequestauthorizer;


public class UsernamePasswordAuth extends AuthService{
	
	public UsernamePasswordAuth( String username,
			String password) {
		
		super(username, password);
		
		this.grantType = USERNAME_PASSWORD;

	}
	
	public OAuthResponse fetchToken() {

		return null;
	}

}
