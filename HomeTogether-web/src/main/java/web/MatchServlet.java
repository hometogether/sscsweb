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
import ejb.Provincia;
import ejb.Regione;
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
            if (action == null) {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            } else if (action.equals("searchUtente")) {
                ServletContext context = getServletContext();
                List<Comune> list = (List<Comune>) context.getAttribute("list");
                boolean trovato = false;
                boolean comune_trovato = false;
                boolean provincia_trovato = false;
                boolean regione_trovato = false;
                Long idComune = null;
                Long idProvincia = null;
                Long idRegione = null;
                if (request.getParameter("localita") != null && !request.getParameter("localita").equals("") && list != null) {
                    String comune = (String) request.getParameter("localita").toLowerCase();

                    for (int i = 0; i < list.size() && comune_trovato == false; i++) {

                        if ((list.get(i).getNome().toLowerCase()).equals(comune)) {
                            trovato = true;
                            comune_trovato = true;
                            idComune = list.get(i).getId();
                        }

                    }
                } else if (request.getParameter("provincia") != null && !request.getParameter("provincia").equals("") && list != null) {
                    String provincia = (String) request.getParameter("provincia").toLowerCase();
                    for (int i = 0; i < list.size() && provincia_trovato == false; i++) {

                        if ((list.get(i).getProvincia().getNome().toLowerCase()).equals(provincia)) {
                            trovato = true;
                            provincia_trovato = true;
                            idProvincia = list.get(i).getProvincia().getId();
                        }

                    }
                } else if (request.getParameter("regione") != null && !request.getParameter("regione").equals("") && list != null) {
                    String regione = (String) request.getParameter("regione").toLowerCase();
                    for (int i = 0; i < list.size() && regione_trovato == false; i++) {

                        if ((list.get(i).getProvincia().getRegione().getNome().toLowerCase()).equals(regione)) {
                            trovato = true;
                            regione_trovato = true;
                            idRegione = list.get(i).getProvincia().getRegione().getId();
                        }
                    }
                } else {
                    Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    request.setAttribute("profilo", personalProfile);
                    request.setAttribute("warning", "Specificare il comune, la provincia o la regione!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                }

                if (trovato == false) {
                    Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    request.setAttribute("profilo", personalProfile);
                    request.setAttribute("warning", "Località non esistente!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                } else {
                    Long idprofilo = (Long) session.getAttribute("id");
                    List<Profilo> profili = null;
                    if (comune_trovato) {
                        profili = gestoreMatch.getMatchComune(idComune, idprofilo);
                    } else if (provincia_trovato) {
                        profili = gestoreMatch.getMatchProvincia(idProvincia, idprofilo);
                    } else if (regione_trovato) {
                        profili = gestoreMatch.getMatchRegione(idRegione, idprofilo);
                    }

                    if (profili != null) {
                        Profilo profilo = profiloFacade.getProfilo(idprofilo);
                        List<Match> res = new ArrayList<Match>();
                        for (int i = 0; i < profili.size(); i++) {
                            res.add(gestoreMatch.getMatch(profilo, profili.get(i)));
                        }
                        // Sorting
                        Collections.sort(res, new Comparator<Match>() {
                            @Override
                            public int compare(Match item2, Match item1) {
                                return Double.compare(item1.getMatch_totale(), item2.getMatch_totale());
                            }
                        });
                        request.setAttribute("match", res);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/match.jsp");
                        rd.forward(request, response);

                    } else {
                        request.setAttribute("warning", "Nessun Risultato Trovato!");
                        Profilo p = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                        request.setAttribute("profilo", p);
                        request.setAttribute("match", null);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/match.jsp");
                        rd.forward(request, response);
                    }

                }
            } else if (action.equals("autocompileRegione")) {
                ServletContext context = getServletContext();
                List<Regione> list = (List<Regione>) context.getAttribute("listRe");

                String nomeDigitato = request.getParameter("regione").toLowerCase();
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

            } else if (action.equals("autocompileProvincia")) {
                ServletContext context = getServletContext();
                List<Provincia> list = (List<Provincia>) context.getAttribute("listPro");

                String nomeDigitato = request.getParameter("provincia").toLowerCase();
                List<Provincia> res = new ArrayList<Provincia>();
                int cont = 0;
                if (nomeDigitato != null && list != null) {
                    for (int i = 0; i < list.size() && cont < 5; i++) {
                        if ((list.get(i).getNome().toLowerCase()).startsWith(nomeDigitato)) {
                            res.add(list.get(i));
                            cont++;
                        }

                    }
                    out.println(buildGsonP(res));
                } else {
                    out.println("-1");
                }

            } else if (action.equals("autocompileComune")) {
                ServletContext context = getServletContext();
                List<Comune> list = (List<Comune>) context.getAttribute("list");

                String nomeDigitato = request.getParameter("comune").toLowerCase();
                List<Comune> res = new ArrayList<Comune>();
                int cont = 0;
                if (nomeDigitato != null && list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (cont != 5 && (list.get(i).getNome().toLowerCase()).startsWith(nomeDigitato)) {
                            res.add(list.get(i));
                            cont++;
                        } else if ((list.get(i).getNome().toLowerCase()).equals(nomeDigitato)) {
                            res.add(list.get(i));
                        }

                    }
                    out.println(buildGsonC(res));
                } else {
                    out.println("-1");
                }

            } else if (action.equals("searchAjax")) {
                String nomeDigitato = (String) request.getParameter("ric_utente");
                int offset = Integer.parseInt(request.getParameter("offset"));
                List<Profilo> res = profiloFacade.getProfiloUtente(nomeDigitato.toLowerCase(), (Long) (session.getAttribute("id")), offset);
                out.println(buildGson(res));
            } else {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                request.setAttribute("danger", "azione sconosciuta!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                rd.forward(request, response);

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

    private String buildGsonC(List<Comune> c) {

        Gson gson = new Gson();
        String json = gson.toJson(c);

        if (json == null) {
            System.out.println("servlet buildGson: NULL");
        } else {
            System.out.println("servlet buildGson: NOT NULL  " + json);
        }
        return json;
    }

    private String buildGsonP(List<Provincia> c) {

        Gson gson = new Gson();
        String json = gson.toJson(c);

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
