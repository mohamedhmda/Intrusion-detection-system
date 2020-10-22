/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohamed
 */
@WebServlet(name = "Info", urlPatterns = {"/Info"})
public class Info extends HttpServlet {

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
            if(Training.isEmpty())
                GetData(1);
            if(Coded.isEmpty())
                GetData(2);
            if(Norm.isEmpty())
                GetData(3);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Info.class.getName()).log(Level.SEVERE, null, ex);
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
        int bd = Integer.parseInt(request.getParameter("BD"));
        String json=new Gson().toJson("");
        Type listType = new TypeToken<List<DisplayedData>>() {}.getType();
        switch(bd){
            case 1:
                json = new Gson().toJson(Training,listType);
                break;
            case 2:
                json = new Gson().toJson(Coded,listType);
                break;
            case 3:
                json = new Gson().toJson(Norm,listType);
                break;
            default:
                break;
        }
        response.setContentType("application/json");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(json);
    }
    
    List<DisplayedData> Training = new ArrayList<DisplayedData>();
    List<DisplayedData> Coded = new ArrayList<DisplayedData>();
    List<DisplayedData> Norm = new ArrayList<DisplayedData>();
    protected void GetData(int bd) throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/test";
        String user = "postgres";
        String password = "1234";
        String bds[] = {
            "train",
            "train_coded",
            "train_cnn_normalised"
        };
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String header_select = "logged_in,dst_host_same_srv_rate,dst_host_serror_rate,same_srv_rate,dst_host_srv_serror_rate,dst_host_srv_count,dst_host_srv_rerror_rate,srv_rerror_rate,dst_host_same_src_port_rate,protocol_type,flag,service,count,diff_srv_rate,is_guest_login,root_shell,wrong_fragment,attaque_name";
            String max = "select "+header_select+" from "+bds[bd-1]+" limit 15000";
            PreparedStatement pst = con.prepareStatement(max);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                DisplayedData Train_data =new DisplayedData();
                    Train_data.logged_in = String.valueOf(rs.getObject(1));
                    Train_data.dst_host_same_srv_rate = String.valueOf(rs.getObject(2));
                    Train_data.dst_host_serror_rate = String.valueOf(rs.getObject(3));
                    Train_data.same_srv_rate = String.valueOf(rs.getObject(4));
                    Train_data.dst_host_srv_serror_rate = String.valueOf(rs.getObject(5));
                    Train_data.dst_host_srv_count = String.valueOf(rs.getObject(6));
                    Train_data.dst_host_srv_rerror_rate = String.valueOf(rs.getObject(7));
                    Train_data.srv_rerror_rate = String.valueOf(rs.getObject(8));
                    Train_data.dst_host_same_src_port_rate = String.valueOf(rs.getObject(9));
                    Train_data.protocol_type = String.valueOf(rs.getObject(10));
                    Train_data.flag = String.valueOf(rs.getObject(11));
                    Train_data.service = String.valueOf(rs.getObject(12));
                    Train_data.count = String.valueOf(rs.getObject(13));
                    Train_data.diff_srv_rate = String.valueOf(rs.getObject(14));
                    Train_data.is_guest_login = String.valueOf(rs.getObject(15));
                    Train_data.root_shell = String.valueOf(rs.getObject(16));
                    Train_data.wrong_fragment = String.valueOf(rs.getObject(17));
                    Train_data.attaque_name = String.valueOf(rs.getObject(18));
                switch(bd){
                    case 1:
                        Training.add(Train_data);
                        break;
                    case 2:
                        Coded.add(Train_data);
                        break;
                    case 3:
                        Norm.add(Train_data);
                        break;
                    default:
                        break;
                }
                
            }
        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Info.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
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
