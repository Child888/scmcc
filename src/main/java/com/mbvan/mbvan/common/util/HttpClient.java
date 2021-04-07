package com.mbvan.mbvan.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpClient {
	

	public String requestGet(String url){
		return responseToString(doGet(url));
	}

	public String requestPost(String url, Object requestBody){
		return responseToString(doPost(url,requestBody));
	}	
	
	public static String responseToString(HttpResponse response){
		try {
			if(response == null ){
				return "";
			}
			HttpEntity entity = response.getEntity();  
			System.out.println("----------------------------------------");  
			System.out.println(response.getStatusLine());
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8")); 
			StringBuilder sb = new StringBuilder();
			String line = null;  
			while ((line = reader.readLine()) != null) {  
				sb.append(line);
			} 
			String responseStr = sb.toString();
			reader.close();
			return responseStr;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "";
	}

	
	private HttpResponse doGet(String url){
		
		DefaultHttpClient httpclient = new DefaultHttpClient();		
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return response;
	}
	
	private HttpResponse doPost(String url, Object requestBody){
		
		DefaultHttpClient httpclient = new DefaultHttpClient();		
		HttpPost httppost = new HttpPost(url);
		HttpResponse response = null;
		try {
			String reqStr = "";
			
			if(requestBody != null){
				if(requestBody instanceof String){
					reqStr = (String)requestBody;
				}
				else{
					JsonConverter jsonConverter = new JsonConverter();
					reqStr = jsonConverter.toJson(requestBody);
				}
				HttpEntity entity = new StringEntity(reqStr);
				httppost.setEntity(entity);
			}
	
			response = httpclient.execute(httppost);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return response;
	}	
	
}
