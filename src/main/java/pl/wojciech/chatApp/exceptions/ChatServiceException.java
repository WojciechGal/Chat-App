package pl.wojciech.chatApp.exceptions;

public class ChatServiceException extends RuntimeException {

    public ChatServiceException(String message) {
        super(message);
    }
}
