package org.keycloak.quickstart.springboot.web;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakToken {
    public static void main(String[] args) {
        //password 方式
//        Keycloak keycloak = passwordToken();
        //client_credentials 方式
        Keycloak keycloak = passwordToken();
        System.out.println("**********" + keycloak.tokenManager().getAccessTokenString());
    }
    public static Keycloak passwordToken() {
        String url = "http://localhost:8080/auth/";
        String realm = "spring-boot-quickstart";
        String userName = "alice";
        String password = "password";
        String clientId = "app-authz-springboot";
        String clientSecret = "46cf15ff-48ca-429c-926d-0707fdf62cb0";
        Keycloak keycloak = Keycloak.getInstance(url, realm, userName, password, clientId, clientSecret);
        return keycloak;
    }
    public static Keycloak credentialsToken() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/auth/")
                .realm("spring-boot-quickstart")
                .clientId("app-authz-springboot")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientSecret("46cf15ff-48ca-429c-926d-0707fdf62cb0")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(20).build())
                .build();
        return keycloak;
    }
}