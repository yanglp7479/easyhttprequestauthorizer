package com.scrapexpress.entity;

import java.util.Date;
import java.util.List;

/**
 * The abstraction of general account accessing to remote post board services
 * 
 * @author andy
 *
 */
public interface IAccount {
	
	
	public void setOwnerId(String ownerId);
	public void setPassword(String password);
	public void setUsername(String username);
	public void setAccessToken(String accessToken);
	public void setAccessTokenExpireIn(int seconds);
	public void setAccessTokenFrom(Date from);
	public void setRefreshToken(String refreshToken);
	
	public void setScopes(List<String> scopes);
	
	public List<String> getScopes();
	
	public String getUsername() ;

	public String getPassword() ;

	public String getAdvertiserId();

	public String getAccessToken() ;

	public int getAccessTokenExpireIn();

	public Date getAccessTokenFrom() ;
	
	public String getRefreshToken();
	


}
