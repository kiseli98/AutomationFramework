package support.rest_api;

import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface BasicRestClient {

	Response get(String api);

	Response getWithHeaders(String api,  HashMap<String, Object> headers);

	Response getWithParams(String api, HashMap<String, Object> parametersMap);

	Response put(String api, String json);

	Response post(String api, String json);

	Response postWithParams(String api, String json,
									   HashMap<String, Object> parametersMap);

	Response postNoBody(String api);

	Response delete(String api);

	Response patch(String api, String jsonInput);
}
