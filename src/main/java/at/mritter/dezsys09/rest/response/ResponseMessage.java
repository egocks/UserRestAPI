package at.mritter.dezsys09.rest.response;

public class ResponseMessage {

    private int status;
    private String message;

    private ResponseMessage() {

    }

    public ResponseMessage(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
