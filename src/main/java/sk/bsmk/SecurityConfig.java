package sk.bsmk;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static sk.bsmk.GatewayController.SECURED;
import static sk.bsmk.GatewayController.UNSECURED;

@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
    http
      .authorizeExchange()
      .pathMatchers(SECURED).authenticated()
      .pathMatchers(UNSECURED).permitAll()
      .and()
      .oauth2ResourceServer()
      .jwt();
    return http.build();
  }

}
