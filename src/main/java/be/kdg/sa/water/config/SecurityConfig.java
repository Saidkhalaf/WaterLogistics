package be.kdg.sa.water.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests(requsests -> requsests.requestMatchers("unsecured").permitAll()// Als ik binnen komt, dan ga ik alle request authorizzeren
                .anyRequest().authenticated()) //Alle andere request moeten geauthenticeerd zijn
                .sessionManagement(mgmt -> mgmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //We maak geen sessie aan
//                .oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults())).build(); //We accepteer de jwt token en we gaan de default configuratie gebruiken van application.yml
                .oauth2ResourceServer(rs -> rs.jwt(jwt -> jwtCustomConverter())).build();
    }

    @Bean
    JwtAuthenticationConverter jwtCustomConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return converter;

    }
}
