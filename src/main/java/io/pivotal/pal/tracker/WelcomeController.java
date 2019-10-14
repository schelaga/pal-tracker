package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    String greetingMsg;

    @Autowired
    public WelcomeController(@Value("${WELCOME_MESSAGE}") String welcomeMsg){
        greetingMsg=welcomeMsg;
    }

    @GetMapping("/")
    public String sayHello() {
        return greetingMsg;
    }
}
