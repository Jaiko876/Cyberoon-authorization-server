package net.pet.auth_server.security.configuration.well_known;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("openid-configuration")
@Getter
@Setter
public class  OpenIdConfiguration {
    private String authorization_endpoint;
    private String end_session_endpoint;
    private List<String> grant_types_supported;
    private String issuer;
    private String token_endpoint;
    private String userinfo_endpoint;
    private String jwks_uri;
}
