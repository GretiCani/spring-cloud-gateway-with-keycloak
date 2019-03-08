package sk.bsmk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class GatewayController {

  @Value("${remote.home}")
  private URI home;

  @GetMapping("/secured")
  public Mono<ResponseEntity<byte[]>> secured(
    @AuthenticationPrincipal Jwt jwt,
    ProxyExchange<byte[]> proxy) {
    return proxy.uri(home.toString() + "/auth/realms/master").get();
  }

  @GetMapping("/unsecured")
  public Mono<ResponseEntity<byte[]>> unsecured(ProxyExchange<byte[]> proxy) {
    return proxy.uri(home.toString() + "/auth/realms/master").get();
  }

}
