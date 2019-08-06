package com.scrapexpress.easyhttprequestauthorizer;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

import com.scrapexpress.entity.AuthException;
import com.scrapexpress.entity.PostBoardRequestPackage;

public class OAuthWithAuthorisationCode extends AuthService{
	
	public OAuthWithAuthorisationCode(String authEndpoint, String clientId,
			String clientSecret, String redirectUri,String authorisationCode) {
		
		super(authEndpoint, clientId, clientSecret, redirectUri, authorisationCode);

	}
	
	public OAuthResponse fetchToken() throws AuthException {

		// String client_id =
				// DomainConfig.getDomainClientId();//"client_dd71cb531bbe4e5b91ea6e4af9475812";
				// String client_secret =DomainConfig.getDomainClientSecret();
				// //"secret_83979d148c89ede728814c5733cf2586";
				// String redirect_uri =
				// "https://testament.clicktocloud.com/CTCAuth/auth/domainoauth_callback";
				//
				// String auth_code = req.getParameter("code");

				// String finalUrl = BasicConfig.get("domain_default_oauth_url") ;
				
				
		
		OAuthResponse response = null;
		try {
			
			String bodyString =  
					"code=" + URLEncoder.encode(authorisationCode, "utf-8")
					+ "&grant_type=" + URLEncoder.encode(grantType.value(),"utf-8")
					+ "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8");
			
			PostBoardRequestPackage requestPackage = buildOAuthRequestPackage(bodyString);
			
			response = new PostBoardRequestDispatcher().execute(requestPackage, OAuthResponse.class, new PostContentJsonConverter());
		
			if(response!=null
					&&!StringUtils.isEmpty(response.getRefreshToken())
					&&!StringUtils.isEmpty( this.refreshTokenPropertyName)){
				RuntimeDataManager.storeProperty(refreshTokenPropertyName, response.getRefreshToken());
			}else if(response!=null){
				throw new Exception(response.getHttpMessage());
			}
			
		} catch (Exception ex) {
			throw new AuthException("Failed to fetch token - " + ex.getMessage());
		}

		return response;
	}
	
	

}
