package pl.wojciech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.wojciech.chat.ChatController;
import pl.wojciech.chat.ChatRepository;
import pl.wojciech.chat.ChatService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.wojciech")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver; }

    @Bean
    public ChatRepository repository() {
        return new ChatRepository();
    }

    @Bean
    public ChatService service() {
        return new ChatService(repository());
    }

}
