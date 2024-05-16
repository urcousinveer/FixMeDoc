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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Doctor_LoginController implements Initializable {

    @FXML
    private TextField doctorIDtext;
    @FXML
    private TextField pintext;
    @FXML
    private Button LoginBtn;
    @FXML
    private Label ErrorLabel;
    @FXML
    private Button BackBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
       @FXML
   public void LoginBtnClicked(ActionEvent ev) throws SQLException{
       
        try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         String sql = "select * from doctorinfo";
         
         ResultSet rs = stmt.executeQuery(sql);
         
         Boolean state = false;
         
         while(rs.next()){
             
             if(rs.getString(1).equalsIgnoreCase(this.doctorIDtext.getText())){
                 state = true;
                 
             }
             
         }
         
         
         if(state == true){
              String sql1 = "select * from doctorinfo where DoctorID = " + this.doctorIDtext.getText();
              
              ResultSet rs1 = stmt.executeQuery(sql1);
              
              String storePIN = "";
         
              while(rs1.next()){
                  
                  storePIN = String.valueOf(rs1.getInt(6));
              }
         
        
         
         if(this.pintext.getText().equalsIgnoreCase(storePIN)){
             
             
             String sqladd  = "INSERT INTO currentdoctor (DoctorID, PIN)  VALUES ('" + this.doctorIDtext.getText() + "','" + this.pintext.getText() + "')";
             
             stmt.executeUpdate(sqladd);
                
               try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
              } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         } else{this.ErrorLabel.setText("WRONG PIN");
         this.doctorIDtext.setText(null);
         this.pintext.setText(null);
         }
         
         
         
         
         
         }else {  
             this.ErrorLabel.setText("WRONG ID");
             this.doctorIDtext.setText(null);
         this.pintext.setText(null);}
         
       
            
            
          
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
   }
   
   
       
    @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException{
     
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Home");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
       
   }
    
}
