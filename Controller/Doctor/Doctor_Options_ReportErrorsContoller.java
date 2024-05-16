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


public class Doctor_Options_ReportErrorsController implements Initializable {

    @FXML
    private Button BackBtn;
    @FXML
    private TextArea doctortext;
    @FXML
    private Button SubmitBtn;

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
   public void SubmitBtnClicked(ActionEvent ev) throws SQLException{
     
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
         
         String Fname = "";
         String Lname = "";
         String PhoneNumber = "";
         String Email = "";
         
         
         
           String basicquery2 = "Select * from doctorinfo where DoctorID = " +  id;  

              ResultSet rsbasic2 = stmt.executeQuery(basicquery2);
        
         while(rsbasic2.next()){
             
             Fname = rsbasic2.getString(2);
             Lname = rsbasic2.getString(3);
                     PhoneNumber = rsbasic2.getString(4);
                     Email = rsbasic2.getString(5);
         }
             
             String sql3 = "INSERT INTO `errormessages`(`FirstName`, `LastName`, `PhoneNumber`, `Email`, `Message`) VALUES ( '" + 
              Fname + "'," + "'"+ Lname + "','" + PhoneNumber + "','" + Email + "','"+this.doctortext.getText() +"')";
            
             stmt.executeUpdate(sql3);
             
             try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
          
         
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
       
   }
   
}
