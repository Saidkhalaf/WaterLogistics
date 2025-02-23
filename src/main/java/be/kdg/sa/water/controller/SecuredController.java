package be.kdg.sa.water.controller;

import be.kdg.sa.water.messages.SecuredMessage;
import com.nimbusds.jose.proc.SecurityContext;
import com.rabbitmq.client.AMQP;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

    @GetMapping
    @PreAuthorize("hasAuthority('user')")
    public SecuredMessage getSecuredMessage() {
        return SecuredMessage.builder()
                .message("Hello secured Endpoint")
                .build();
    }

//    @GetMapping("/principal")
//    public SecuredMessage getSecuredMessageUsingPrincipal() {
//        Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(token);
//
//        return SecuredMessage.builder()
//                .message("Hello secured Endpoint")
//                .build();
//    }

    @GetMapping("/principal")
    public SecuredMessage getSecuredMessageUsingPrincipal() {
        Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(token);

        return getInfoFromToken(token, "Via Principal");
    }

    @GetMapping("/annotation")
    public SecuredMessage getSecuredMessageUsingAnnotation(@AuthenticationPrincipal Jwt token) {
        return getInfoFromToken(token, "Via Principal");
    }



    public static SecuredMessage getInfoFromToken(Jwt token, String message) {
        return SecuredMessage.builder()
                .subjectId(token.getClaimAsString("sub"))
                .email(token.getClaimAsString("email"))
                .firstName(token.getClaimAsString("given_name"))
                .lastName(token.getClaimAsString("family_name"))
                .message(message)
                .build();
    }



}
