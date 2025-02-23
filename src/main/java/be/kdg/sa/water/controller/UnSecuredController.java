package be.kdg.sa.water.controller;

import be.kdg.sa.water.messages.UnSecuredMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecured")
public class UnSecuredController {

    @GetMapping
    public UnSecuredMessage getUnSecuredMessage() {
        return new UnSecuredMessage();
    }
}
