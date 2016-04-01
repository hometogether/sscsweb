/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.common.collect.Lists;
import ejb.Commento;
import ejb.Diario;
import ejb.GestoreDiari;
import ejb.GestoreUtenti;
import ejb.Post;
import ejb.Profilo;
import ejb.ProfiloFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "DiaryServlet", urlPatterns = {"/DiaryServlet"})
@MultipartConfig

public class DiaryServlet extends HttpServlet {

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
    private GestoreDiari gestoreDiari;
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
            if (action == null) {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                rd.forward(request, response);
            } else if (action.equals("goToDiary")) {
                if (request.getParameter("idDiario") != null && !request.getParameter("idDiario").equals("")) {
                    Long idDiario = new Long(request.getParameter("idDiario"));
                    Diario d = gestoreDiari.getDiario(idDiario);
                    Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                    //List<Post> posts = gestoreDiari.getPosts(idDiario);

                    if (d != null) {
                        d.setPost(Lists.reverse(d.getPost()));
                        String post;
                        String tmp="";
                        for (int i = 0; i < d.getPost().size(); i++) {
                            if (!d.getPost().get(i).getHashtags().isEmpty()) {
                                post = d.getPost().get(i).getTesto();
                                for (int j = 0; j < post.length(); j++) {
                                    if (post.charAt(j) == '#') {
                                        tmp+="<a href='#' style='color:rgba(228, 131, 18, 0.6)'><B>#";
                                        j++;
                                        while (j < post.length() && post.charAt(j) != ' '){
                                            tmp+=post.charAt(j);
                                            j++;
                                        }
                                        tmp+="</B></a>";
                                        if (j < post.length()){
                                            tmp+=post.charAt(j);
                                        }
                                    } else {
                                        tmp+=post.charAt(j);
                                    }
                                }
                                d.getPost().get(i).setTesto(tmp);
                                tmp="";
                            }
                        }

                        request.setAttribute("profilo", p);
                        request.setAttribute("diario", d);

                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/diary.jsp");
                        rd.forward(request, response);
                    } else {
                        Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                        request.setAttribute("profilo", personalProfile);

                        request.setAttribute("danger", "diario non esistente!");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                        rd.forward(request, response);
                    }

                } else {
                    Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                    request.setAttribute("profilo", personalProfile);

                    request.setAttribute("danger", "diario non esistente!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                    rd.forward(request, response);

                }
            } else if (action.equals("submitPost")) {
                Long idDiario = new Long(request.getParameter("idDiario"));
                String text = request.getParameter("text");
                Diario d = gestoreDiari.getDiario(idDiario);
                Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                Post post = gestoreDiari.aggiungiPost(d, p, text);

                //estrazione degli hashtag nel testo
                String[] hashtags = text.split("#");
                String[] tmp;
                for (int i = 0; i < hashtags.length; i++) {
                    if (i == 0 && text.charAt(0) == '#') {
                        tmp = hashtags[i].split(" ");
                        System.out.println("hashtag:" + tmp[0]);
                        gestoreDiari.aggiungiHashtag(post, tmp[0]);
                    } else if (i != 0) {
                        tmp = hashtags[i].split(" ");
                        System.out.println("hashtag:" + tmp[0]);
                        gestoreDiari.aggiungiHashtag(post, tmp[0]);
                    }

                }

                out.println(post.getId());

            } else if (action.equals("editPost")) {
                Long idPost = new Long(request.getParameter("idPost"));
                String testo = request.getParameter("testo");

                Post post = gestoreDiari.getPost(idPost);
                gestoreDiari.modificaPost(post, testo);

                out.println("0");
            } else if (action.equals("removePost")) {
                Long idPost = new Long(request.getParameter("idPost"));

                Post post = gestoreDiari.getPost(idPost);

                gestoreDiari.eliminaPost(post);

                out.println("0");
            } else if (action.equals("addLike")) {
                Long idPost = new Long(request.getParameter("idPost"));
                Post post = gestoreDiari.getPost(idPost);
                Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                gestoreDiari.aggiungiLike(post, p);

                out.println("0");

            } else if (action.equals("removeLike")) {
                Long idPost = new Long(request.getParameter("idPost"));
                Post post = gestoreDiari.getPost(idPost);
                Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                int res = gestoreDiari.rimuoviLike(post, p);

                out.println(res);

            } else if (action.equals("addComment")) {
                Long idPost = new Long(request.getParameter("idPost"));
                String testo = request.getParameter("testo");
                Post post = gestoreDiari.getPost(idPost);
                Profilo p = profiloFacade.getProfilo((String) session.getAttribute("email"));
                Long idCommento = gestoreDiari.aggiungiCommento(post, p, testo);
                out.println(idCommento);

            } else if (action.equals("editComment")) {
                Long idCommento = new Long(request.getParameter("idCommento"));
                String testo = request.getParameter("testo");
                Commento commento = gestoreDiari.getCommento(idCommento);
                gestoreDiari.modificaCommento(commento, testo);
                out.println("0");

            } else if (action.equals("removeComment")) {
                Long idCommento = new Long(request.getParameter("idCommento"));
                Commento commento = gestoreDiari.getCommento(idCommento);
                gestoreDiari.eliminaCommento(commento);
                out.println("0");

            } else {
                Profilo personalProfile = profiloFacade.getProfilo((Long) (session.getAttribute("id")));
                request.setAttribute("profilo", personalProfile);
                request.setAttribute("danger", "azione sconosciuta!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
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
