package com.mbvan.mbvan.common.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSocketClient {
	
	
	//Initialize
	public UDPSocketClient(String serverIPAddress,int serverPort){
		
	}
	
	public static String sendMessage(String serverIPAddress,int serverPort,String message) throws Exception{
				
		if(message==null){
			return "Message is null!";
		}
				
		String returnMsg;
		int TIMEOUT=2000;//��ʱʱ��
		byte bytesToSend[]=message.getBytes("UTF-8");//�跢�����
		InetAddress serverAddress=InetAddress.getByName(serverIPAddress);//�Է����յ�ַ
		int servPort=serverPort;//�Է����ն˿�
		
		
		// 1. UDP DatagramSocket
		DatagramSocket socket = new DatagramSocket();  
		  
		// 2��timeout  
		socket.setSoTimeout(TIMEOUT);  
		  
		// 3. construct package  
		DatagramPacket sendPacket = new DatagramPacket(bytesToSend,  
		    bytesToSend.length, serverAddress, servPort);  
		DatagramPacket receivePacket =  
		    new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);  
		  
		// 4.ָ�����ԵĴ���  
		int tries = 0; 
		int MAXTRIES=1;
		boolean receivedResponse = false;  
		 do  
		{  
		    socket.send(sendPacket);  
		    try  
		    {  
		        socket.receive(receivePacket);  
		   
		        if(!receivePacket.getAddress().equals(serverAddress))  
		        {  
		            throw new IOException("Received packet from an unknown source");  
		        }  
		        receivedResponse = true;  
		    }  
		    catch(InterruptedIOException e)  
		    {  
		        tries += 1;  
		        System.out.println("Timed out, " + (MAXTRIES - tries) + "");  
		    }  
		}while((!receivedResponse) && (tries < MAXTRIES));  
		  
		//������������Ϣ 
		returnMsg=new String(receivePacket.getData());
		 
		// ����Ƿ���յ����Ľ��з���  
		if(receivedResponse)  
		{  
		    System.out.println("Received from server: " + returnMsg);  
		}  
		else  
		{  
		    System.out.println("No response from server -- giving up.");  
		}  
		  
		// 5. �ر�socket  
		socket.close();  
		
		return receivedResponse ?returnMsg:"";
	}
	
	
//	public static String sendMessage1(String serverIPAddress,int serverPort,String message) throws Exception{
//		
//		if(message==null){
//			return "Message is null!";
//		}
//				
//		String returnMsg = "";
//		int TIMEOUT=10;//��ʱʱ��
//		byte bytesToSend[]=message.getBytes("UTF-8");//�跢�����
//		InetAddress serverAddress=InetAddress.getByName(serverIPAddress);//�Է����յ�ַ
//		int servPort=serverPort;//�Է����ն˿�
//		
//		
//		// 1. UDP DatagramSocket
//		DatagramSocket socket = new DatagramSocket();  
//		  
//		// 2��timeout  
////		socket.setSoTimeout(TIMEOUT);  
//		  
//		// 3. construct package  
//		DatagramPacket sendPacket = new DatagramPacket(bytesToSend,  
//		    bytesToSend.length, serverAddress, servPort);  
//		DatagramPacket receivePacket =  
//		    new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);  
//		  
//		// 4.ָ�����ԵĴ���  
//		int tries = 0; 
//		int MAXTRIES=1;
//		boolean receivedResponse = false;  
//	    socket.send(sendPacket);  
////	    socket.receive(receivePacket);  
////	    
//// 
////	    System.out.println("Received from server: " + receivePacket.getData()); 
////        if(!receivePacket.getAddress().equals(serverAddress))  
////        {  
////            throw new IOException("Received packet from an unknown source");  
////        }
////        returnMsg=new String(receivePacket.getData());
//		
//
//		socket.close();
//		  
//		// 5. �ر�socket  
//		  
//		
//		return receivedResponse ?returnMsg:"";
//	}	
	
	
		
	public static void main(String[] args) throws IOException {
		
		try{
			long a = System.currentTimeMillis();
			UDPSocketClient.sendMessage("127.0.0.1",60000,"Hello from Jerry!");
			//UDPSocketClient.sendMessage("16.158.169.71",60000,"Hello from Jerry!");
			//UDPSocketClient.sendMessage("127.0.0.1",9999,"Hello from Jerry!");
			long b = System.currentTimeMillis();
			
			System.out.println(b-a);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
