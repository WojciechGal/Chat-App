package pl.wojciech.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import pl.wojciech.message.Message;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatService {

    private int counter = 0;

    private ChatRepository repository;

    public ChatService(ChatRepository repository) {
        this.repository = repository;
    }

    public List<Message> chatInitialization() {
        List<Message> firstList = repository.readChat();
        counter = firstList.size();
        return firstList;
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
        List<Message> chat = repository.readChat();

        List<Message> tempChat = new ArrayList<>();

        Iterator<Message> mainIt = chat.iterator();

        int innerCounter = 0;

        while (mainIt.hasNext()) {
            innerCounter++;

            Message mess = mainIt.next();
            if (innerCounter > counter) {
                tempChat.add(mess);
            }

        }

        counter = innerCounter;

        return tempChat;
    }


}
