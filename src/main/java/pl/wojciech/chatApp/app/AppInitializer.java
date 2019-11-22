package pl.wojciech.chatApp.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import pl.wojciech.chatApp.config.AppConfig;

import javax.servlet.*;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(AppConfig.class);

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);

        servlet.addMapping("/");

        FilterRegistration.Dynamic filter =
                servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());

        filter.setInitParameter("encoding", "UTF-8");

        filter.setInitParameter("forceEncoding", "true");

        filter.addMappingForUrlPatterns(null, true, "/*");
    }
}
