package support.rest_api.dto.req;

public class AuthRequest {

    public String userName;
    public String password;

    public AuthRequest(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

}