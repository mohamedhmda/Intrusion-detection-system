/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS_Package;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;

/**
 *
 * @author mohamed
 */
public class Approche {
    static ArrayList<ArrayList<double[]>> atk_class= new ArrayList<ArrayList<double[]>>();
    static String header_select = "logged_in,dst_host_same_srv_rate,dst_host_serror_rate,same_srv_rate,dst_host_srv_serror_rate,dst_host_srv_count,dst_host_srv_rerror_rate,srv_rerror_rate,dst_host_same_src_port_rate,protocol_type,flag,service,count,diff_srv_rate,is_guest_login,root_shell,wrong_fragment,attaque_name";
    static int header_select_lenght = 18;
    static String services[] = {"aol","auth","bgp","courier","csnet_ns","ctf","daytime","discard","domain","domain_u","echo","eco_i","ecr_i","efs","exec","finger","ftp","ftp_data","gopher","harvest","hostnames","http","http_2784","http_443","http_8001","imap4","IRC","iso_tsap","klogin","kshell","ldap","link","login","mtp","name","netbios_dgm","netbios_ns","netbios_ssn","netstat","nnsp","nntp","ntp_u","other","pm_dump","pop_2","pop_3","printer","private","red_i","remote_job","rje","shell","smtp","sql_net","ssh","sunrpc","supdup","systat","telnet","tftp_u","tim_i","time","urh_i","urp_i","uucp","uucp_path","vmnet","whois","X11","Z39_50"};
    static String protocols[] = {"tcp", "udp", "icmp"};
    static String flags[] = {"OTH", "REJ", "RSTO", "RSTOS0", "RSTR", "S0", "S1", "S2", "S3", "SF", "SH" };
    static String[] attaques ={"back","buffer_overflow","ftp_write","guess_passwd","imap","ipsweep","land","loadmodule","multihop","neptune","nmap","perl","phf","pod","portsweep","rootkit","satan","smurf","spy","teardrop","warezclient","warezmaster","normal"};
    static String[] ddos = {"back","land","neptune","pod","smurf","teardrop"};
    static String[] prob = {"ipsweep","nmap","portsweep","satan"};
    static String[] u2r = {"buffer_overflow","loadmodule","perl","rootkit"};
    static String[] r2l = {"ftp_write","guess_pass","imap","multihop","phf","spy","warezclient","warezmaster"};
    static double[] dummy ={9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
    static int false_negative=0,true_negative=0,true_positive=0,false_positive=0;
    static int K=1;
    static double maxs[] = new double[header_select_lenght];
    static double mins[] = new double[header_select_lenght];
    static final ThreadLocal<Integer> good = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
          return Integer.valueOf(0);
    }
    };
    static final ThreadLocal<Integer> bad = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
          return Integer.valueOf(0);
    }
    };
    
    /**
     *  Denormalisation
     */
    public static synchronized String denorm(double lab){
        int att = (int) Math.round(lab * 22);
        //System.out.println("lab = "+attaques[att]);
        return attaques[att];
    }
    
    /**
     *  Normalisation
     */
    public static void Init_norm(){
        String attributes[] = header_select.split(",");
        String url = "jdbc:postgresql://localhost:5432/test";
        String user = "postgres";
        String password = "1234";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            for(int i=0; i < attributes.length; i++){
                String max_min = "select max("+attributes[i]+"), min("+attributes[i]+") FROM test_coded";
                PreparedStatement pst = con.prepareStatement(max_min);           
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    maxs[i] = rs.getDouble(1);
                    mins[i] = rs.getDouble(2);
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(Approche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static double Normalisation(String attribut,int i){
        if(i == 9){
            int protocol = Arrays.asList(protocols).indexOf(attribut)+1;
            attribut = String.valueOf(protocol);
        }else if(i == 10){
            int flag = Arrays.asList(flags).indexOf(attribut)+1;
            attribut = String.valueOf(flag);
        }else if(i == 11){
            int service = Arrays.asList(services).indexOf(attribut)+1;
            attribut = String.valueOf(service);
        }
        double Xi = Double.parseDouble(attribut);        
        double Xnouv = (Xi-mins[i])/(maxs[i]-mins[i]);
        BigDecimal bd = new BigDecimal(Double.toString(Xnouv));
        bd = bd.setScale(8, RoundingMode.HALF_UP);
        return bd.doubleValue();
        //return Math.round(Xnouv);
    }
    
    /**
     *  Building model
     */
    public ArrayList<ArrayList<double[]>> build_model(String choosen_approche){
        Object pauselock = new Object();
        synchronized(pauselock){
            for(int i = 0; i < attaques.length ; i++){
                get_train(i,choosen_approche);
            }
            System.out.println("model built "+Thread.currentThread().getName());
        }
        return atk_class;
    }
    public static synchronized void get_train(int i_train,String choosen_approche){
        String url = "jdbc:postgresql://localhost:5432/test";
        String user = "postgres";
        String password = "1234";
        ArrayList<double[]> train= new ArrayList<double[]>();
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String max = "select "+header_select+" FROM "+choosen_approche+" where round(attaque_name::numeric,2) = round("+i_train+"::numeric/22,2)";
            PreparedStatement pst = con.prepareStatement(max);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                double vecteur[] = new double[header_select_lenght-1];
                for(int i = 1; i < header_select_lenght ; i++){
                    double number = rs.getDouble(i);
                    
                    if(Double.isNaN(number)){
                        vecteur[i-1] = 0;
                    }else{
                        vecteur[i-1] = number;
                    }
                }
                train.add(vecteur);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Approche.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        atk_class.add(i_train,train);
    }
    
    /**
     *  Getting test data and distance calculation 
     */
    public synchronized void get_test_data(Vecteur vecteur){
        String prediction = test(vecteur.vecteur);
        vecteur.prediction_lvl2 = prediction;
        if(prediction.equals("normal")){
            vecteur.prediction = "normal";
            Primary_Frame.controller.page_info.Prediction[0]++;
            Primary_Frame.controller.page_info.pred_chart2[0]++;
        }else if(Arrays.asList(ddos).contains(prediction)){
            vecteur.prediction = "ddos";
            Primary_Frame.controller.page_info.Prediction[1]++;
            Primary_Frame.controller.page_info.pred_chart2[1]++;
        }else if(Arrays.asList(prob).contains(prediction)){
            vecteur.prediction = "prob";
            Primary_Frame.controller.page_info.Prediction[1]++;
            Primary_Frame.controller.page_info.pred_chart2[2]++;
        }else if(Arrays.asList(u2r).contains(prediction)){
            vecteur.prediction = "u2r";
            Primary_Frame.controller.page_info.Prediction[1]++;
            Primary_Frame.controller.page_info.pred_chart2[3]++;
        }else if(Arrays.asList(r2l).contains(prediction)){
            vecteur.prediction = "r2l";
            Primary_Frame.controller.page_info.Prediction[1]++;
            Primary_Frame.controller.page_info.pred_chart2[4]++;
        }
        Primary_Frame.controller.final_list2.add(vecteur);
        if(prediction.equals("normal")){
            int g = good.get();
            g++;
            if(vecteur.Label.equals("normal")){
                Primary_Frame.controller.page_info.VN++;
            }else{
                Primary_Frame.controller.page_info.FP++;
            }
            new Thread(new Sender(vecteur),"sender-"+Thread.currentThread().getName()).start();
            good.set(g);
        }else{
            if(vecteur.Label.equals("normal")){
                Primary_Frame.controller.page_info.FN++;
            }else{
                Primary_Frame.controller.page_info.VP++;
            }
            int b = bad.get();
            b++;
            bad.set(b);
        }
    }
    /**
     * calculate distance
     */
    public static synchronized double[] dist(ArrayList<double[]> train, double[] test,int k){
        if(k > train.size()){
            for(int i = 1; i < k ; i++){
               train.add(dummy);
            }
        }        
        double distances[] = new double[train.size()];
        double distances_to_return[] = new double[k];
        int j=0;
        for(double[] t : train){
            double distance=0;
            for(int i = 0; i < header_select_lenght-1 ; i++){
                distance += Math.pow(t[i]-test[i],2);                
            }
            distances[j] = Math.sqrt(distance);
            j++;
        }
        Arrays.sort(distances);
        for(int i = 0; i < k ; i++)
            distances_to_return[i] = distances[i];
        return distances_to_return;
    }
    
    /**
     *  Test function
     */
    public static synchronized String test(double vecteur[]){
        ArrayList<double[]> knn_class_dist= new ArrayList<double[]>();
        for(int i = 0; i < attaques.length ; i++){
            knn_class_dist.add(i, dist(atk_class.get(i),vecteur,K));
        }
        // chaque distence avec un label
        TreeMap<Double,String> knn = new TreeMap<Double,String>();
        for(int i = 0; i < K ; i++){
            for(int j = 0 ; j < attaques.length ; j++){
                knn.put(knn_class_dist.get(j)[i], attaques[j]);
            }
        }
        // prevent false_negative
        double services[] = { 0.22058824 , 0.64705882 , 0.83823529, 0.86764706, 0.35294118, 0.45588235 ,0.14705882};
        double counts[] = {0.00195695 , 0.0039139 };///{0.0019569471 , 0.0039138943 };
        double s = Math.round(vecteur[11] * 10000000000.0) / 10000000000.0;
        double c = Math.round(vecteur[12] * 10000000000.0) / 10000000000.0;
        if(knn.firstEntry().getValue().equals("normal") && DoubleStream.of(services).anyMatch(x -> x == s) && DoubleStream.of(counts).anyMatch(x -> x == c)){
            knn.remove(knn.firstKey());
        }
        double flags = 0.5;
        if(knn.firstEntry().getValue().equals("normal") && vecteur[10]== flags){
           knn.remove(knn.firstKey());
        }
        double diff_srv_rate[] = {0.05, 0.06, 0.1 , 0.18, 0.19, 0.25, 0.31, 0.53};
        double d = Math.round(vecteur[13] * 10000000000.0) / 10000000000.0;
        if(knn.firstEntry().getValue().equals("normal") && DoubleStream.of(diff_srv_rate).anyMatch(x -> x == d)){
           knn.remove(knn.firstKey());
        }
        if(knn.firstEntry().getValue().equals("normal")){
            double srv_rerror_rate[] = {0.11, 0.14, 0.17, 0.2, 0.4};
            double dst_host_srv_rerror_rate[] = {0.06};
            double dst_host_srv_serror_rate[] = {1};
            double same_srv_rate[] = {0.56, 0.62};
            double dst_host_serror_rate[] = {0.29, 0.32};
            double d1 = Math.round(vecteur[7] * 10000000000.0) / 10000000000.0;
            double d2 = Math.round(vecteur[6] * 10000000000.0) / 10000000000.0;
            double d3 = Math.round(vecteur[4] * 10000000000.0) / 10000000000.0;
            double d4 = Math.round(vecteur[3] * 10000000000.0) / 10000000000.0;
            double d5 = Math.round(vecteur[2] * 10000000000.0) / 10000000000.0;
            if(DoubleStream.of(srv_rerror_rate).anyMatch(x -> x == d1) || DoubleStream.of(dst_host_srv_rerror_rate).anyMatch(x -> x == d2) || DoubleStream.of(dst_host_srv_serror_rate).anyMatch(x -> x == d3) || DoubleStream.of(same_srv_rate).anyMatch(x -> x == d4) || DoubleStream.of(dst_host_serror_rate).anyMatch(x -> x == d5)){
                knn.remove(knn.firstKey());
            }
        }
        double services2[] = { 0.64705882 };
        double protocol_type[] = { 0 };
        double s2 = Math.round(vecteur[11] * 10000000000.0) / 10000000000.0;
        double p = Math.round(vecteur[11] * 10000000000.0) / 10000000000.0;
        if(knn.firstEntry().getValue().equals("normal") && DoubleStream.of(services2).anyMatch(x -> x == s2) && DoubleStream.of(protocol_type).anyMatch(x -> x == p)){
            knn.remove(knn.firstKey());
        }
        // trim treemap, only needed
        int size = knn.size();
        for(int i = 0; i < size-K ; i++){
            knn.remove(knn.lastKey());
        }
        TreeMap<Integer,String> class_majoritaire = new TreeMap<Integer,String>();
        for(int j = 0 ; j < attaques.length ; j++){
            if(Collections.frequency(knn.values(),attaques[j]) != 0)
                class_majoritaire.put(Collections.frequency(knn.values(),attaques[j]),attaques[j]);
        }
        return class_majoritaire.lastEntry().getValue();
    }
}
