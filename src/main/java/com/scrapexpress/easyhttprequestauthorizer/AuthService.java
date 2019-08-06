package com.scrapexpress.easyhttprequestauthorizer;

import java.util.Date;

import org.apache.commons.net.util.Base64;

import com.scrapexpress.entity.AuthException;
import com.scrapexpress.entity.IAccount;
import com.scrapexpress.entity.PostBoardRequestPackage;

public abstract class AuthService {
	
	public static final GrantType AUTHORISATION_CODE = GrantType.AUTHORISATION_CODE;
	public static final GrantType CLIENT_CREDENTIALS = GrantType.CLIENT_CREDENTIALS;
	public static final GrantType REFRESH_TOKEN = GrantType.REFRESH_TOKEN;
	public static final GrantType BASIC = GrantType.BASIC;
	public static final GrantType USERNAME_PASSWORD = GrantType.USERNAME_PASSWORD;
	
	public enum GrantType{
		BASIC("basic"),
		AUTHORISATION_CODE("authorization_code"),
		CLIENT_CREDENTIALS("client_credentials"),
		REFRESH_TOKEN("refresh_token"),
		USERNAME_PASSWORD("refresh_token");
		
		String value;
		GrantType(String value){
			this.value = value;
		}
		
		public String value(){
			return this.value;
		}
		
	}

	protected String authEndpoint;
	protected String clientId;
	protected String clientSecret;
	protected String redirectUri;
	protected String authorisationCode;

	protected GrantType grantType ;
	protected String scope = "";
	protected String refreshToken = "";
	
	protected int refreshTokenTimeBeforeExpired = 1000 ;
	
	
	
	protected IAccount existingAccount;
	
	
	public AuthService(String authEndpoint, String clientId,
			String clientSecret, String redirectUri, String authorisationCode) {
		this.authEndpoint = authEndpoint;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
		this.authorisationCode = authorisationCode;

		this.grantType = AUTHORISATION_CODE;

	}

	public AuthService(String authEndpoint, String clientId,
			String clientSecret, String scopeOrRefreshToken,GrantType grantType) {
		this.authEndpoint = authEndpoint;
		this.clientId = clientId;
		this.clientSecret = clientSecret;

		this.grantType = grantType;
		if(REFRESH_TOKEN.equals(grantType)){
			this.refreshToken = scopeOrRefreshToken;
		}else{
			this.scope = scopeOrRefreshToken;
		}
		

	}
	
	public AuthService(String clientId,
			String clientSecret) {
		
		this.clientId = clientId;
		this.clientSecret = clientSecret;

		this.grantType = BASIC;

	}
	
	
	
	public AuthService setExistAccount(IAccount existingAccount){
		this.existingAccount  = existingAccount;
		
		return this;
	}
	
	
	
	
	public void authoriseRequest(PostBoardRequestPackage requestPackage) throws AuthException{
		
		if(BASIC.equals(this.grantType)){
			requestPackage.addHeader("Authorization", "Basic " + Base64.encodeBase64StringUnChunked((clientId+":"+clientSecret).getBytes()));
			
		}else if(USERNAME_PASSWORD.equals(this.grantType)){
			requestPackage.addHeader("Username", clientId);
			requestPackage.addHeader("Password", clientSecret);
		}else{
			OAuthResponse response = fetchCachedToken();
			
			requestPackage.addHeader("Authorization", "Bearer "+ response.getAccessToken());
			
		}
				
	}
	
	public OAuthResponse fetchCachedToken( ) throws AuthException{
		
		OAuthResponse response = null;
		
		if(existingAccount != null){
			
			//get cached token from existing account
			response = new OAuthResponse();
			response.setAccessToken(existingAccount.getAccessToken());
			response.setRefreshToken(existingAccount.getRefreshToken());
			response.setExpiresIn( existingAccount.getAccessTokenExpireIn());
			
			//decide if the token expired
			String accessToken = response.getAccessToken();
			int expireIn = response.getExpiresIn();
			Date from = existingAccount.getAccessTokenFrom();
			if(accessToken==null 
					|| accessToken.equals("") 
					|| from == null 
					|| ((from.getTime() + ((long) expireIn) * 1000) - new Date().getTime()   <= refreshTokenTimeBeforeExpired) ){
				
				response  = fetchToken( );
				if( response!= null){
					accessToken = response.getAccessToken();
					existingAccount.setAccessToken(accessToken);
					existingAccount.setAccessTokenExpireIn(response.getExpiresIn());
					existingAccount.setAccessTokenFrom( new Date());
					existingAccount.setRefreshToken(response.getRefreshToken());
				}		
			}
		}
		
		return response;
		
	}
	
	
	public abstract OAuthResponse fetchToken() throws AuthException;
	
	protected PostBoardRequestPackage buildOAuthRequestPackage(String bodyString){
		PostBoardRequestPackage requestPackage = new PostBoardRequestPackage();
		requestPackage.setRequest(new OAuthRequest());
		requestPackage.setUrl(authEndpoint);
		requestPackage.addHeader("Authorization", "Basic " + Base64.encodeBase64String((clientId+":"+clientSecret).getBytes(),false));
		
		requestPackage.setBody(bodyString);
		
		return requestPackage;
	}

}
