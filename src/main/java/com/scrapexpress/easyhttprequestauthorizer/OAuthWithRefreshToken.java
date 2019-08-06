package com.scrapexpress.easyhttprequestauthorizer;

import com.scrapexpress.entity.AuthException;
import com.scrapexpress.entity.PostBoardRequestPackage;

public class OAuthWithRefreshToken extends AuthService{
	
	public OAuthWithRefreshToken(String authEndpoint, String clientId,
			String clientSecret, String refreshToken) {
		
		super(authEndpoint, clientId, clientSecret, refreshToken, AuthService.REFRESH_TOKEN);

	}
	
	public OAuthResponse fetchToken() throws AuthException {

	
		
		OAuthResponse response = null;
		try {
			
			String bodyString = "grant_type="+grantType.value()+"&refresh_token="+refreshToken;
			
			PostBoardRequestPackage requestPackage = buildOAuthRequestPackage(bodyString);
			
			response = new PostBoardRequestDispatcher().execute(requestPackage, OAuthResponse.class, new PostContentJsonConverter());
		
			
			
		} catch (Exception ex) {
			throw new AuthException("Failed to fetch token - " + ex.getMessage());
		}

		return response;
	}

}
