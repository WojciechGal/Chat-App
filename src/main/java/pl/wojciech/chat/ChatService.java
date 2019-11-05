package pl.wojciech.chat;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private ChatRepository repository;

    public ChatService(ChatRepository repository) {
        this.repository = repository;
    }

    public void addMessage(String message) {
        repository.addMessage(message);
    }

    public List<String> readMessages() {
        List<String> messages = repository.readMessages();
        if (messages.size() > 15) {
            messages.remove(0);
        }
        return messages;
    }


}