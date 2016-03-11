/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.GestoreUtenti;
import ejb.Profilo;
import ejb.ProfiloFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Antonio
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
@MultipartConfig

public class ProfileServlet extends HttpServlet {

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
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if (action.equals("add_profile_image")) {
                String email = (String) session.getAttribute("email");
                Part filePart = request.getPart("nomeFile");
                InputStream filecontent = filePart.getInputStream();
                FileOutputStream prova = new FileOutputStream("C:/Users/Antonio/Documents/NetBeansProjects/HomeTogether/HomeTogether-web/src/main/webapp/profile_img/" + filePart.getSubmittedFileName());
                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    prova.write(bytes, 0, read);
                }
                String foto = "profile_img/" + filePart.getSubmittedFileName();
                gestoreUtenti.modificaFotoProfilo(email, foto);
                session.setAttribute("foto", "profile_img/" + filePart.getSubmittedFileName());
                filecontent.close();
                prova.close();
                Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                request.setAttribute("profilo", p);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                rd.forward(request, response);
            } else if (action.equals("mod_info")) {
                String location = request.getParameter("localita");
                String data = request.getParameter("data_nascita");
                String email = (String) (session.getAttribute("email"));
                String formazione = request.getParameter("formazione");
                String occupazione = request.getParameter("occupazione");
                String telefono = request.getParameter("telefono");
                Profilo p = gestoreUtenti.modificaInfo(email, data, formazione, occupazione, telefono);
                request.setAttribute("profilo", p);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                rd.forward(request, response);
            } else if (action.equals("follow")) {
                Long idfollow = new Long(request.getParameter("id"));
                Long id = (Long) (session.getAttribute("id"));
                System.out.println("id request:" + request.getParameter("id"));
                System.out.println("id sessione:" + session.getAttribute("id"));
                Profilo personalProfile = profiloFacade.getProfilo(id);
                Profilo followProfile = profiloFacade.getProfilo(idfollow);
                int res = gestoreUtenti.aggiungiFollowing(personalProfile, followProfile);
                if (res == 0) {
                    out.println("0");
                } else {
                    out.println("-1");
                }

            } else if (action.equals("eliminafollow")) {
                Long idfollow = new Long(request.getParameter("id"));
                Long id = (Long) (session.getAttribute("id"));
                Profilo personalProfile = profiloFacade.getProfilo(id);
                Profilo followProfile = profiloFacade.getProfilo(idfollow);
                int res = gestoreUtenti.eliminaFollowing(personalProfile, followProfile);
                if (res == 0) {
                    out.println("0");
                } else {
                    out.println("-1");
                }

            } else {
                //GESTIRE ERRORE
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
