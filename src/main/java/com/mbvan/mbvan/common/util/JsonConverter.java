package com.mbvan.mbvan.common.util;

import java.io.IOException;

import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {

	private static Logger logger = LoggerFactory.getLogger(JsonConverter.class);

	private ObjectMapper mapper;

	public JsonConverter() {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
	}


	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}


	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}



	public ObjectMapper getMapper() {
		return mapper;
	}
	
	
	public static void main(String args[]){
//		JsonConverter jc = new JsonConverter();
//		String json = " {    \"responseHeader\":    {       \"result\": 1,       \"resultCode\": null,       \"resultDescription\": null    },    \"singleData\":    [             {          \"id\": 15,          \"userId\": 10195,          \"metro\":          {             \"id\": 3,             \"title\": \"Massages\",             \"html\": \"metro/Massages.jsp\"          },          \"num\": 0       },             {          \"id\": 16,          \"userId\": 10195,          \"metro\":          {             \"id\": 5,             \"title\": \"Eco Driving\",             \"html\": \"metro/EcoDriving.jsp\"          },          \"num\": 1       },             {          \"id\": 17,          \"userId\": 10195,          \"metro\":          {             \"id\": 6,             \"title\": \"Route Planning\",             \"html\": \"metro/RoutePlanning.jsp\"          },          \"num\": 2       },             {          \"id\": 18,          \"userId\": 10195,          \"metro\":          {             \"id\": 7,             \"title\": \"Remote Control\",             \"html\": \"metro/RemoteControl.jsp\"          },          \"num\": 3       },             {          \"id\": 21,          \"userId\": 10195,          \"metro\":          {             \"id\": 2,             \"title\": \"Car Status\",             \"html\": \"metro/CarStatus.jsp\"          },          \"num\": 6       }    ] }";
//		JavaType jt = jc.getMapper().getTypeFactory().constructParametricType(List.class,HomeInfo.class);
//		JavaType jt1 = jc.getMapper().getTypeFactory().constructParametricType(SingleDataClientResponse.class, jt);
////				String json2 = "    [             {          \"id\": 15,          \"userId\": 10195,          \"metro\":          {             \"id\": 3,             \"title\": \"Massages\",             \"html\": \"metro/Massages.jsp\"          },          \"num\": 0       },             {          \"id\": 16,          \"userId\": 10195,          \"metro\":          {             \"id\": 5,             \"title\": \"Eco Driving\",             \"html\": \"metro/EcoDriving.jsp\"          },          \"num\": 1       },             {          \"id\": 17,          \"userId\": 10195,          \"metro\":          {             \"id\": 6,             \"title\": \"Route Planning\",             \"html\": \"metro/RoutePlanning.jsp\"          },          \"num\": 2       },             {          \"id\": 18,          \"userId\": 10195,          \"metro\":          {             \"id\": 7,             \"title\": \"Remote Control\",             \"html\": \"metro/RemoteControl.jsp\"          },          \"num\": 3       },             {          \"id\": 21,          \"userId\": 10195,          \"metro\":          {             \"id\": 2,             \"title\": \"Car Status\",             \"html\": \"metro/CarStatus.jsp\"          },          \"num\": 6       }    ]";
//		
//		SingleDataClientResponse<List<HomeInfo>> response = jc.fromJson(json, jt1);
//		
//		List<HomeInfo> homeList = response.getSingleData();
//		System.out.println("aaaaaaaaa " + homeList.size());
//		for(int i= 0;i<homeList.size();i++){
//			HomeInfo info = homeList.get(i);
//			System.out.println("bbbb " + info.getId());
//		}

	}
	
//	private static void test1(){
//		SDSmartPoiPrefRequest request = new SDSmartPoiPrefRequest();
//		I6RequestHeader requestHeader = new I6RequestHeader();
//		requestHeader.setUserId(new Long(1));
//		requestHeader.setVin("WAR1234567891");
//		request.setRequestHeader(requestHeader);
//		request.setUserId(new Long(1));
//		JsonConverter jc = new JsonConverter();
//		String ret = jc.toJson(request);
//		System.out.println(ret);
//	}
//	
//	private static void test2(){
//		List<RemoteControlHistory> romoteCotrolHistoryList = null;
//		SingleDataClientResponse<List<RemoteControlHistory>> response = DefaultResponseHandler.generateSingleDataClientResponse(romoteCotrolHistoryList);
//		JsonConverter jc = new JsonConverter();
//		String ret = jc.toJson(response);
//		System.out.println(ret);
//	}	
	
	
	
}
