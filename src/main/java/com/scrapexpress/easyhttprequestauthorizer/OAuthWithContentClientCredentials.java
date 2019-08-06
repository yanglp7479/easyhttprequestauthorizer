package com.scrapexpress.easyhttprequestauthorizer;

import java.net.URLEncoder;

import com.scrapexpress.entity.PostBoardRequestPackage;

public class OAuthWithContentClientCredentials extends AuthService{
	
	public OAuthWithContentClientCredentials(String authEndpoint, String clientId,
			String clientSecret, String scope) {
		
		super(authEndpoint, clientId, clientSecret, scope, AuthService.CLIENT_CREDENTIALS);

	}
	
	public OAuthResponse fetchToken() {

	
		
		OAuthResponse response = null;
		try {
			
			
			
			String bodyString = "client_id="+URLEncoder.encode(clientId,"utf-8")+"&client_secret="+URLEncoder.encode(clientSecret,"utf-8")+"&grant_type="+ grantType.value();
			
			PostBoardRequestPackage requestPackage = new PostBoardRequestPackage();
			requestPackage.setRequest(new OAuthRequest());
			requestPackage.setUrl(authEndpoint);
			
			requestPackage.setBody(bodyString);
			
			response = new PostBoardRequestDispatcher().execute(requestPackage, OAuthResponse.class, new PostContentJsonConverter());
		
			
			
		} catch (Exception ex) {

		}

		return response;
	}

}
