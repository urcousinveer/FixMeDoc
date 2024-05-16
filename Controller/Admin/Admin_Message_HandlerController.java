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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Admin_Message_HandlerController implements Initializable {

    @FXML
    private Button BactBtn;
    @FXML
    private TextArea doctormessage_text;
    @FXML
    private TextArea errorMessage_text;
    @FXML
    private Button Replybtn;
    @FXML
    private Button clearmessagesBtn;
    @FXML
    private Button clearmsgBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displaymessage();
    }    
    
    @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException, IOException{
     
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
   
   
   public void displaymessage(){
       
        try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
       
         
         String sql1 = "Select * from messagetoadmin";
         
         ResultSet rs1 = stmt.executeQuery(sql1);
       
        String x = "";
        
        while(rs1.next()){
            x+= "Doctor ID: " +rs1.getString(6)+"\n"+
                    "Name: " + rs1.getString(1) + " " + rs1.getString(2) + "\n" +
                    "Phone Number: "  + rs1.getString(3)+ "\n" +
                    "Email: " + rs1.getString(4)+"\n" +
            "Error Message: " + rs1.getString(5) + "\n\n\n";
            
          
            
        }
         
        this.doctormessage_text.setText(x);
        
        
        
         String sql2 = "Select * from errormessages";
         
         ResultSet rs2 = stmt.executeQuery(sql2);
       
        String x1 = "";
        
        while(rs2.next()){
            x1+= "Name: " + rs2.getString(1) + " " + rs2.getString(2) + "\n" +
                    "Phone Number: "  + rs2.getString(3)+ "\n" +
                    "Email: " + rs2.getString(4)+"\n" +
            "Error Message: " + rs2.getString(5) + "\n\n\n";
            
          
            
        }
        
        this.errorMessage_text.setText(x1);

              
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
       
   
       
       
       
   }
   
   
   @FXML
   public void ReplybtnClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Admin_Reply_Doctor.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Reply");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   @FXML
   public void clearmsgBtnClicked(ActionEvent ev) throws SQLException, IOException{
       
       
       try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
       
         String sql = "TRUNCATE TABLE messagetoadmin";
         
         stmt.executeUpdate(sql);
                 
                 
               this.doctormessage_text.setText(null);
       
                
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
   }
       
       @FXML
       public void clearmessagesBtnClicked(ActionEvent ev) throws SQLException, IOException{
       
       
       try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
       
         String sql = "TRUNCATE TABLE errormessages";
         
         stmt.executeUpdate(sql);
                 
                 
               this.errorMessage_text.setText(null);
       
                
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
       
       
       
   
   }
   
   
}
