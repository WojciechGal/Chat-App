package pl.wojciech.close;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import pl.wojciech.json.JsonResponse;

import javax.servlet.http.HttpSession;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CloseController {

    @RequestMapping(value = "/close", method = RequestMethod.GET, produces = "application/json")
    public JsonResponse closeSession(HttpSession session) {

        session.invalidate();

        return new JsonResponse(200, "Session has been closed.");
    }
}
