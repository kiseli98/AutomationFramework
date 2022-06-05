package support.rest_api.dto.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    @JsonProperty("token")
    public String tokenString;
    public String expires;
    public String status;
    public String result;
}
