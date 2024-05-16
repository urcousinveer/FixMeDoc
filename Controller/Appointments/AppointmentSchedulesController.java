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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory.Result;


public class AppointmentSchedulesController implements Initializable{
    
  
   @FXML
    public ChoiceBox<String> checkbox;
    
 
    @FXML
    private Button SubmitBtn;

 

     @FXML
    private Button BackBtn;
 
    
  
   ObservableList<String> list = FXCollections.observableArrayList();

   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        additems();
    }
   
    public void additems(){
        
         try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         String sql = "Select Availability From schedules where Status = 1" ;
         
         ResultSet rs = stmt.executeQuery(sql);
         
         String a ="";
         
         while(rs.next()){
             
            
            a = rs.getString(1);
            
            this.list.add(a);
            
         }
         
         this.checkbox.setItems(list);
         
          
       
         
         }catch(Exception e){System.err.println(e);
         
        
    }

   
}
    public void SubmitBtnClicked(ActionEvent ev) throws SQLException, IOException{
   
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
         
         String sqlcheck = "Select * FROM appointmentschedule";
         
         ResultSet rscheck = stmt.executeQuery(sqlcheck);
         
         String storeCheck = "";
         
         while(rscheck.next()){
             
             storeCheck = rscheck.getString(4);
             
             
         }
         /////////////////////////////////////////////////////////////////
         if(String.valueOf(msp).equalsIgnoreCase(storeCheck)){
         
             try {
            Parent parent = FXMLLoader.load(getClass().getResource("Error_For_AppointmentBooking.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Note");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
             
         } else{
                  String appointmentschedule = this.checkbox.getValue();
            
                  // Storing doctor ID
                  
                  String sqldoctor = "(Select * from schedules where Availability =  " + "'"+appointmentschedule +"')" ;
                
                  
                  ResultSet rsdoctor = stmt.executeQuery(sqldoctor);
                  
                  String holddoctorID = ""; 
                  
                      
                     while(rsdoctor.next()) {
                     holddoctorID = String.valueOf(rsdoctor.getInt(2));
                     }
                  
                  
     
                  // Now Storing Numbers
                  String sqladd = "INSERT INTO appointmentschedule (`AppointmentID`, `AppointmentSchedule`, `DoctorID`, `PatientID`)" +
                          "VALUES" + "(" + "NULL"+ ",'" +appointmentschedule + "',"+ "'"+holddoctorID + "','"+msp +"'" + ")" ; 
                  
                 
                  stmt.executeUpdate(sqladd);
                      
                  
                  // alter status of appointmentschedule
                  
                  String sqlalter = "UPDATE `schedules` SET `Status` = '-1' WHERE `Availability` = " + "'" + appointmentschedule +"'";
                  
                  
                  stmt.executeUpdate(sqlalter);
                  
                  
                  con.close();
                  
                  try {
            Parent parent = FXMLLoader.load(getClass().getResource("AppointmentConfirmation.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Confirmation");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
                  
                  
      
                  
              
                  
              
         
         }
      }catch (Exception e){System.err.println(e);
    
    }
    }
    
          @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException{
     
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("User_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
       
   }
}
