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

public class Admin_Reply_DoctorController implements Initializable {

    @FXML
    private Button BackBtn;
    @FXML
    private Button SubmitBtn;
    @FXML
    private TextField doctorID;
    @FXML
    private TextArea text;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     
   public void BackBtnClicked(ActionEvent ev) throws SQLException, IOException{
     
        try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Admin_Message_Handler.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Messages");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
   public void SubmitBtnClicked(ActionEvent ev) throws SQLException, IOException{
       
       
         try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
         String sql = "select * from doctorinfo";
         
         ResultSet rs = stmt.executeQuery(sql);
         
         Boolean state = false;
         
         while(rs.next()){
             
             if(rs.getString(1).equalsIgnoreCase(this.doctorID.getText())){
                 state = true;
                 
             }
             
         }
         
         
         if(state == true){
             
             String sqlalter = "UPDATE `doctorinfo` SET `MessageFromAdmin` = '" + this.text.getText() + "'" + "WHERE `DoctorID` =  " + "'" + this.doctorID.getText() + "'";
             
             
             stmt.executeUpdate(sqlalter);
             
             try {
           
            Parent parent = FXMLLoader.load(getClass().getResource("Admin_FunctionsSelection.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
            
            
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
             
         }else{
             this.errorLabel.setText("Wrong Doctor ID");
             this.doctorID.setText(null);
         }
         
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
       
       
   }
   
}
