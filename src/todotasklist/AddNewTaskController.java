/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todotasklist;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.TemporalQueries.zone;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.print.attribute.Size2DSyntax.MM;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddNewTaskController implements Initializable {

    @FXML
    private ImageView backIcon;
    @FXML
    private Label back;
    @FXML
    private TextField newTask;
    @FXML
    private TextArea taskDetails;
    @FXML
    private DatePicker deadlineDate;
    @FXML
    private DatePicker fromDate;
    @FXML
    private ImageView saveIcon;
    @FXML
    private Label save;
    LocalDate fdate,ddate;
    List<TaskData> tList = new ArrayList<>();
     List<TaskData> list = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
       back.setOnMouseMoved(event ->{
           
               FadeTransition backTransition = new FadeTransition(Duration.seconds(1),back);
               backTransition.setFromValue(0.20);
               backTransition.setToValue(.76);
               backTransition.playFrom(Duration.ONE);
               
               FadeTransition backIconTransition = new FadeTransition(Duration.seconds(1),backIcon);
               backIconTransition.setFromValue(0.20);
               backIconTransition.setToValue(.38);
               backIconTransition.playFrom(Duration.ONE);
           });
        save.setOnMouseMoved(event ->{
           
               FadeTransition saveTransition = new FadeTransition(Duration.seconds(1),save);
               saveTransition.setFromValue(0.20);
               saveTransition.setToValue(.78);
               saveTransition.playFrom(Duration.ONE);
               
               FadeTransition saveLTransition = new FadeTransition(Duration.seconds(1),saveIcon);
               saveLTransition.setFromValue(0.20);
               saveLTransition.setToValue(.8);
               saveLTransition.playFrom(Duration.ONE);
           });
        back.setOnMouseClicked(event ->{
           try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("toDoListWindow.fxml"));
              Parent root = loader.load();
              Scene scene = new Scene(root);
              ToDoListWindowController sc = loader.getController();
             
             
            // sc.setItemAgain(tList);
             sc.showItemAgain();
              
              Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
           } catch (IOException ex) {
               //Logger.getLogger(ToDoListWindowController.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println(ex);
           }
           
       }  
);
        save.setOnMouseClicked(event ->{
          try{
             String startDate = fdate.toString();
             String deadLine = ddate.toString();
             String task = newTask.getText().trim();
             String taskDetail = taskDetails.getText().trim().replaceAll("\n", " ");
             if(startDate == "" ||deadLine == "" ||task == "" ||taskDetail == ""){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("input is invalid");
                alert.setHeaderText("Warning!!");
                alert.setContentText("Atleast One of your input is empty or invalid!!");
                alert.showAndWait();
             }
             else{
                TaskData nItems = new TaskData(startDate,deadLine,task,taskDetail); 
                tList.add(nItems);
                System.out.println(tList.size());
              try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("toDoListWindow.fxml"));
              Parent root = loader.load();
              Scene scene = new Scene(root);
              ToDoListWindowController sc = loader.getController();
                  
              sc.setItemAgain(tList);
              
              //sc.showItemAgain();
              
              sc.fileWrite(tList);  
               
             
              Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
           } catch (IOException ex) {
               
               System.out.println(ex);
           }
             
             }
          }catch(NullPointerException ex){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("input is invalid");
                alert.setHeaderText("Warning!!");
                alert.setContentText("Atleast One of your input is empty or invalid!!");
                alert.showAndWait();   
                     
          }
              
        });
      
                }
     @FXML
    void deadlineDateSet(ActionEvent event) {
       try{ 
         if(deadlineDate.getValue().toEpochDay() < fromDate.getValue().toEpochDay() || deadlineDate.getValue().toEpochDay() < LocalDate.now(/*zone*/).toEpochDay()||fromDate.getValue().equals(null))
         { Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("deadline input is invalid");
           alert.setHeaderText("Warning!!");
           alert.setContentText("Your deadline input is less than starting Date");
           alert.showAndWait();
         }
         else{
             ddate = deadlineDate.getValue();
            //System.out.println(ddate);
         }
       }catch(NullPointerException e){
            fromDate.setValue(LocalDate.now(/*zone*/));
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("startDate input is empty");
            alert1.setHeaderText("Warning!!");
            alert1.setContentText("Using today's day as your starting date input!!");
            alert1.showAndWait();
             if(deadlineDate.getValue().toEpochDay() < fromDate.getValue().toEpochDay() || deadlineDate.getValue().toEpochDay() < LocalDate.now(/*zone*/).toEpochDay())
                { 
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("deadline input is invalid");
                  alert.setHeaderText("Warning!!");
                  alert.setContentText("Your deadline input is less than starting Date");
                  alert.showAndWait();
                }
            else{
                ddate = deadlineDate.getValue();
            //System.out.println(ddate);
         }
       }
       catch(Exception e){
                 System.out.println(e);
                 }
    }

    @FXML
    void fromDateSet(ActionEvent event) {
        try{
        if(LocalDate.now(/*zone*/).toEpochDay() > fromDate.getValue().toEpochDay() )
         { Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Starting date input is invalid");
           alert.setHeaderText("Warning!!");
           alert.setContentText("Your Starting Date input is less than today's date");
           alert.showAndWait();
         }
         else{
             fdate = fromDate.getValue();
           // System.out.println(fdate);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
     public void setItem(List<TaskData> l) throws NullPointerException, IOException {
         
         //sc.fileRead();
         tList = l     ; 
        
        
    }
    
}
