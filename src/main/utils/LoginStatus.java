package utils;

public class LoginStatus {
    private final int statusCode;
    private final String message;
    
    public LoginStatus(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public String getMessage() {
        return message;
    }
}
