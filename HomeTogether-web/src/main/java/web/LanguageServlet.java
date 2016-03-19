/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import ejb.GestoreLingue;
import ejb.Lingua;
import ejb.Profilo;
import ejb.ProfiloFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrea22
 */
@WebServlet(name = "LanguageServlet", urlPatterns = {"/LanguageServlet"})
public class LanguageServlet extends HttpServlet {

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
    private ProfiloFacadeLocal profiloFacade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SAXException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");

            System.out.println("action is:" + action);
            if (action == null) {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            } else if (action.equals("add")) {
                System.out.println("entro in action add");
                Long idProfilo = (Long) session.getAttribute("id");

                String nomelingua = request.getParameter("nomelingua");
                nomelingua = nomelingua.toLowerCase();
                nomelingua = nomelingua.substring(0, 1).toUpperCase() + nomelingua.substring(1);
                Long res = gestoreLingue.aggiungiLingua(idProfilo, nomelingua);

                System.out.println("supero aggiungi lingua, res = " + res);

                if (res != null) {
                    out.println(res);
                } else {
                    out.println("-1");
                }

            } else if (action.equals("remove")) {
                Long idProfilo = (Long) session.getAttribute("id");
                System.out.println("entro in action remove");
                Long idlingua = Long.parseLong(request.getParameter("idlingua"));

                int res = gestoreLingue.rimuoviLingua(idProfilo, idlingua);

                System.out.println("supero rimuovi lingua, res = " + res);

                if (res == 0) {
                    System.out.println("pronto a tornare nella jsp");
                    out.println("0");
                } else {
                    out.println("-1");
                }

            } else if (action.equals("autocompileLingue")) {
                ServletContext context = getServletContext();
                List<Lingua> list = (List<Lingua>) context.getAttribute("listLingue");
                
                String nomeDigitato = request.getParameter("lingua").toLowerCase(); 
                String res = "";
                int cont = 0;
                if (nomeDigitato != null && list != null) {
                    for (int i = 0; i < list.size() && cont < 5; i++) {
                        if ((list.get(i).getNome().toLowerCase()).startsWith(nomeDigitato)) {
                            res += list.get(i).getNome() + "_";
                            cont++;
                        }

                    }
                    out.println(res);
                } else {
                    out.println("-1");
                }

            } else {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                request.setAttribute("danger", "azione sconosciuta!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);

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
        try {
            processRequest(request, response);
        }  catch (SAXException ex) {
            Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        }  catch (SAXException ex) {
            Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
