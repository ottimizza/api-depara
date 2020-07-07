package br.com.ottimizza.depara.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${oauth.service.name}", url = "${oauth.service.url}")
public interface AccountFeignClient { // @formatter:off


	
    @GetMapping("/api/v1/organizations")
    public ResponseEntity<String> getOrganizationByCNPJ(@RequestHeader("Authorization") String authorization, @RequestParam String cnpj);

    @GetMapping("/oauth/userinfo")
    public ResponseEntity<String> getUserInfo(@RequestHeader("Authorization") String authorization);

    @GetMapping("/oauth/tokeninfo")
    public ResponseEntity<String> getTokenInfo(@RequestHeader("Authorization") String authorization);

}
