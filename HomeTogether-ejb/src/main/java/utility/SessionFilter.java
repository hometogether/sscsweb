/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "SessionFilter")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Dentro il filtrooooo!");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String path = ((HttpServletRequest) request).getRequestURI();
        String homeURL = "/HomeTogether-web/";
        String facebookURL = "/HomeTogether-web/FacebookServlet";
        String googleURL = "/HomeTogether-web/GoogleServlet";
        String loginURL = "/HomeTogether-web/LoginServlet";
        String registrationURL = "/HomeTogether-web/RegistrationServlet";
        System.out.println("url:"+loginURL);
        System.out.println("req:"+request.getRequestURI());
        if (request.getRequestURI().equals(loginURL) || request.getRequestURI().equals(googleURL)
                ||request.getRequestURI().equals(facebookURL) || request.getRequestURI().equals(homeURL)){
            System.out.println("devo loggarmi!");
            chain.doFilter(req, res);
            
            //chain.doFilter(req, res);
            //response.sendRedirect(request.getContextPath() + "/index.jsp"); // No logged-in user found, so redirect to login page.
        } else if (request.getRequestURI().equals(registrationURL) ){
            System.out.println("non sono loggato, ma sto effettuando un'operazione di registrazione!");
            chain.doFilter(req, res);
        } else if (session == null || session.getAttribute("id") == null){
            System.out.println("non sono loggato!");
            response.sendRedirect(request.getContextPath()); 
        }else {
            System.out.println("sono loggato!");
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

}