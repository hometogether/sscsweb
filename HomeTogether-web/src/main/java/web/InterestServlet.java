/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import ejb.GestoreInteressi;
import ejb.GestoreUtenti;
import ejb.UtenteApp;
import ejb.UtenteGoogle;
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
 * @author Andrea22
 */
@WebServlet(name = "IterestServlet", urlPatterns = {"/InterestServlet"})
public class InterestServlet extends HttpServlet {

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
    private GestoreInteressi gestoreInteressi;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            

            String action = request.getParameter("action");
            
            System.out.println("action is:"+action);
            if (action.equals("add")) {
                HttpSession session = request.getSession();
                System.out.println("entro in action add");
                Long idProfilo = (Long) session.getAttribute("id");

                String nomeinteresse = request.getParameter("nomeinteresse");
                nomeinteresse = nomeinteresse.toLowerCase();
                String res = gestoreInteressi.aggiungiInteresse(idProfilo, nomeinteresse);
                
                System.out.println("supero aggiungi interesse, res = "+res);
                

                if (!res.equals("-1")){
                    System.out.println("pronto a tornare nella jsp");
                    out.println(res);
                } else {
                    out.println("-1");
                }
                
                
               
            } else if (action.equals("remove")) {
                HttpSession session = request.getSession();
                Long idProfilo = (Long) session.getAttribute("id");
                System.out.println("entro in action remove");
                Long idInteresse =  Long.parseLong(request.getParameter("idinteresse"));
               
                int res = gestoreInteressi.rimuoviInteresse(idProfilo, idInteresse);
                
                System.out.println("supero rimuovi interesse, res = "+res);


                if (res == 0){
                    System.out.println("pronto a tornare nella jsp");
                    out.println("0");
                } else {
                    out.println("-1");
                }
                
                
               
            }else {
                out.println("-1");
                System.out.println("Action OTHER");
            }
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
