package support.rest_api;

public class EfolioClient extends RestClient{

	private final String AUTHORIZATION = "/login";

	public EfolioClient(String baseUri) {
		super(baseUri);
	}

//	public String authenticate(String username, String password){
////		String token = post(AUTHORIZATION, username+password).getBody();
////		add
//	}
}
