package pl.wojciech.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class welcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage() {
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String welcomePage(HttpServletRequest request, HttpSession sess){
        String name = request.getParameter("nick");
        sess.setAttribute("nick", name);
        return "chatVer2";
    }
}
