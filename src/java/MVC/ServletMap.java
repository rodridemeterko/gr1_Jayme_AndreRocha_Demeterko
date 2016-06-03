/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import db.mapDAO;

/**
 *
 * @author demeterko
 */
@WebServlet(name = "ServletMap", urlPatterns = {"/ServletMap"})
public class ServletMap extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{
                MapModel f = new MapModel((long)Long.parseLong(request.getParameter("user_id")), request.getParameter("map_url"));
                mapDAO u = new mapDAO();
                u.insere(f);
                out.print("Imagem enviada com sucesso");
                //ServletContext app = this.getServletContext(); 
                //RequestDispatcher rd = app.getRequestDispatcher("/inicial.jsp?user_id="+f.getUserId()); 
                //rd.forward(request, response);
                 
            }
            catch(NumberFormatException e)
            {
                request.setAttribute("error", "Entrada Inv&aacute;lida: par&acirc;metro n&atilde;o &eacute; um n&uacute;mero v&aacute;lido."); 
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/error.jsp"); 
                rd.forward(request, response);
                //PrintWriter out1 = response.getWriter();
                //out.print("NumberFormatException:\n"+e.getMessage());
            }
            /*catch(ServletException | IOException e)
            {
                request.setAttribute("error", "Erro no processamento da aplica&ccedil;&atilde;o"); 
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/error.jsp"); 
                rd.forward(request, response);
                //PrintWriter out1 = response.getWriter();
                //out.print("ServletException(or IOException):\n"+e.getMessage()); 
            }*/
            
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
