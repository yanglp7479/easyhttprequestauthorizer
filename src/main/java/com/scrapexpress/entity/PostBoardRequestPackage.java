package com.scrapexpress.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.scrapexpress.entity.PostBoardRequest.MethodType;

public class PostBoardRequestPackage {
	
	private MethodType methodType;
	private String contentType;
	private String accept;
	private Map<String, String> headers = new HashMap<String, String>();

	private PostBoardRequest request;
	
	private String body;
	
	private String url;
	
	
	
	public void addHeader(String key, String value){
		headers.put(key, value);
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}



	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public PostBoardRequest getRequest() {
		return request;
	}

	public void setRequest(PostBoardRequest request) {
		this.request = request;
		
		if(request != null){
			methodType = request.getMethodType();
			contentType = request.getContentType();
			if(! StringUtils.isEmpty( contentType )){
				addHeader("Content-Type", contentType);
			}
			url = request.getUrl();
			accept = request.getAccept();
			if(! StringUtils.isEmpty( accept )){
				addHeader("Accept", accept);
			}
			
		}
	}

	public MethodType getMethodType() {
		return methodType;
	}

	public void setMethodType(MethodType methodType) {
		this.methodType = methodType;
	}
	
	public boolean isGet(){
		return this.methodType == MethodType.GET;
	}
	
	public boolean isPOST(){
		return this.methodType == MethodType.POST;
	}
	
	
	public boolean isHead(){
		return this.methodType == MethodType.HEAD;
	}
	
	
	
	public boolean isPut(){
		return this.methodType == MethodType.PUT;
	}
	
	public boolean isPatch(){
		return this.methodType == MethodType.PATCH;
	}
	
	public boolean isDelete(){
		return this.methodType == MethodType.DELETE;
	}
	
	public boolean isFtp(){
		return this.methodType == MethodType.FTP;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}
	
	
	

}
