package com.scrapexpress.entity;



/**
 * The super type of request to remove boarding services, any request object to access remote boarding services must be the sub type of this interface
 * 
 * @author andy
 *
 */
public interface PostBoardRequest {
	
	public static  enum MethodType{GET, POST, HEAD,PUT,PATCH,DELETE,FTP}
	
	
	public static final String ACCEPT_JSON = "application/json";
	public static final String ACCEPT_XML = "application/xml";
	public static final String CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded";
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	
	
	public static final String ENTITY_HEADER = "$_ENTITY_REQUEST";
	
	
	
	public MethodType getMethodType();
	
	public String getContentType();
	
	public void setUrl(String url);
	public String getUrl();
	
	public String getAccept();
	
	public Object getData();
	

}
