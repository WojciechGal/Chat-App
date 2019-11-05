package pl.wojciech.chat;

import org.springframework.stereotype.Repository;
import pl.wojciech.message.Message;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatRepository {

    private final List<Message> chat = new ArrayList<>();

    public List<Message> readChat() {
        return this.chat;
    }
}
