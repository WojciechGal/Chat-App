package pl.wojciech.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class welcomeController {

    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }
}
