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
import javafx.stage.Stage;

public class Doctor_OptionsController implements Initializable {

    @FXML
    private Label DisplayLabel;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Button ReportErrorsBtn;
    @FXML
    private Button AppointmentBtn;
    @FXML
    private Button MessageforadminBtn;
    @FXML
    private Button CheckMsgButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      DisplayName();
    }    
    
    
    public void DisplayName(){
         try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         
        int x = 0;
         
          String sql = "select * from currentdoctor";
         
         ResultSet rs = stmt.executeQuery(sql);
         
   
         
         while(rs.next()){
             
             
             x = rs.getInt(1);
             
                 
             }
             
         String sql1 = "Select  * from doctorinfo Where DoctorID = " + x;
         
         ResultSet rs1 = stmt.executeQuery(sql1);
         
         String x1 = "Welcome ";
         
         while(rs1.next()){
             
             x1 += rs1.getString(2) + " " + rs1.getString(3);
             
         }
         
         this.DisplayLabel.setText(x1);
         
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
        
        
        
    }
    
    
       
    
   

         @FXML
   public void LogoutBtnClicked(ActionEvent ev) throws SQLException{
       try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         int id = 0;
        
         String basicquery = "Select * from currentdoctor";
         
         ResultSet rsbasic = stmt.executeQuery(basicquery);
        
         while(rsbasic.next()){
             
             id = rsbasic.getInt(1);
             
         }
         
        String update = "DELETE from currentdoctor WHERE DoctorID = " + id;
             
           stmt.executeUpdate(update);
             
            
         
           con.close();
            
       
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Home Page");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
       
           
       
   }
   
   
    @FXML
   public void MessageforadminBtnClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options_MessageToAdmin.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Message For Admin");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
    @FXML
   public void ReportErrorsBtnClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options_ReportErrors.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Report Errors");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
    @FXML
   public void CheckMsgButtonClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Option_CheckMessages.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Message From Admin");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
   
   
    @FXML
   public void AppointmentBtnClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options_AppointmentDetails.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Appointment Details");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
}
