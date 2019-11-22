package pl.wojciech.chatApp.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.wojciech.chatApp.message.Message;

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
        String stringTime = ((Integer)time.getHour()).toString() + ":";
        if (time.getMinute() < 10) {
            stringTime += "0" + ((Integer)time.getMinute()).toString();
        } else {
            stringTime += ((Integer)time.getMinute()).toString();
        }
        Message totalMessage = new Message(nick, message, stringTime);
        repository.addMessage(totalMessage);
    }

    public List<Message> readMessages() {
        return repository.readChat();
    }

}
