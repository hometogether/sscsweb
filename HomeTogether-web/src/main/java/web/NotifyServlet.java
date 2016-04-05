/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.GestoreRichieste;
import ejb.Profilo;
import ejb.ProfiloFacade;
import ejb.Richiesta;
import ejb.RichiestaFacade;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            throws ServletException, IOException, ParseException {
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
                
                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
                java.util.Date parsed1 = format.parse(request.getParameter("data_inizio"));
                java.util.Date parsed2 = format.parse(request.getParameter("data_fine"));
                Date data_inizio = new Date(parsed1.getTime());
                Date data_fine = new Date(parsed2.getTime());
                
                
                System.out.println("data inizio:"+request.getParameter("data_inizio"));
                System.out.println("data fine:"+request.getParameter("data_fine"));

                gestoreRichieste.aggiungiRichiesta(personalProfile, destProfile, data_inizio, data_fine);

                
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(NotifyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(NotifyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
