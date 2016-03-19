/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import ejb.Profilo;
import ejb.ProfiloFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Antonio
 */
@WebServlet(name = "NavBarServlet", urlPatterns = {"/NavBarServlet"})
public class NavBarServlet extends HttpServlet {

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
    ProfiloFacade profiloFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        try (PrintWriter out = response.getWriter()) {
            if (action == null) {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            } else if (action.equals("searchUtente")) {
                
                if (request.getParameter("ric_utente") != null && !request.getParameter("ric_utente").equals("")) {
                    String nomeDigitato = (String) request.getParameter("ric_utente");
                    List<Profilo> res = profiloFacade.getProfiloUtente(nomeDigitato.toLowerCase(), (Long) (session.getAttribute("id")), 0);
                    if (res == null) {
                        request.setAttribute("warning", "Nessun Risultato Trovato!");
                    }
                    request.setAttribute("utente", res);
                    request.setAttribute("ric_utente", nomeDigitato);
                    Profilo p = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    request.setAttribute("profilo", p);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/utenti.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("utente", null);
                    request.setAttribute("warning", "Nessun Risultato Trovato!");
                    Profilo p = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    request.setAttribute("profilo", p);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/utenti.jsp");
                    rd.forward(request, response);
                }

            } else if (action.equals("searchAjax")) {
                String nomeDigitato = (String) request.getParameter("ric_utente");
                int offset = Integer.parseInt(request.getParameter("offset"));
                List<Profilo> res = profiloFacade.getProfiloUtente(nomeDigitato.toLowerCase(), (Long) (session.getAttribute("id")), offset);
                out.println(buildGson(res));
            } else {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                request.setAttribute("danger", "azione sconosciuta!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);

            }
        }
    }

    private String buildGson(List<Profilo> u) {

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
