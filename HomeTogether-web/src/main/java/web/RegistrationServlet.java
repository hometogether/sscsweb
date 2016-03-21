/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import ejb.Comune;
import ejb.GestoreComuni;
import ejb.GestoreUtenti;
import ejb.Profilo;
import ejb.Provincia;
import ejb.Regione;
import ejb.UtenteApp;
import ejb.UtenteFacebook;
import ejb.UtenteGoogle;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if (action == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            } else if (action.equals("registration")) {
                if (request.getParameter("nome") != null && !request.getParameter("nome").equals("")
                        && request.getParameter("cognome") != null && !request.getParameter("cognome").equals("")
                        && request.getParameter("email") != null && !request.getParameter("email").equals("")
                        && request.getParameter("r_email") != null && !request.getParameter("r_email").equals("")
                        && request.getParameter("data_nascita") != null && !request.getParameter("data_nascita").equals("")
                        && request.getParameter("sesso") != null && !request.getParameter("sesso").equals("")
                        //&& request.getParameter("foto_profilo") != null && !request.getParameter("foto_profilo").equals("")
                        && request.getParameter("tipo_registrazione") != null && !request.getParameter("tipo_registrazione").equals("")
                        && request.getParameter("localita") != null && !request.getParameter("localita").equals("")) {

                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    String email = request.getParameter("email");
                    String r_email = request.getParameter("r_email");
                    String data_nascita = request.getParameter("data_nascita");
                    String sesso = request.getParameter("sesso");
                    String foto = request.getParameter("foto_profilo");
                    String tipo_registrazione = request.getParameter("tipo_registrazione");

                    String localita = request.getParameter("localita").toLowerCase();
                    System.out.println("localita:" + localita);
                    Comune comune = null;
                    ServletContext context = getServletContext();
                    List<Comune> list = (List<Comune>) context.getAttribute("list");
                    boolean trovato = false;
                    for (int i = 0; i < list.size() && trovato == false; i++) {
                        if ((list.get(i).getNome().toLowerCase()).equals(localita)) {

                            System.out.println("comune trovato!");
                            comune = list.get(i);
                            trovato = true;
                        }

                    }

                    if (tipo_registrazione.equals("0")) {
                        if (request.getParameter("password") != null && !request.getParameter("password").equals("")
                                && request.getParameter("r_password") != null && !request.getParameter("r_password").equals("")) {
                            String password = request.getParameter("password");
                            String r_password = request.getParameter("r_password");
                            Profilo p = gestoreUtenti.aggiungiUser(nome, cognome, password, r_password, email, r_email, data_nascita, sesso, comune);
                            if (p != null) {
                                session.setAttribute("id", p.getId());
                                session.setAttribute("nome", "" + p.getNome());
                                session.setAttribute("cognome", "" + p.getCognome());
                                session.setAttribute("email", "" + p.getEmail());
                                session.setAttribute("data", "" + p.getData_nascita());
                                session.setAttribute("sesso", "" + p.getSesso());
                                session.setAttribute("formazione", p.getFormazione());
                                session.setAttribute("occupazione", p.getOccupazione());
                                session.setAttribute("telefono", p.getTelefono());
                                session.setAttribute("foto", "" + p.getFoto_profilo());
                                request.setAttribute("profilo", p);

                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                                rd.forward(request, response);
                            } else {
                                request.setAttribute("warning", "Errore nella Registrazione, campi compilati non correttamente o email non disponibile!");
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                                rd.forward(request, response);
                            }
                        } else {
                            request.setAttribute("warning", "Errore nella Registrazione, password non inserita!");
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                            rd.forward(request, response);
                        }

                    } else if (tipo_registrazione.equals("1")) {
                        if (request.getParameter("idSocial") != null && !request.getParameter("idSocial").equals("")) {
                            String idSocial = request.getParameter("idSocial");

                            Profilo p = gestoreUtenti.aggiungiUserFacebook(nome, cognome, idSocial, email, r_email, data_nascita, sesso, foto, comune);
                            if (p != null) {
                                session.setAttribute("id", p.getId());
                                session.setAttribute("nome", "" + p.getNome());
                                session.setAttribute("cognome", "" + p.getCognome());
                                session.setAttribute("email", "" + p.getEmail());
                                session.setAttribute("data", "" + p.getData_nascita());
                                session.setAttribute("sesso", "" + p.getSesso());
                                session.setAttribute("formazione", p.getFormazione());
                                session.setAttribute("occupazione", p.getOccupazione());
                                session.setAttribute("telefono", p.getTelefono());
                                //  s.setAttribute("location",""+location);
                                session.setAttribute("foto", "" + p.getFoto_profilo());
                                request.setAttribute("profilo", p);
                                request.setAttribute("profilo", p);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                                rd.forward(request, response);
                            } else {
                                request.setAttribute("warning", "Errore nella Registrazione, email non disponibile!");
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                                rd.forward(request, response);
                            }
                        } else {
                            request.setAttribute("warning", "Errore nella Registrazione, id di Facebook non reperibile!");
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                            rd.forward(request, response);
                        }

                    } else if (tipo_registrazione.equals("2")) {
                        if (request.getParameter("idSocial") != null && !request.getParameter("idSocial").equals("")) {
                            String idSocial = request.getParameter("idSocial");
                            Profilo p = gestoreUtenti.aggiungiUserGoogle(nome, cognome, idSocial, email, r_email, data_nascita, sesso, foto, comune);
                            if (p != null) {
                                session.setAttribute("id", p.getId());

                                session.setAttribute("nome", "" + p.getNome());
                                session.setAttribute("cognome", "" + p.getCognome());
                                session.setAttribute("email", "" + p.getEmail());
                                session.setAttribute("data", "" + p.getData_nascita());
                                session.setAttribute("sesso", "" + p.getSesso());
                                session.setAttribute("formazione", p.getFormazione());
                                session.setAttribute("occupazione", p.getOccupazione());
                                session.setAttribute("telefono", p.getTelefono());
                                //  s.setAttribute("location",""+location);
                                session.setAttribute("foto", "" + p.getFoto_profilo());
                                request.setAttribute("profilo", p);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                                rd.forward(request, response);
                            } else {
                                request.setAttribute("warning", "Errore nella Registrazione, email non disponibile!");
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                                rd.forward(request, response);
                            }

                        } else {
                            request.setAttribute("warning", "Errore nella Registrazione, id di Google non reperibile!");
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                            rd.forward(request, response);
                        }

                    }

                } else {
                    request.setAttribute("warning", "Compilare tutti i campi!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }

            } else if (action.equals("autocompileComune")) {
                ServletContext context = getServletContext();
                List<Comune> list = (List<Comune>) context.getAttribute("list");

                String nomeDigitato = request.getParameter("comune").toLowerCase();
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

            } else if (action.equals("checkComune")) {
                ServletContext context = getServletContext();
                List<Comune> list = (List<Comune>) context.getAttribute("list");

                String comune = request.getParameter("comune").toLowerCase();
                boolean trovato = false;
                if (comune != null && list != null) {
                    for (int i = 0; i < list.size() && trovato == false; i++) {
                        if ((list.get(i).getNome().toLowerCase()).equals(comune)) {
                            trovato = true;
                        }

                    }
                    if (trovato) {
                        out.println("0");
                    } else {
                        out.println("-1");
                    }
                } else {
                    out.println("-1");
                }

            } else {
                request.setAttribute("danger", "azione sconosciuta!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
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
