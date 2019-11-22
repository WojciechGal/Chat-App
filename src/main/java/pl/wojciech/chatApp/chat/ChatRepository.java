package pl.wojciech.chatApp.chat;

import org.springframework.stereotype.Repository;
import pl.wojciech.chatApp.message.Message;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatRepository {

    private final List<Message> chat = new ArrayList<>();

    public List<Message> readChat() {
        return this.chat;
    }

    public void addMessage(Message mess) {
        chat.add(mess);
    }
}
