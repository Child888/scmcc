package com.mbvan.mbvan.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;
 
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class HttpsClient {
	
	/**
	 * send https request to specific url
	 * and get return as string
	 * 
	 * */
	public static String SendRequest(String httpsUrl, String requestMethod, String proxyHost, String proxyPort, boolean printCertInfo){
		
		/**confirm exists url parameter*/
		if(httpsUrl==null){
			return "No URL input!";
		}
				
		/**configurate proxy host and port if have*/
		if(proxyHost!=null&&proxyPort !=null){
			System.setProperty("https.proxyHost", proxyHost);  
			System.setProperty("https.proxyPort", proxyPort); 
		}
		
		URL url=null;
		HttpsURLConnection con=null;
		StringBuffer sb = new StringBuffer();
		BufferedReader br=null;
		
		try{
			url = new URL(httpsUrl);
			con = (HttpsURLConnection)url.openConnection();
		    con.setRequestMethod((requestMethod!=null&&"POST".equals(requestMethod)?requestMethod:"GET"));
			
		    /**whether need print https information*/
		    if(printCertInfo){
		    	print_https_cert(con);
		    }
		    
		    //System.out.println("******** response content ********");			
		   
		    br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
	 
		    String input;
	 
		    while ((input = br.readLine()) != null){
			   sb.append(input.trim());
		    }
			
		    br.close();
		    
		}catch (MalformedURLException e) {
		     e.printStackTrace();
	    }catch (IOException e) {
		     e.printStackTrace();
	    }
		
		return sb.toString();
	}
	
	/**
	 * assist class for print https information
	 * */
	private static void print_https_cert(HttpsURLConnection con){
		 
	    if(con!=null){
	 
	      try {
	 
		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Cipher Suite : " + con.getCipherSuite());
		System.out.println("\n");
	 
		Certificate[] certs = con.getServerCertificates();
		for(Certificate cert : certs){
		   System.out.println("Cert Type : " + cert.getType());
		   System.out.println("Cert Hash Code : " + cert.hashCode());
		   System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
		   System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
		   System.out.println("\n");
		}
	 
		} catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	 
	     }
	 
	   }
	
	
	
	
}
