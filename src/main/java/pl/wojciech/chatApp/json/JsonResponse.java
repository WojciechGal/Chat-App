package pl.wojciech.json;

public class JsonResponse {
    private int status;
    private String message;

    public JsonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
