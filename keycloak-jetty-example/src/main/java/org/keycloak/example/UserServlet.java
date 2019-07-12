package org.keycloak.example;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.example.utils.CommonKeys;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        Keycloak keycloak = this.passwordToken();
        System.out.println(keycloak);
        response.getWriter().println("<h1>Hello from HelloServlet"+keycloak+"</h1>");
    }

    public Keycloak passwordToken() {
        Keycloak keycloak = Keycloak.getInstance(CommonKeys.KEYCLOAK_AUTH_SERVER_URL, CommonKeys.KEYCLOAK_REALM, CommonKeys.USER_NAME,
                CommonKeys.PASSWORD, CommonKeys.CLIENT_ID, CommonKeys.CLIENT_SECRET);
        return keycloak;
    }
}
