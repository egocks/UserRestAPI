package at.mritter.dezsys09;

public class ResponseMessage {

    private int status;
    private String message;

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
