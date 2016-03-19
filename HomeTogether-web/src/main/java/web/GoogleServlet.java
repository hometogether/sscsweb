/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.gson.Gson;
import ejb.GestoreUtenti;
import ejb.Profilo;
import ejb.ProfiloFacade;
import ejb.UtenteGoogle;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Constants;

/**
 *
 * @author Andrea22
 */
@WebServlet(name = "GoogleServlet", urlPatterns = {"/GoogleServlet"})
public class GoogleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private GestoreUtenti gestoreUtenti;
    @EJB
    private ProfiloFacade profiloFacade;

    // private Sessione sessione = new Sessione();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, GeneralSecurityException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if (action.equals("loginGoogle")) {
                //HttpTransport transport = new HttpTransport();
                JsonFactory jsonFactory = new JacksonFactory();
                NetHttpTransport transport = new NetHttpTransport();

                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                        .setAudience(Arrays.asList(Constants.ID_GOOGLE))
                        .build();

                // (Receive idTokenString by HTTPS POST)
                String idTokenString = request.getParameter("token");
                System.out.println("idTokenString:" + idTokenString);
                //  System.out.println("verifier is:" + verifier.toString());
                GoogleIdToken idToken = verifier.verify(idTokenString);

                if (idToken != null) {
                    Payload payload = idToken.getPayload();
                    String id = request.getParameter("id");
                    if (id.equals(payload.getUserId())) {
                        System.out.println("id utente e id del token uguali");
                        String email = request.getParameter("email");
                        String idgoogle = request.getParameter("idgoogle");
                        UtenteGoogle u = gestoreUtenti.loginGoogle(email, idgoogle);
                        //Profilo p = profiloFacade.getProfilo(email);
                        if (u != null) {
                            
                            out.println("1");

                        } else {
                            System.out.println("non sono loggato!");
                            out.println("0");
                        }
                    } else {
                        System.out.println("id utente e id del token non uguali");
                        out.println("-1");
                    }

                } else {
                    System.out.println("Invalid ID token.");
                    out.println("-1");
                }
            } else {
                System.out.println("Action OTHER");
            }

        }
    }

    private String buildGson(List<UtenteGoogle> u) {

        Gson gson = new Gson();
        String json = gson.toJson(u);

        if (json == null) {
            System.out.println("servlet buildGson: NULL");
        } else {
            System.out.println("servlet buildGson: NOT NULL  " + json);
        }
        return json;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(GoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(GoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
