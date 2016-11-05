package com.e2open.datahub.servlet;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(displayName = "loginFormServlet", name = "loginFormServlet", urlPatterns = "/form")
public class LoginFormServlet extends HttpServlet {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus (200);
        resp.getWriter ().write ("1");
        resp.getWriter ().flush ();
        resp.getWriter ().close ();
    }
}
