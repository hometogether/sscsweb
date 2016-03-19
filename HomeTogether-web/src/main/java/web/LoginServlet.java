/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import ejb.GestoreUtenti;
import ejb.Interesse;
import ejb.Profilo;
import ejb.ProfiloFacade;
import ejb.UtenteApp;
import ejb.UtenteGoogle;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrea22
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            System.out.println("action is:" + action);
            if (action == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            } else if (action.equals("login")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                UtenteApp u = gestoreUtenti.loginUtente(email, password);
                if (u != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("id", u.getProfilo().getId());

                    //Profilo p = profiloFacade.getProfilo(email);
                    session.setAttribute("nome", "" + u.getProfilo().getNome());
                    session.setAttribute("cognome", "" + u.getProfilo().getCognome());
                    session.setAttribute("email", "" + u.getProfilo().getEmail());
                    session.setAttribute("data", "" + u.getProfilo().getData_nascita());
                    session.setAttribute("sesso", "" + u.getProfilo().getSesso());
                    session.setAttribute("formazione", u.getProfilo().getFormazione());
                    session.setAttribute("occupazione", u.getProfilo().getOccupazione());
                    session.setAttribute("telefono", u.getProfilo().getTelefono());
                    //  s.setAttribute("location",""+location);
                    session.setAttribute("foto", "" + u.getProfilo().getFoto_profilo());
                    Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                    request.setAttribute("profilo", p);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);

                } else {
                    request.setAttribute("warning", "Login non valido!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }

            } else {
                request.setAttribute("danger", "azione sconosciuta!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
