/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohamed
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    List<Vecteur> vectors_to_send = new ArrayList<Vecteur>();

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
        vectors_to_send.clear();
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
        int page = Integer.parseInt(request.getParameter("Page"));
        String json = new Gson().toJson("");
        
        
        long elapsedTime = System.currentTimeMillis() - Primary_Frame.controller.start;
        Date date = new Date((long)(elapsedTime));
        DateFormat formatted = new SimpleDateFormat("HH:mm:ss.SSS");
        formatted.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = formatted.format(date);
        Primary_Frame.controller.page_info.time = formattedDate;
        
        
        switch(page){
                case 1:
                    json = new Gson().toJson(Primary_Frame.controller.page_info);
                    break;
                case 2:
                    if(!Primary_Frame.controller.final_list2.isEmpty()){
                    //    vectors_to_send.clear();
                    //    vectors_to_send.addAll(analyseur.final_list2);
                    //    if(!vectors_to_send.isEmpty()){
                            json = new Gson().toJson(Primary_Frame.controller.final_list2);                            
                    //    }
                    }
                    break;
                case 3:
                    int id = Integer.parseInt(request.getParameter("id"));
                    for(Vecteur v : Primary_Frame.controller.final_list2){
                        if(v.id == id){
                            json = new Gson().toJson(v.vecteur);
                        }
                    }
            }
        response.setContentType("application/json");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(json);
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
