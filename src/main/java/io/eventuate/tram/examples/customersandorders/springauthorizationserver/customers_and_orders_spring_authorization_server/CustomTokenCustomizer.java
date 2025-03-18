package io.eventuate.tram.examples.customersandorders.springauthorizationserver.customers_and_orders_spring_authorization_server;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        Authentication principal = context.getPrincipal();
        JwtClaimsSet.Builder claims = context.getClaims();

        // Assuming the principal is a UserDetails object
        if (principal.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal.getPrincipal();
            claims.claim("name", userDetails.getUsername());
            /// claims.claim("email", userDetails.getEmail());
        }
    }
}