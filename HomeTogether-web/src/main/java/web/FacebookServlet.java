package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import ejb.GestoreUtenti;
import ejb.ProfiloFacade;
import ejb.Profilo;
import ejb.UtenteFacebook;
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
@WebServlet(name = "FacebookServlet", urlPatterns = {"/FacebookServlet"})
public class FacebookServlet extends HttpServlet {

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
        HttpSession s = request.getSession();

        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            System.out.println("action is:"+action);
            if (true) {
                //String username = request.getParameter("username");
                String idSocial= request.getParameter("idSocial");
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String email = request.getParameter("email");
                String giorno = request.getParameter("giorno");
                String mese = request.getParameter("mese");
                String anno = request.getParameter("anno");
                String sesso = request.getParameter("sesso");
                String location= request.getParameter("localita");
                String foto = request.getParameter("foto");
                
                UtenteFacebook u = gestoreUtenti.loginFacebook(email, idSocial);
                //Profilo p = profiloFacade.getProfilo(email);
                if(u != null){
                    s.setAttribute("id", u.getProfilo().getId());
                    s.setAttribute("nome",""+u.getProfilo().getNome());
                    s.setAttribute("cognome",""+u.getProfilo().getCognome());
                    s.setAttribute("email",""+u.getProfilo().getEmail());
                    s.setAttribute("data",""+u.getProfilo().getData_nascita());
                    s.setAttribute("sesso",""+u.getProfilo().getSesso());
                    s.setAttribute("location",""+location);
                    s.setAttribute("foto",""+u.getProfilo().getFoto_profilo());
                    
                }else{
                    response.getWriter().write("no");
                }
                
                
                
                
            } else {
                System.out.println("Action OTHER");
            }
            
        }
    }
private String buildGson(List<UtenteFacebook> u) {

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