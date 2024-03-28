/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todotasklist;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TaskListDetailsController implements Initializable {

    @FXML
    private ImageView backIcon;
    @FXML
    private Label back;
    @FXML
    private TextField sDateTextField;
    @FXML
    private TextField dDateTextField;
    @FXML
    private TextField sTaskTextField;
    @FXML
    private TextField taskDetailTextField;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private Label delete;
    @FXML
    List<TaskData> list = new ArrayList<>();
   
    int x;
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
          back.setOnMouseClicked(event ->{
           try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("toDoListWindow.fxml"));
              Parent root = loader.load();
              Scene scene = new Scene(root);
              ToDoListWindowController sc = loader.getController();
             // sc.setItemAgain(list);
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
          delete.setOnMouseMoved(event ->{
           
               FadeTransition deleteTransition = new FadeTransition(Duration.seconds(1),delete);
               deleteTransition.setFromValue(0.20);
               deleteTransition.setToValue(.76);
               deleteTransition.playFrom(Duration.ONE);
               
               FadeTransition deleteIconTransition = new FadeTransition(Duration.seconds(1),deleteIcon);
               deleteIconTransition.setFromValue(0.20);
               deleteIconTransition.setToValue(.38);
               deleteIconTransition.playFrom(Duration.ONE);
           });
          delete.setOnMouseClicked(event ->{
            
           try {
             
              FXMLLoader loader = new FXMLLoader(getClass().getResource("toDoListWindow.fxml"));
              Parent root = loader.load();
              Scene scene = new Scene(root);
              ToDoListWindowController sc = loader.getController();
              
              //sc. fileRead();
              
              deleteTask(); 
             
              sc.setItemAgain(list);
              //sc.showItemAgain();
              sc.fileWrite(list);
              
              
              
              Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
           } catch (IOException ex) {
               //Logger.getLogger(ToDoListWindowController.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println(ex);
                }
           
            }  
        );
        
    }
    public void setIndex(int x){
        this.x=x;
    }
    
    public void setItemforDetails(List<TaskData> l) {
        
         list = l;
        
         sDateTextField.setText(list.get(x).sDate.toString());
         dDateTextField.setText(list.get(x).dDate.toString());
         sTaskTextField.setText(list.get(x).sTask.trim());
         taskDetailTextField.setText(list.get(x).lTask.trim()); 
    }
    

     @FXML
    public void deleteTask() { 
         list.remove(x);
    }
    
}
