/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.GestoreInteressi;
import ejb.Profilo;
import ejb.ProfiloFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

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
    @EJB
    private ProfiloFacadeLocal profiloFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParserConfigurationException, SAXException, TransformerException {
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
                Long idProfilo = (Long) session.getAttribute("id");

                String nomeinteresse = request.getParameter("nomeinteresse");
                nomeinteresse = nomeinteresse.toLowerCase();
                Long res = gestoreInteressi.aggiungiInteresse(idProfilo, nomeinteresse);

                if (res != null) {
                    out.println(res);
                } else {
                    out.println("-1");
                }

            } else if (action.equals("remove")) {
                Long idProfilo = (Long) session.getAttribute("id");
                Long idInteresse = Long.parseLong(request.getParameter("idinteresse"));

                int res = gestoreInteressi.rimuoviInteresse(idProfilo, idInteresse);


                if (res == 0) {
                    out.println("0");
                } else {
                    out.println("-1");
                }

            } else if (action.equals("goToInterest")) {
                if (request.getParameter("nome") != null && !request.getParameter("nome").equals("")) {
                    String nome = request.getParameter("nome");
                    String nomereplace = nome.replaceAll(" ", "%20");

                    URL url = new URL("https://it.wikipedia.org/w/api.php?format=xml&action=query&prop=langlinks&prop=extracts&titles=" + nomereplace + "&redirects=true");
                    URLConnection conn = url.openConnection();

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    org.w3c.dom.Document doc = builder.parse(conn.getInputStream());

                    TransformerFactory factory2 = TransformerFactory.newInstance();
                    Transformer xform = factory2.newTransformer();

                    StringWriter writer = new StringWriter();
                    StreamResult result = new StreamResult(writer);
                    xform.transform(new DOMSource(doc), result);

                    request.setAttribute("xml", writer.toString());
                    request.setAttribute("nome", nome);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/interesse.jsp");
                    rd.forward(request, response);
                } else {
                    Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    request.setAttribute("profilo", personalProfile);
                    request.setAttribute("danger", "azione sconosciuta!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);

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
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(InterestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(InterestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(InterestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(InterestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(InterestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(InterestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
