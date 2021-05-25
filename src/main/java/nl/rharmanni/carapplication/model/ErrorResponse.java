package nl.rharmanni.carapplication.model;

public class ErrorResponse {

    private String message;
    private String property;

    public ErrorResponse(String message, String property) {
        this.message = message;
        this.property = property;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
