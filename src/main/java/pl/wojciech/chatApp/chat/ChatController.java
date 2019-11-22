package pl.wojciech.chatApp.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import pl.wojciech.chatApp.exceptions.ChatServiceException;
import pl.wojciech.chatApp.json.JsonResponse;
import pl.wojciech.chatApp.message.Message;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatController {

    private ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET, produces = "application/json")
    public List<Message> chat() {
        return service.readMessages();
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST, produces = "application/json")
    public JsonResponse chat(@RequestBody String message, HttpSession sess) {
        String nick = (String) sess.getAttribute("nick");

        try {
            service.addMessage(nick ,message);
            return new JsonResponse(200, "Message successfully added.");
        }catch (ChatServiceException e) {
            return new JsonResponse(500, "Cannot add message.");
        }
    }

}
