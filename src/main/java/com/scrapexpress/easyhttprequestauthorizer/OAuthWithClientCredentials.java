package com.scrapexpress.easyhttprequestauthorizer;

import java.net.URLEncoder;

import com.scrapexpress.entity.AuthException;
import com.scrapexpress.entity.PostBoardRequestPackage;

public class OAuthWithClientCredentials extends AuthService{
	
	public OAuthWithClientCredentials(String authEndpoint, String clientId,
			String clientSecret, String scope) {
		
		super(authEndpoint, clientId, clientSecret, scope, AuthService.CLIENT_CREDENTIALS);

	}
	
	public OAuthResponse fetchToken() throws AuthException{

	
		
		OAuthResponse response = null;
		try {
			
			String bodyString = 
					"grant_type=" + grantType.value()
					+ "&scope=" + URLEncoder.encode(scope, "utf-8");
			
			PostBoardRequestPackage requestPackage = buildOAuthRequestPackage(bodyString);
			
			response = new PostBoardRequestDispatcher().execute(requestPackage, OAuthResponse.class, new PostContentJsonConverter());
		
			
			
		} catch (Exception ex) {
			throw new AuthException("Failed to fetch token - " + ex.getMessage());
		}

		return response;
	}

}
