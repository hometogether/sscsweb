/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Profilo;
import ejb.ProfiloFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Antonio
 */
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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            if (action.equals("goProfile")) {
                Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                request.setAttribute("profilo", p);
                //System.out.println("foto:" + p.getFoto_profilo());
                //request.setAttribute("interessi", p.getInteressi());
                
                
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                rd.forward(request, response);
            } else if (action.equals("goUserProfile")) {

                Long idprofile = new Long(request.getParameter("idprofile"));
                Profilo p = profiloFacade.getProfilo(idprofile);
                if (p != null) {

                    Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    List<Profilo> listafollowing = personalProfile.getFollowing();
                    boolean trovato = false;
                    System.out.println("id dell'amico:"+idprofile);
                    for (int i = 0; i < listafollowing.size() && trovato == false; i++) {
                        System.out.println("id:"+i+": "+listafollowing.get(i).getId());
                        if (listafollowing.get(i).getId().equals(idprofile)) {
                            System.out.println("trovato!");
                            trovato = true;
                        }
                    }
                    if (trovato) {
                        System.out.println("trovato! è un amico");
                        request.setAttribute("amici", 1);

                    } else {
                        System.out.println("non è un amico...");

                        request.setAttribute("amici", 0);

                    }

                    request.setAttribute("profilo", p);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                    rd.forward(request, response);
                } else {
                    //GESTIRE ERRORE
                }
            } else if (action.equals("loginSocial")) {

                if (session != null) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);

                } else {
                    //errore
                }
            }else if (action.equals("logOut")) {
                session.invalidate();
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            } else {
                //gestione erroi
            }
            /* TODO output your page here. You may use following sample code. */

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
