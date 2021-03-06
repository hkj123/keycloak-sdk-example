/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.keycloak.quickstart.springboot.web;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.ClientTemplateRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@Controller
public class KeycloakClients {

    public static void main(String[] args) {
        // get token
        Keycloak keycloak = token();
        addClientPermissionsResource(keycloak);
    }
    public static Keycloak token() {
        String url = "http://localhost:8080/auth/";
        String realm = "spring-boot-quickstart";
        String userName = "alice";
        String password = "password";
        String clientId = "app-authz-springboot";
        String clientSecret = "46cf15ff-48ca-429c-926d-0707fdf62cb0";
        Keycloak keycloak = Keycloak.getInstance(url, realm, userName, password, clientId, clientSecret);
        return keycloak;
    }
    public static void addClientPermissionsResource(Keycloak keycloak) {
        ResourcePermissionRepresentation resourcePermissionRepresentation = new ResourcePermissionRepresentation();
        resourcePermissionRepresentation.setResourceType("resource");
        resourcePermissionRepresentation.setName("111111");
        resourcePermissionRepresentation.setLogic(Logic.POSITIVE);
        resourcePermissionRepresentation.setDecisionStrategy(DecisionStrategy.UNANIMOUS);
        Set<String> policies = new HashSet<String>();
        policies.add("61c32e04-4512-497d-b6a6-8bf3d9b9b10d");
        Set<String> resources = new HashSet<String>();
        resources.add("188c333e-8ca5-4b78-a74a-f2b82b49bb43");
        Response response = keycloak.realm("spring-boot-quickstart").clients()
                .get("a79df12b-6aa1-46de-9d28-65813475cfd3").authorization().permissions().resource().create(resourcePermissionRepresentation);
        System.out.println("****" + response.getStatus());
    }
    public static void addClientPolicies(Keycloak keycloak) {
        UserPolicyRepresentation userPolicyRepresentation = new UserPolicyRepresentation();
        Set<String> users = new HashSet<String>();
        users.add("e8ff3e07-d366-4cfe-8b4f-4e6d89fbaa4e");
        userPolicyRepresentation.setName("test");
        userPolicyRepresentation.setType("user");
        userPolicyRepresentation.setLogic(Logic.POSITIVE);
        userPolicyRepresentation.setDecisionStrategy(DecisionStrategy.UNANIMOUS);
        Response response = keycloak.realm("spring-boot-quickstart").clients()
                .get("a79df12b-6aa1-46de-9d28-65813475cfd3").authorization().policies().user().create(userPolicyRepresentation);
        System.out.println("****" + response.getStatus());
    }
    public static void addClientAuthorizationScope(Keycloak keycloak) {
        ScopeRepresentation scopeRepresentation = new ScopeRepresentation();
        scopeRepresentation.setName("test");
        scopeRepresentation.setDisplayName("test");
        scopeRepresentation.setIconUri("localhost:8080/");
        Response response = keycloak.realm("spring-boot-quickstart").clients()
                .get("a79df12b-6aa1-46de-9d28-65813475cfd3").authorization().scopes().create(scopeRepresentation);
        System.out.println("****" + response.getStatus());
    }

    public static void addClientResource(Keycloak keycloak) {
        ResourceRepresentation resourceRepresentation = new ResourceRepresentation();
        resourceRepresentation.setName("branch");
        resourceRepresentation.setType("http://qloudpep.shdpoc.service.sd/v1/qloud/sandbox/branch");
        resourceRepresentation.setUri("/v1/qloud/sandbox/branch/");
        resourceRepresentation.setOwner("alice");
        resourceRepresentation.setOwnerManagedAccess(true);
        Response response = keycloak.realm("spring-boot-quickstart").clients()
                .get("a79df12b-6aa1-46de-9d28-65813475cfd3").authorization().resources().create(resourceRepresentation);
        System.out.println("****" + response.getStatus());
    }
    public static void addAuthorizationScope(Keycloak keycloak) {
        ResourceRepresentation resourceRepresentation = new ResourceRepresentation();
        resourceRepresentation.setName("branch");
        resourceRepresentation.setType("http://qloudpep.shdpoc.service.sd/v1/qloud/sandbox/branch");
        resourceRepresentation.setUri("/v1/qloud/sandbox/branch/");
        resourceRepresentation.setOwner("alice");
        resourceRepresentation.setOwnerManagedAccess(true);
        Response response = keycloak.realm("spring-boot-quickstart").clients()
                .get("a79df12b-6aa1-46de-9d28-65813475cfd3").authorization().resources().create(resourceRepresentation);
        System.out.println("****" + response.getStatus());
    }

    public static void get(Keycloak keycloak) {
        ClientRepresentation clientRepresentation = keycloak.realm("spring-boot-quickstart").clients()
                .get("a79df12b-6aa1-46de-9d28-65813475cfd3").toRepresentation();
        System.out.println("****" + clientRepresentation.getClientId());
    }

    public static void search(Keycloak keycloak) {
        //find user
        List<UserRepresentation> userRepresentationList = keycloak.realm("spring-boot-quickstart").users().search("alice");
        System.out.println("****" + userRepresentationList.get(0).getUsername());
    }
}
