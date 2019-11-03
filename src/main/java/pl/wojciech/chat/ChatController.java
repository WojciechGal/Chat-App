package pl.wojciech.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

    private ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat(HttpServletRequest request) {
        request.setAttribute("chat", service.readMessages());
        request.setAttribute("message", null);
        return "chat";
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String chat(HttpSession sess, HttpServletRequest request) {
        String name = (String) sess.getAttribute("nick");
        String message = (String) request.getAttribute("message");
        service.addMessage(name + ": " + message);
        return "redirect:/chat";
    }
}
