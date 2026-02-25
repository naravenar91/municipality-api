package cl.aravena.infrastructure.adapter.in.security;

import cl.aravena.infrastructure.adapter.in.exception.ApiError;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.vertx.http.runtime.security.ChallengeData;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.quarkus.vertx.http.runtime.security.HttpCredentialTransport;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import io.quarkus.smallrye.jwt.runtime.auth.JWTAuthMechanism;

import java.util.Set;

@Alternative
@Priority(1)
@ApplicationScoped
public class CustomAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    JWTAuthMechanism delegate; // Inyectamos específicamente el de JWT, no la interfaz genérica

    @Override
    public Uni<SecurityIdentity> authenticate(RoutingContext context, IdentityProviderManager identityProviderManager) {
        return delegate.authenticate(context, identityProviderManager);
    }

    @Override
    public Uni<ChallengeData> getChallenge(RoutingContext context) {
        // Personalizamos la respuesta JSON para el 401
        context.response()
                .setStatusCode(401)
                .putHeader("Content-Type", "application/json")
                .end(Json.encode(new ApiError("AUTH_001", "Invalid or expired token")));

        return Uni.createFrom().nullItem();
    }

    @Override
    public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
        return delegate.getCredentialTypes();
    }

    @Override
    public Uni<HttpCredentialTransport> getCredentialTransport(RoutingContext context) {
        return delegate.getCredentialTransport(context);
    }
}