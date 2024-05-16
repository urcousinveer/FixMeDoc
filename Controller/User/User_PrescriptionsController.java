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

public class User_PrescriptionsController implements Initializable {

 
    @FXML
    private Button BackBtn;
    
    @FXML
    private TextArea outputArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetPrecriptionBtnClicked();
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
   
    @FXML
   public void GetPrecriptionBtnClicked(){
     
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
         
        
              String sqlprescription = "Select * From patientinfo where MSPNumber = " + "'"+ msp+ "'";
             
         
                  ResultSet rsprecription = stmt.executeQuery(sqlprescription);
         
         String x = "";
         
         while(rsprecription.next()){
             
             x += rsprecription.getString(13);
             
         }
            this.outputArea.setText(x);
            
            this.outputArea.setEditable(false);
        
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
   }
   
    
}
   
