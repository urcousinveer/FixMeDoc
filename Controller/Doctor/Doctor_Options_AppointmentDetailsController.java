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

public class Doctor_Options_AppointmentDetailsController implements Initializable {

    @FXML
    private Button BackBtn;
    @FXML
    private TextField msp_text;
    @FXML
    private Button GetDetailsBtn;
    @FXML
    private TextArea Output_details;
    @FXML
    private TextArea prescription_text;
    @FXML
    private Button Next_patient_Btn;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
    @FXML
   public void GetDetailsClicked(ActionEvent ev) throws SQLException, IOException{
       
       
        try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         
         String checkID = "Select * from patientinfo where MSPNumber = " + this.msp_text.getText();
         
         ResultSet rscheck = stmt.executeQuery(checkID);
         String msp ="";
         while(rscheck.next()){
             msp =  rscheck.getString(1);
             
         }
         
         if(this.msp_text.getText().equalsIgnoreCase(msp)){
         
         
         String x = "";
         
         String sql = "Select * from patientinfo where MSPNumber = " + this.msp_text.getText();
         
         ResultSet rs = stmt.executeQuery(sql);
         
         while(rs.next()){
             
             
             
             
             x += "Name: " + rs.getString(2) + " " + rs.getString(3) + "\n" +
                     "Email Address: " + rs.getString(4) + "\n"+
                     "Date of Birth: " + rs.getString(6) + "\n" +
                     
                      "Medical History: " + rs.getString(7) + "\n" +
                   
                     "Prescription: " + rs.getString(13) + "\n" ;
             
         }
         
             
         String sql1 = "Select * from appointmentschedule WHERE PatientID = " + this.msp_text.getText();
         
         ResultSet rs1 = stmt.executeQuery(sql1);
         String id ="";
         
         String x1 = "";
             
             Boolean state = false;
         while(rs1.next()){
      
             id = rs1.getString(4);
             
         
             
             
             if(this.msp_text.getText().equalsIgnoreCase(id)){
                 state = true;
                 
             }
                 
         }     
         
         String sql2= "Select * from appointmentschedule WHERE PatientID = " + this.msp_text.getText();
         
         ResultSet rs2 = stmt.executeQuery(sql2);
         
         if(state == true ){
              while(rs2.next()){
                 x1+= "Appointment ID: " + String.valueOf(rs2.getInt(1)) + "\n"+
                         "Appointment Schedule: " + rs2.getString(2) + "\n\n";
                         
                 x1 = x1 + x;
                              
             this.Output_details.setText(x1);
              this.errorLabel.setText(null);
              }
                 }else{
                     x1 = "No Appointments Yet" + "\n\n";
                     
                     x1 = x1 + x;
                      this.Output_details.setText(x1);
                      this.errorLabel.setText(null);
                     
                 }
         
         
         
                     
             con.close();
         }else{
             this.errorLabel.setText("Wrong MSP Number");
             this.msp_text.setText(null);
        
       } 
    
        }catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
       
       
       
   }
   
   
   
     @FXML
   public void Next_patient_BtnClicked(ActionEvent ev) throws SQLException, IOException{
       
       
        try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         String sqlalter = "UPDATE `patientinfo` SET `Prescription` = ' "+ this.prescription_text.getText()+"' "+ " WHERE `MSPNumber` =  " + "'" + this.msp_text.getText() + "'";
   
         
         stmt.executeUpdate(sqlalter);
         
         
         String getavailability = " Select * from appointmentschedule where PatientID = " + this.msp_text.getText();
         
         ResultSet rsGA = stmt.executeQuery(getavailability);
         
         String storeAvailability = "";
         
         while(rsGA.next()){
             
             storeAvailability = rsGA.getString(2);
             
         }
          String sqlalter3 = "DELETE From appointmentschedule where PatientID = " + this.msp_text.getText();
         
          stmt.executeUpdate(sqlalter3);
         String sqlalter2 = "DELETE From schedules where Availability = '" + storeAvailability +"'";
 
         
         stmt.executeUpdate(sqlalter2);
         
        
         
         
         
          try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        }catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
   
   }
}
