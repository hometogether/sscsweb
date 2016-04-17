/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Profilo;
import ejb.ProfiloFacade;
import java.io.IOException;
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
@WebServlet(name = "RedirectServlet", urlPatterns = {"/RedirectServlet"})
public class RedirectServlet extends HttpServlet {

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
    private ProfiloFacade profiloFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
            request.setAttribute("profilo", personalProfile);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } else if (action.equals("goProfile")) {
            Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
            request.setAttribute("profilo", personalProfile);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
            rd.forward(request, response);

        } else if (action.equals("goUserProfile")) {
            if (request.getParameter("idprofile") != null && !request.getParameter("idprofile").equals("")) {

                Long idprofile = new Long(request.getParameter("idprofile"));
                Profilo p = profiloFacade.getProfilo(idprofile);
                if (p != null) {
                    if (!(p.getId() == idprofile)) {
                        Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                        List<Profilo> listafollowing = personalProfile.getFollowing();
                        boolean trovato = false;
                        for (int i = 0; i < listafollowing.size() && trovato == false; i++) {
                            if (listafollowing.get(i).getId().equals(idprofile)) {
                                trovato = true;
                            }
                        }
                        if (trovato) {
                            request.setAttribute("amici", 1);
                        } else {
                            request.setAttribute("amici", 0);
                        }
                    } else {
                        request.setAttribute("amici", 0);
                    }
                    request.setAttribute("profilo", p);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                    rd.forward(request, response);
                } else {
                    Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    request.setAttribute("profilo", personalProfile);
                    request.setAttribute("danger", "Profilo non esistente!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                }
            } else {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                request.setAttribute("warning", "Profilo non presente nell'URL!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            }
        } else if (action.equals("loginSocial")) {

            Profilo p = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
            request.setAttribute("profilo", p);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);

        } else if (action.equals("logOut")) {
            session.invalidate();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else if (action.equals("goHome")) {
            Profilo p = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
            request.setAttribute("profilo", p);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } else {
            Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
            request.setAttribute("profilo", personalProfile);
            request.setAttribute("danger", "azione sconosciuta!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
            rd.forward(request, response);

        }
        /* TODO output your page here. You may use following sample code. */

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
