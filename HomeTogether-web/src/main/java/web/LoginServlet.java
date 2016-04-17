/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Diario;
import ejb.GestoreUtenti;
import ejb.Post;
import ejb.Profilo;
import ejb.ProfiloFacade;
import ejb.UtenteApp;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Andrea22
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            System.out.println("action is:" + action);
            if (action == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            } else if (action.equals("login")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                MessageDigest md;
                try {
                    md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] digest = md.digest();
                    StringBuffer sb = new StringBuffer();
                    for (byte b : digest) {
                        sb.append(String.format("%02x", b & 0xff));
                    }
                    password = sb.toString();

                    UtenteApp u = gestoreUtenti.loginUtente(email, password);
                    if (u != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("id", u.getProfilo().getId());

                        //Profilo p = profiloFacade.getProfilo(email);
                        session.setAttribute("nome", "" + u.getProfilo().getNome());
                        session.setAttribute("cognome", "" + u.getProfilo().getCognome());
                        session.setAttribute("email", "" + u.getProfilo().getEmail());
                        session.setAttribute("data", "" + u.getProfilo().getData_nascita());
                        session.setAttribute("sesso", "" + u.getProfilo().getSesso());
                        session.setAttribute("formazione", u.getProfilo().getFormazione());
                        session.setAttribute("occupazione", u.getProfilo().getOccupazione());
                        session.setAttribute("telefono", u.getProfilo().getTelefono());
                        //  s.setAttribute("location",""+location);
                        session.setAttribute("foto", "" + u.getProfilo().getFoto_profilo());
                        Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                        request.setAttribute("profilo", p);

                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                        rd.forward(request, response);

                    } else {
                        request.setAttribute("warning", "Login non valido!");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("warning", "Password non valida!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }

            } else if (action.equals("android-login")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                MessageDigest md;
                try {
                    md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] digest = md.digest();
                    StringBuffer sb = new StringBuffer();
                    for (byte b : digest) {
                        sb.append(String.format("%02x", b & 0xff));
                    }
                    password = sb.toString();

                    UtenteApp u = gestoreUtenti.loginUtente(email, password);
                    if (u != null) {
                        Profilo p = u.getProfilo();
                        out.print(buildGson(p));

                    } else {
                        out.print(0);
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.print(0);
                }

            } else {
                request.setAttribute("danger", "azione sconosciuta!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);

            }

        }
    }

    private String buildGson(Profilo p) {

        //JSONObject jo = new JSONObject();
        //Collection<JSONObject> items = new ArrayList<JSONObject>();
        JSONObject item = new JSONObject();
        item.put("nome", p.getNome());
        item.put("cognome", p.getCognome());
        item.put("email", p.getEmail());
        item.put("comune", p.getComune().getNome());
        item.put("foto_profilo", p.getFoto_profilo());
        item.put("id", p.getId());

        JSONObject json_follower = new JSONObject();
        JSONArray json_follower_array = new JSONArray();

        JSONObject json_diario = new JSONObject();
        JSONArray json_diario_array = new JSONArray();

        JSONObject json_post = new JSONObject();
        JSONArray json_post_array = new JSONArray();

        JSONObject json_partecipante = new JSONObject();
        JSONArray json_partecipante_array = new JSONArray();

        //JSONObject diario;
        Profilo following;
        Profilo partecipante;
        Diario diario;
        Post post;
        for (int i = 0; i < p.getFollowing().size(); i++) {
            following = p.getFollowing().get(i);

            for (int j = 0; j < following.getDiari().size(); j++) {

                diario = following.getDiari().get(j);
                json_diario.put("nome", diario.getNome());
                json_diario.put("data_inizio", diario.getData_inizio());
                json_diario.put("data_fine", diario.getData_fine());
                for (int k = 0; k < diario.getPartecipanti().size(); k++) {
                    partecipante = diario.getPartecipanti().get(k);
                    json_partecipante.put("nome", partecipante.getNome() + " " + partecipante.getCognome());
                    json_partecipante_array.add(json_partecipante);
                    json_partecipante = new JSONObject();
                }
                json_diario.put("partecipanti", json_partecipante_array);
                json_partecipante_array = new JSONArray();
                for (int k = 0; k < diario.getPost().size(); k++) {
                    post = diario.getPost().get(k);
                    json_post.put("mittente", post.getUser().getNome() + " " + post.getUser().getCognome());
                    json_post.put("testo", post.getTesto());
                    json_post_array.add(json_post);
                    json_post = new JSONObject();
                }
                json_diario.put("post", json_post_array);

                json_diario_array.add(json_diario);
                json_diario = new JSONObject();
                json_post_array = new JSONArray();

            }
            json_follower.put("nome", following.getNome());

            json_follower.put("diari", json_diario_array);
            json_follower_array.add(json_follower);
            json_follower = new JSONObject();
            json_diario_array = new JSONArray();

        }

        item.put("following", json_follower_array);
        return item.toJSONString();
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
