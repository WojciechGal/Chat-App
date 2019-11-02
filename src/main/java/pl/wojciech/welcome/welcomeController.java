package pl.wojciech.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class welcomeController {

    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }

    @PostMapping("/")
    public String welcomePage(HttpServletRequest request, HttpSession sess){
        String name = request.getParameter("nick");
        sess.setAttribute("nick", name);
        return "hi";
    }
}
