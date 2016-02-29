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
        HttpSession session= request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            System.out.println("action is:" + action);
            if (action.equals("registration")) {
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String email = request.getParameter("email");
                String r_email = request.getParameter("r_email");
                String data_nascita = request.getParameter("data_nascita");
                String sesso = request.getParameter("sesso");
                String foto = request.getParameter("foto_profilo");
                String tipo_registrazione = request.getParameter("tipo_registrazione");
                
                String localita = request.getParameter("localita").toLowerCase();
                System.out.println("localita:"+localita);
                Comune comune=null;
                ServletContext context = getServletContext();
                List<Comune> list = (List<Comune>) context.getAttribute("list");
                boolean trovato = false;
                for (int i = 0; i < list.size() && trovato == false; i++) {
                    if ((list.get(i).getNome().toLowerCase()).equals(localita)) {
                        
                        System.out.println("comune trovato!");
                        comune=list.get(i);
                        trovato = true;
                    }

                }
                
                if (tipo_registrazione.equals("0")) {
                    String password = request.getParameter("password");
                    String r_password = request.getParameter("r_password");
                    System.out.println(data_nascita);
                    System.out.println(sesso);
                    int res = gestoreUtenti.aggiungiUser(nome, cognome, password, r_password, email, r_email, data_nascita, sesso, comune);
                    if (res == 0) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet RegistrationServlet</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Errore!</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                } else if (tipo_registrazione.equals("1")) {
                    String idSocial = request.getParameter("idSocial");

                    int res = gestoreUtenti.aggiungiUserFacebook(nome, cognome, idSocial, email, r_email, data_nascita, sesso, foto, comune);
                    if (res == 0) {
                        List<UtenteFacebook> lista = gestoreUtenti.getUserFacebook();

                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                        rd.forward(request, response);
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet RegistrationServlet</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Errore!</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                } else if (tipo_registrazione.equals("2")) {
                    String idSocial = request.getParameter("idSocial");
                    System.out.println("idgoogle:" + idSocial);
                    int res = gestoreUtenti.aggiungiUserGoogle(nome, cognome, idSocial, email, r_email, data_nascita, sesso, foto, comune);
                    if (res == 0) {
                        //List<UtenteGoogle> lista = gestoreUtenti.getUserGoogle();
                        //String gsonList = buildGson(lista);

                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                        rd.forward(request, response);
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet RegistrationServlet</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Errore!</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
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
