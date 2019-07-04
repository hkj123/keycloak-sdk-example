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


import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.authorization.*;
import org.springframework.stereotype.Controller;

import java.util.*;


/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@Controller
public class KeycloakAuthorization {

    public static void main(String[] args) {
        putPermissionTicket();
    }
    public static String putPermissionTicket() {
        String userName = "alice";
        String password = "password";
        String resourceId = "888c85b8-5a66-4120-8104-d33d8b3d6536";
        PermissionTicketRepresentation permissionTicketRepresentation = new PermissionTicketRepresentation();
        permissionTicketRepresentation.setId("222c85b8-5a66-4120-8104-d33d8b3d6537");
        permissionTicketRepresentation.setResource(resourceId);
        permissionTicketRepresentation.setGranted(true);
        AuthzClient.create().protection(userName,password).permission().update(permissionTicketRepresentation);
        return "success";
    }

    public static String getPermissionTicket() {
        String userName = "alice";
        String password = "password";
        String resourceId = "888c85b8-5a66-4120-8104-d33d8b3d6536";
        List<PermissionTicketRepresentation> permissionTicketRepresentations= AuthzClient.create().protection(userName,password).permission().find(resourceId,null,null,null,null,null,null,null);
        System.out.println("****" + permissionTicketRepresentations.get(0).getId());
        return "1111";
    }
    public static String permission() {
        String userName = "alice";
        String password = "password";
        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setResourceId("888c85b8-5a66-4120-8104-d33d8b3d6536");
        Set<String> scopes = new HashSet<String>();
        scopes.add("payment");
        permissionRequest.setScopes(scopes);
        PermissionResponse permissionResponse = AuthzClient.create().protection(userName,password).permission().create(permissionRequest);
        System.out.println("****" + permissionResponse.getTicket());
        return "1111";
    }


}
