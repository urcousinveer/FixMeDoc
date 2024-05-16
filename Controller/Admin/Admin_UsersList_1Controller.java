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


public class Admin_UsersList_1Controller implements Initializable {

    @FXML
    private Button BackBtn;
    @FXML
    private TextField mspText;
    @FXML
    private TextField DoctoridText;
    @FXML
    private Button User_GetDetails;
    @FXML
    private Button Doctor_GetDetails;
    @FXML
    private Label errorLabel;
    @FXML
    private TextArea outputText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException{
     
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Admin_FunctionsSelection.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         
   }
   
   @FXML
    public void User_GetDetailsClicked(ActionEvent ev) throws SQLException{
   
         try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         
         String sql = "select * from patientinfo";
         
         ResultSet rs = stmt.executeQuery(sql);
         
         Boolean state = false;
         
         while(rs.next()){
             
             if(rs.getString(1).equalsIgnoreCase(this.mspText.getText())){
                 state = true;
                 
             }
             
         }
         
         
         if(state == true){
             
             
            String sql1 = "Select * from patientinfo where MSPNumber = " +this.mspText.getText();
            
            ResultSet rs1 = stmt.executeQuery(sql1);
             
             String x = "";
             
             while(rs1.next()){
                 
                 x+= "MSP Number: "  + rs1.getString(1) + "\n" + 
                         
                         "First Name: "+ rs1.getString(2) + "\n" +
                         "Last Name: "+ rs1.getString(3) + "\n" +
                         "Phone Number: "+rs1.getString(4) + "\n" +
                         "Email: : "+ rs1.getString(5) + "\n" +
                         "Date Of Birth: : "+ rs1.getString(6) + "\n" +
                         "Medical History: "+rs1.getString(7) + "\n" +
                         "Address: "+ rs1.getString(8) + ", " + rs1.getString(9) +", " +  rs1.getString(10) + ", " + rs1.getString(11) +"\n" +
                         "PIN: "+ rs1.getString(12) + "\n" +
                         "Prescription: " + rs1.getString(13) + "\n\n" ;
                     
             }
             
             
             
             
             
             
             x = x + "Appointments:" + "\n";
             
             
            Boolean check = false;
            
            String sql11 = "Select * from appointmentschedule";
             
            ResultSet rs11 = stmt.executeQuery(sql11);
            
            while(rs11.next()){
                
                String store = rs11.getString(4);
                
                if(this.mspText.getText().equalsIgnoreCase(store)){
                    check = true;
                }
                
                
            }
             
             if(check == true){
             String sql2 = "Select * from appointmentschedule where PatientID = " + this.mspText.getText();
             
            ResultSet rs2 = stmt.executeQuery(sql2);
            
            
            
            
            while(rs2.next()){
                
                x+= "Appointment ID: " + String.valueOf(rs2.getInt(1)) + "\n" +
                        "Appointment Schedule: " + rs2.getString(2) + "\n" + 
                "Doctor ID: " + String.valueOf(rs2.getInt(3)) + "\n\n";
                
                
            }
             }else{
                 x += "null";
                 
             }
             
    
             
             
             
         this.outputText.setText(x);
         }else {  
             this.errorLabel.setText("WRONG MSP Number");
             this.mspText.setText(null);
             this.outputText.setText(null);
         
         }
         
            
         this.DoctoridText.setText(null);
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
        
    }
    
     
   
   @FXML
    public void Doctor_GetDetailsClicked(ActionEvent ev) throws SQLException{
   
         try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         String sql = "select * from doctorinfo";
         
         ResultSet rs = stmt.executeQuery(sql);
         
         Boolean state = false;
         
         while(rs.next()){
             
             if(rs.getString(1).equalsIgnoreCase(this.DoctoridText.getText())){
                 state = true;
                 
             }
             
         }
         
         
         if(state == true){
             
             
             String sql1 = "Select * from doctorinfo where DoctorId = " + this.DoctoridText.getText();
             
             ResultSet rs1 = stmt.executeQuery(sql1);
             
             
             String x = "";
             
             while(rs1.next()){
             
                 x+= "ID:" + " " +String.valueOf(rs1.getInt(1)) + "\n"+
                         "First Name:" + " " +rs1.getString(2) + "\n" +
                 "Last Name: " + " " +rs1.getString(3) + "\n" +
                 "Phone Number: " +" " + rs1.getString(4) + "\n" +
                 "Email: " + " " +rs1.getString(5) + "\n" ;
               
                 
             }
             
             this.outputText.setText(x);
             
             
             
             
         }else{
             this.errorLabel.setText("Wrong Doctor ID");
             this.outputText.setText(null);
         }
         
         this.mspText.setText(null);
         
           con.close();
           
           
 
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
        
    

}
}
