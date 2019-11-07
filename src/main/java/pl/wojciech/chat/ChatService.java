package pl.wojciech.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.wojciech.message.Message;

import java.time.LocalTime;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatService {

    private ChatRepository repository;

    public ChatService(ChatRepository repository) {
        this.repository = repository;
    }

    public void addMessage(String nick, String message) {
        LocalTime time = LocalTime.now();
        Message totalMessage = new Message(nick, message, ((Integer)time.getHour()).toString() + ":" + ((Integer)time.getMinute()).toString());
        repository.readChat().add(totalMessage);
    }

    public List<Message> readMessages() {
        List<Message> chat = repository.readChat();
        return chat;
    }


}
