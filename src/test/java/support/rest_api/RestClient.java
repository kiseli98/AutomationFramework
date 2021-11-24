package support.rest_api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;

public class RestClient implements BasicRestClient {

	private Response response;
	private HashMap<String, Object> headers;
	private HashMap<String, Object> cookies;

	public RestClient(String baseUri) {
		RestAssured.baseURI = baseUri;
	}

	public void setHeaders(HashMap<String, Object> headers) {
		this.headers = headers;
	}

	public void addHeaders(HashMap<String, Object> headers) {
		this.headers.putAll(headers);
	}

	public void setBasicHeaders() {
//		TODO
	}

	@Override
	public Response get(String path) {
		response = given()
			.headers(headers)
			.request()
			.get(path);
		return response;
	}

	@Override
	public Response getWithHeaders(String path, HashMap<String, Object> headers) {
		response = given()
			.headers(headers)
			.request()
			.get(path);
		return response;
	}

	@Override
	public Response getWithParams(String path, HashMap<String, Object> params) {
		response = given()
			.headers(headers)
			.params(params)
			.request()
			.get(path);
		return response;
	}

	@Override
	public Response put(String path, String json) {
		response = given()
			.headers(headers)
			.contentType(ContentType.JSON).body(json)
			.when().put(path);
		return response;
	}

	@Override
	public Response post(String path, String json) {
		response = given()
			.headers(headers)
			.contentType(ContentType.JSON).body(json)
			.when().post(path);
		return response;
	}

	@Override
	public Response postWithParams(String path, String json, HashMap<String, Object> params) {
		response = given()
			.headers(headers)
			.contentType(ContentType.JSON).body(json)
			.params(params)
			.when().post(path);
		return response;
	}

	@Override
	public Response postNoBody(String path) {
		response = given()
			.headers(headers)
			.when().post(path);
		return response;
	}

	@Override
	public Response delete(String path) {
		response = given()
			.headers(headers)
			.when().delete(path);
		return response;
	}

	@Override
	public Response patch(String path, String json) {
		response = given()
			.headers(headers)
			.contentType(ContentType.JSON).body(json)
			.when().patch(path);
		return response;
	}
}
