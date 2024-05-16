package javafxapplication10;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class User_CancelAppointmentController {

    @FXML
    private Button BackBtn;
   
    @FXML
    private Button CacncelAppointmentBtn;

     
     
    @FXML
    private TextField AppointmentNumberGETTEXT;
    @FXML
    private Label errorlabel;

     
    @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException{
     
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
   public void CacncelAppointmentBtnClicked(ActionEvent ev) throws SQLException, IOException{
       try{
           Class.forName("com.mysql.jdbc.Driver");
           try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "")) {
               Statement  stmt = con.createStatement();
               
               
         String msp = "";
        
         String basicquery = "Select * from currentuser";
         
         ResultSet rsbasic = stmt.executeQuery(basicquery);
        
         while(rsbasic.next()){
             
             msp = rsbasic.getString(1);
             
         }
                          
                           String findAvailability = "Select * from appointmentschedule where AppointmentID =  " + this.AppointmentNumberGETTEXT.getText();
                           
                           ResultSet rsFindAvailability = stmt.executeQuery(findAvailability);
                           
                           String storeAvailability = "";
                           
                           while(rsFindAvailability.next()){
                               
                               storeAvailability = rsFindAvailability.getString(2);
                           }
                           
                           String errorforwrongAPPNo = "";
                           
                           String sqlcheck = "Select * from appointmentschedule";
                           
                           ResultSet rscheck = stmt.executeQuery(sqlcheck);
                           
                           while(rscheck.next()){
                               
                               errorforwrongAPPNo = String.valueOf(rscheck.getInt(1));
                           }
                           
                           if(this.AppointmentNumberGETTEXT.getText().equalsIgnoreCase(errorforwrongAPPNo)){
                               
                               String sql = "select * from appointmentschedule";
         
         ResultSet rs = stmt.executeQuery(sql);
         
         Boolean state = false;
         
         while(rs.next()){
             
             if(rs.getString(4).equalsIgnoreCase(msp)){
                 state = true;
                 
             }
             
         }
         
         if(state == true){
              String sql2 = "DELETE From appointmentschedule where AppointmentID = " + this.AppointmentNumberGETTEXT.getText();
                           
                           stmt.executeUpdate(sql2);
                           
                           String sqlalter = "UPDATE `schedules` SET `Status` = '1' WHERE `Availability` =  " + "'" + storeAvailability + "'";
                  
                  
                  stmt.executeUpdate(sqlalter);
                  try{
                   Parent parent = FXMLLoader.load(getClass().getResource("AppointmentCancellationMessage.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Confirmation");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         }else{
            this.errorlabel.setText("Wrong Appointment ID");
                               this.AppointmentNumberGETTEXT.setText(null); 
         }
                               
                               
                          
                  
                           }else{
                               this.errorlabel.setText("Wrong Appointment ID");
                               this.AppointmentNumberGETTEXT.setText(null);
                           }
                   
                con.close();
                
                
             
           } 
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
   }
 
}
