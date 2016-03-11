/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Comune;
import ejb.GestoreComuni;
import ejb.GestoreLingue;
import ejb.GestoreProvincie;
import ejb.GestoreRegioni;
import ejb.GestoreUtenti;
import ejb.Lingua;
import ejb.Provincia;
import ejb.Regione;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrea22
 */
@WebServlet(name = "ContextServlet", urlPatterns = {"/ContextServlet"})
public class ContextServlet extends HttpServlet implements ServletContextListener{

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
    private GestoreLingue gestoreLingue;
    @EJB
    private GestoreComuni gestoreComuni;
    @EJB
    private GestoreProvincie gestoreProvincie;
    @EJB
    private GestoreRegioni gestoreRegioni;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        
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

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("entro nel conextinitialize");
        List<Comune> list = gestoreComuni.creaListaComuni();
        List<Provincia> listPro = gestoreProvincie.creaListaProvincie();
        List<Regione> listRe = gestoreRegioni.creaListaRegioni();
        List<Lingua> lingue = gestoreLingue.creaListaLingue();
        System.out.println("creo liste comuni, provincie, regioni e lingue.");

        sce.getServletContext().setAttribute("list", list);
        sce.getServletContext().setAttribute("listPro", listPro);
        sce.getServletContext().setAttribute("listRe", listRe);
        sce.getServletContext().setAttribute("listLingue", lingue);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("list");
        sce.getServletContext().removeAttribute("listPro");
        sce.getServletContext().removeAttribute("listRe");
        sce.getServletContext().removeAttribute("listLingue");
    }

    
    
}
