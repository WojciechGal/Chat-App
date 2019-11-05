package pl.wojciech.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatController {

    private ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat(HttpServletRequest request) {
        request.setAttribute("chat", service.readMessages());
        return "chat";
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String chat(HttpSession sess, HttpServletRequest request) {
        String name = (String) sess.getAttribute("nick");
        String message = request.getParameter("message");
        service.addMessage(name + ": " + message);
        return "redirect:/chat";
    }
}
