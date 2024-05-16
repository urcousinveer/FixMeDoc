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


public class Admin_UpdateScheduleController implements Initializable {

    @FXML
    private TextField availabilitytext;
    @FXML
    private Button Addbtn;
   
    @FXML
    private Button DeleteBtn;
    @FXML
    private TextField Doctor1IDtext;
    @FXML
    private Button Backbtn;
    @FXML
    private TextField Availability;
    @FXML
    private Label errorlabel;
    @FXML
    private Label errorlabel2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
   public void BackbtnClicked(ActionEvent ev) throws SQLException{
     
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
    public void AddbtnClicked(ActionEvent ev) throws SQLException{
   
        try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         String sqladd = "INSERT INTO `schedules`(`Availability`, `DoctorID`, `Status`) VALUES ('" + this.availabilitytext.getText() + "','" +
                 this.Doctor1IDtext.getText() + "',' 1 ' )";
         
         stmt.executeUpdate(sqladd);
         
         this.availabilitytext.setText(null);
         this.Doctor1IDtext.setText(null);
         this.errorlabel.setText(null);
         this.errorlabel.setText("Schedule Added");
        con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           
           this.errorlabel.setText("Schedule already exists");
            this.availabilitytext.setText(null);
         this.Doctor1IDtext.setText(null);
       }
    }
    
    public void DeleteBtnClicked(ActionEvent ev) throws SQLException{
   
        try{
           Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "")) {
                Statement  stmt = con.createStatement();
                
                
                         String sql2 = "DELETE From schedules where Availability = '" + this.Availability.getText() + "'";
                    
                    stmt.executeUpdate(sql2);
                    
                    this.errorlabel2.setText("Schedule Removed");
                    
                    this.Availability.setText(null);
           
            }
            
       } catch (ClassNotFoundException | SQLException e){
           
            System.out.println(e);
            
       }
    }
    
}
