package javafxapplication10;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class User_RegisterController  {

    @FXML
    private Button BackBtn;
    @FXML
    private TextField MSPNumber;
    @FXML
    private TextField FName;
    @FXML
    private TextField LName;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Email;
    @FXML
    private TextField DOB;
    @FXML
    private TextField History;
    @FXML
    private TextField Street;
    @FXML
    private TextField City;
    @FXML
    private TextField PostalCode;
    @FXML
    private TextField Province;
    @FXML
    private TextField PIN;
    @FXML
    private Button RegisterBtn;
    @FXML
    private Label ConformLabel;


    /**
     * Initializes the controller class.
     */
 
    
      @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException{
     
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("User_Selection.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
       
   }
   
     @FXML
   public void RegisterBtnClicked(ActionEvent ev) throws SQLException{
     
       try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         
         String sql = "INSERT INTO patientinfo (MSPNumber, FirstName, LastName, PhoneNumber,EmailAddress,DOB,MedicalHistory,Street,City,PostalCode,Province,PIN)"+
                  "VALUES" + "(" + "'"+ this.MSPNumber.getText()+ "'," + "'"+this.FName.getText() + "'," + "'"+this.LName.getText() + "'," +"'"+this.PhoneNumber.getText() + "',"+"'"+this.Email.getText() + "',"+"'"+this.DOB.getText() + "'," +"'"+this.History.getText() + "',"+
                         "'"+this.Street.getText() + "',"+"'"+this.City.getText() + "',"+"'"+this.PostalCode.getText() + "',"+"'"+this.Province.getText()+ "',"+"'"+this.PIN.getText() + "'" + ")";
         
         stmt.executeUpdate(sql);
         
       
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("RegisterConfirmition.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Confirmation");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            
            this.ConformLabel.setText("Please Provide Correct Value For Each Filed");
        }        
       
   }
   
}
