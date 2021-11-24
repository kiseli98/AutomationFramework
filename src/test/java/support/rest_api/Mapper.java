package support.rest_api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

@UtilityClass
@Log4j
public class Mapper {

	ObjectMapper objectMapper = new ObjectMapper();

	public <T> T convertJsonToPlainJavaObject(Response response, Class<T> className) {
		return convertJsonToPlainJavaObject(response.getBody().asString(), className);
	}

	public <T> T convertJsonToPlainJavaObject(String response, Class<T> className) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T object = null;
		try {
			object = objectMapper.readValue(response.trim(), className);
		} catch (IOException e) {
			log.error(e);
		}
		return object;
	}

	public String convertPlainJavaObjectToJson(Object plainJavaObject) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String json = "";
		try {
			json = objectMapper.writeValueAsString(plainJavaObject);
		} catch (IOException e) {
			log.error(e);
		}
		return json;
	}

	public <T> List<T> convertJsonToPlainJavaList(Response response, Class<T> className) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<T> objList = null;
		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = typeFactory.constructCollectionType(List.class, className);
		try {
			final String body = response.getBody().asString();
			objList = objectMapper.readValue(body, collectionType);
		} catch (IOException e) {
			log.error(e);
		}
		return objList;
	}
}

