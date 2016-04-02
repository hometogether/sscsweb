/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.GestoreRichieste;
import ejb.GestoreUtenti;
import ejb.Profilo;
import ejb.ProfiloFacade;
import ejb.Richiesta;
import ejb.RichiestaFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Antonio
 */
@WebServlet(name = "NotifyServlet", urlPatterns = {"/NotifyServlet"})
@MultipartConfig

public class NotifyServlet extends HttpServlet {

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
    private GestoreRichieste gestoreRichieste;
    @EJB
    private ProfiloFacade profiloFacade;
    @EJB
    private RichiestaFacade richiestaFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action == null) {
            Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
            request.setAttribute("profilo", personalProfile);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } else if (action.equals("aggiungirichiesta")) {
            if (request.getParameter("idDest") != null && !request.getParameter("idDest").equals("")) {
                Long idDest = new Long(request.getParameter("idDest"));
                Long id = (Long) (session.getAttribute("id"));
                Profilo personalProfile = profiloFacade.getProfilo(id);
                Profilo destProfile = profiloFacade.getProfilo(idDest);
                gestoreRichieste.aggiungiRichiesta(personalProfile, destProfile);

                request.setAttribute("profilo", personalProfile);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            } else {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                request.setAttribute("danger", "Informazione destinatario mancante!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            }

        } else if (action.equals("accettarichiesta")) {
            Long id = (Long) (session.getAttribute("id"));
            Profilo personalProfile = profiloFacade.getProfilo(id);
            Long idRichiesta = new Long(request.getParameter("idRichiesta"));
            Richiesta richiesta = richiestaFacade.getRichiesta(idRichiesta);

            gestoreRichieste.accettaRichiesta(personalProfile, richiesta);
            personalProfile = profiloFacade.getProfilo(id);
            request.setAttribute("profilo", personalProfile);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);

        } else if (action.equals("rifiutarichiesta")) {
            Long id = (Long) (session.getAttribute("id"));
            Profilo personalProfile = profiloFacade.getProfilo(id);
            Long idRichiesta = new Long(request.getParameter("idRichiesta"));
            Richiesta richiesta = richiestaFacade.getRichiesta(idRichiesta);

            gestoreRichieste.rifiutaRichiesta(personalProfile, richiesta);

            request.setAttribute("profilo", personalProfile);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);

        } else {
            Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
            request.setAttribute("profilo", personalProfile);
            request.setAttribute("danger", "azione sconosciuta!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);

        }

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
