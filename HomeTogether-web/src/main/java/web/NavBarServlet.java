/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import ejb.Profilo;
import ejb.ProfiloFacade;
import ejb.UtenteGoogle;
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        try (PrintWriter out = response.getWriter()) {
            if(action.equals("searchUtente")){
                String nomeDigitato=(String)  request.getParameter("ric_utente");
                if(nomeDigitato!= null){
                   List<Profilo> res = profiloFacade.getProfiloUtente(nomeDigitato.toLowerCase(),(Long) (session.getAttribute("id")),0);
                   request.setAttribute("utente", res);
                   request.setAttribute("ric_utente", nomeDigitato);
                   Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                   request.setAttribute("profilo", p);
                   
                   /*String name="";
                    for(int i=0;i<res.size();i++){
                        name=res.get(i).getNome()+" "+ res.get(i).getCognome();
                        
                    }*/
                   
                }
                
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/utenti.jsp");
                rd.forward(request, response);
            }else if(action.equals("searchAjax")){
                String nomeDigitato=(String)  request.getParameter("ric_utente");
                int offset =Integer.parseInt(request.getParameter("offset"));
                List<Profilo> res = profiloFacade.getProfiloUtente(nomeDigitato.toLowerCase(),(Long) (session.getAttribute("id")),offset);
                out.println(buildGson(res));
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
