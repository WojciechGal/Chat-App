package pl.wojciech.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
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
