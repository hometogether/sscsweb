/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import ejb.Comune;
import ejb.GestoreMatch;
import ejb.Profilo;
import ejb.ProfiloFacade;
import ejb.UtenteGoogle;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.Match;

/**
 *
 * @author Antonio
 */
@WebServlet(name = "MatchServlet", urlPatterns = {"/MatchServlet"})
public class MatchServlet extends HttpServlet {

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
    @EJB
    GestoreMatch gestoreMatch;

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
            if (action.equals("searchUtente")) {
                String comune = (String) request.getParameter("localita").toLowerCase();;
                String provincia = (String) request.getParameter("provincia").toLowerCase();;
                String regione = (String) request.getParameter("regione").toLowerCase();;
                ServletContext context = getServletContext();
                List<Comune> list = (List<Comune>) context.getAttribute("list");
                boolean trovato = false;
                boolean comune_trovato = false;
                boolean provincia_trovato = false;
                boolean regione_trovato = false;
                Long idComune = null;
                Long idProvincia = null;
                Long idRegione = null;
                if (comune != null && !comune.equals("") && list != null) {
                    for (int i = 0; i < list.size() && comune_trovato == false; i++) {
                        
                        if ((list.get(i).getNome().toLowerCase()).equals(comune)) {
                            trovato = true;
                            comune_trovato = true;
                            idComune = list.get(i).getId();
                        }

                    }
                } else if (provincia != null && !provincia.equals("") && list != null) {
                    for (int i = 0; i < list.size() && provincia_trovato == false; i++) {
                        
                        if ((list.get(i).getProvincia().getNome().toLowerCase()).equals(provincia)) {
                            trovato = true;
                            provincia_trovato = true;
                            idProvincia = list.get(i).getProvincia().getId();
                        }

                    }
                } else if (regione != null && !regione.equals("") && list != null) {
                    for (int i = 0; i < list.size() && regione_trovato == false; i++) {
                        
                        if ((list.get(i).getProvincia().getRegione().getNome().toLowerCase()).equals(regione)) {
                            trovato = true;
                            regione_trovato = true;
                            idRegione = list.get(i).getProvincia().getRegione().getId();

                        }

                    }
                } 
                
                if (trovato == false){
                    //non c'è un comune, provincia o regione che matcha con i comuni nel DB
                    //GESTIRE ERRORE
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                } else {
                    Long idprofilo = (Long) session.getAttribute("id");
                    List<Profilo> profili = null;
                    if (comune_trovato){
                        profili = gestoreMatch.getMatchComune(idComune,idprofilo);
                    } else if (provincia_trovato){
                        profili = gestoreMatch.getMatchProvincia(idProvincia,idprofilo);
                    } else if (regione_trovato){
                        profili = gestoreMatch.getMatchRegione(idRegione,idprofilo);
                    }
                    
                    if (profili != null) {
                        Profilo profilo = profiloFacade.getProfilo(idprofilo);
                        List<Match> res = new ArrayList<Match>();
                        for (int i = 0; i < profili.size(); i++){
                            res.add(gestoreMatch.getMatch(profilo, profili.get(i)));
                        }
                        // Sorting
                        Collections.sort(res, new Comparator<Match>() {
                                @Override
                                public int compare(Match item2, Match item1)
                                {   
                                    return  Float.compare(item1.getMatch_totale(), item2.getMatch_totale());
                                }
                            });
                        request.setAttribute("match", res);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/match.jsp");
                        rd.forward(request, response);

                        /*String name="";
                         for(int i=0;i<res.size();i++){
                         name=res.get(i).getNome()+" "+ res.get(i).getCognome();

                         }*/
                    }
                    
                }
            } else if (action.equals("searchAjax")) {
                String nomeDigitato = (String) request.getParameter("ric_utente");
                int offset = Integer.parseInt(request.getParameter("offset"));
                List<Profilo> res = profiloFacade.getProfiloUtente(nomeDigitato.toLowerCase(), (Long) (session.getAttribute("id")), offset);
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
