package pl.wojciech.exceptions;

public class ChatServiceException extends RuntimeException {

    public ChatServiceException(String message) {
        super(message);
    }
}
