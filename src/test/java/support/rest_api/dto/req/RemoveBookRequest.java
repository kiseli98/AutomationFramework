package support.rest_api.dto.req;

public class RemoveBookRequest {
    public String isbn;
    public String userId;

    public RemoveBookRequest(String userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }
}