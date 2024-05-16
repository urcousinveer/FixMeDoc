package javafxapplication10;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class User_MyAppointmentController implements Initializable{

    @FXML
    private Button BackBtn;
    

    @FXML
    private TextArea details_text_area;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetDeatilsBtnClicked();
        
    }
    
    

   
   @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("User_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("User_Options");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
    @FXML
   public void GetDeatilsBtnClicked(){
       try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         String msp = "";
        
         String basicquery = "Select * from currentuser";
         
         ResultSet rsbasic = stmt.executeQuery(basicquery);
        
         while(rsbasic.next()){
             
             msp = rsbasic.getString(1);
             
         }
         
        
             
              String sql2 = "select * from appointmentschedule where PatientID = " + msp ;
         
         ResultSet rs2 = stmt.executeQuery(sql2);
         
         String x1 = "";
         
         String storeDoctorID ="";
         
         while (rs2.next()){
             
             x1 += "Appointment ID: " + String.valueOf(rs2.getInt(1)) 
                     
                    + "\n" + "Appointment Schedule: " + rs2.getString(2) + "\n";
             
             storeDoctorID = String.valueOf(rs2.getInt(3));
             
         }
         
           String sql3 = "select * from doctorinfo where DoctorID = " + storeDoctorID;
  
            ResultSet rs3 = stmt.executeQuery(sql3);
            
            while(rs3.next()){
                
                x1+= "Doctor's First Name: " + rs3.getString(3) + "\n" +
                      "Doctor's Last Name: " + rs3.getString(4) + "\n" +
                       "Doctor's Phone Number: " + rs3.getString(5) +"\n" +
                "Doctor's Email Address: " +rs3.getString(6) ;
                
                               
                
            }
            x1 += "\n\n" + "Contact US" + "\t\t\t\t\t" + "Location" + "\n"+
                  "Email: ClinicAB@email.com            2445 Unit 4, AB Street" + "\n" +
                    "Tel: 101-202-3003                           Samana, PB";
            this.details_text_area.setText(x1);
            
         
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
   }

  
}
