/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todotasklist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ToDoListWindowController implements Initializable {

    @FXML
    private ImageView menuIcon;
    @FXML
    private ImageView exitIcon;
    @FXML
    private Label menu;
    @FXML
    private Label exit;
    @FXML
    private ImageView TaskIcon;
    @FXML
    private ImageView AddIcon;
    @FXML
    private ImageView settingsIcon;
    @FXML
    private ImageView infIcon;
    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView TaskIcon1;
    @FXML
    private ImageView AddIcon1;
    @FXML
    private ImageView settingsIcon1;
    @FXML
    private ImageView infIcon1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Label tasks1;
    @FXML
    private Label add1;
    @FXML
    private Label settings1;
    @FXML
    private Label about1;
    @FXML
    private RadioButton allTask,upTask,todayTask;
   
    boolean s = false;
    boolean radio = false;
    @FXML
    private AnchorPane RadioTaskpane;
     @FXML
    private AnchorPane todayTaskpane;
     @FXML
    private AnchorPane upTaskPane;
      @FXML
    private AnchorPane AllTaskPane;
     
    @FXML
    private ListView<String> todayListview;
    @FXML
    private ListView<String> upComingListview;
    @FXML
    private ListView<String> obj;
    List<TaskData> list = new ArrayList<>(); 
    List<String> info = new ArrayList<>();
    List<String> today = new ArrayList<>();
    List<String> up = new ArrayList<>();
     
   boolean all = true;
   boolean ups = false;
   boolean to =  false;
   
   // @FXML
   // ListView<TaskData> listView ;
     //FileWriter wr;
    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       exitIcon.setOnMouseClicked(event ->{
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Exit!");
           alert.setHeaderText("You're about to exit!!");
           alert.setContentText("Do you wan to exit without saving?: ");
           if(alert.showAndWait().get() == ButtonType.OK){
               System.exit(0);
           }
           
       });
       exit.setOnMouseClicked(event ->{
           FadeTransition exitTransition1 = new FadeTransition(Duration.seconds(0.5),exitIcon);
           exitTransition1.setFromValue(0);
           exitTransition1.setToValue(0.6);
           exitTransition1.play();
           
            FadeTransition exitLTransition1 = new FadeTransition(Duration.seconds(0.5),exit);
           exitLTransition1.setFromValue(0);
           exitLTransition1.setToValue(0.73);
           exitLTransition1.play();
           
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Exit!");
           alert.setHeaderText("You're about to exit!!");
           alert.setContentText("Do you wan to exit ?: ");
           if(alert.showAndWait().get() == ButtonType.OK){
               System.exit(0);
           }
           
       });
      
       pane1.setVisible(false);
       FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),pane1);
       fadeTransition.setFromValue(1);
       fadeTransition.setToValue(0);
       fadeTransition.play();
       
       TranslateTransition translateTransition = new TranslateTransition (Duration.seconds(0.5),pane2);
       translateTransition.setFromX(-600);
       translateTransition.play();
       
       TranslateTransition todayTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),todayTaskpane);
       todayTaskpaneTransition1.setFromY(-600);
       todayTaskpaneTransition1.play();
       
       TranslateTransition upTaskPaneTransition1 = new TranslateTransition (Duration.seconds(0.5),upTaskPane);
       upTaskPaneTransition1.setFromY(-600);
       upTaskPaneTransition1.play();
       
       TranslateTransition RadioTaskpaneTransition = new TranslateTransition (Duration.seconds(0.5),RadioTaskpane);
       RadioTaskpaneTransition.setByX(-800);
       RadioTaskpaneTransition.play();
       
       todayTaskpane.setVisible(false);
       FadeTransition todayTaskpaneTransition = new FadeTransition(Duration.seconds(0.5),todayTaskpane);
       todayTaskpaneTransition.setFromValue(1);
       todayTaskpaneTransition.setToValue(1);
       todayTaskpaneTransition.play();
       
       upTaskPane.setVisible(false);
       FadeTransition upTaskPaneTransition = new FadeTransition(Duration.seconds(0.5),upTaskPane);
       upTaskPaneTransition.setFromValue(1);
       upTaskPaneTransition.setToValue(1);
       upTaskPaneTransition.play();
       
       
     
    pane1.setOnMouseMoved(event ->{
            
       FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
       fadeTransition1.setFromValue(0.04);
       fadeTransition1.setToValue(0);
       fadeTransition1.play();
       
       TranslateTransition translateTransition1 = new TranslateTransition (Duration.seconds(0.5),pane2);
       TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),RadioTaskpane);
      if(s == true){
       translateTransition1.setFromX(0);
       translateTransition1.setToX(-600);
       translateTransition1.play();
       s =false;
       if(radio == true){
            RadioTaskpaneTransition1.setFromX(0);
            RadioTaskpaneTransition1.setToX(-800);
            RadioTaskpaneTransition1.play();
            radio =false;
       }
       
      }
     
       });
       
       
       menu.setOnMouseClicked(event ->{
          
       pane1.setVisible(true);
       FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
       fadeTransition1.setFromValue(0);
       fadeTransition1.setToValue(0.15);
       fadeTransition1.play();
       
       FadeTransition menuTransition1 = new FadeTransition(Duration.seconds(0.5),menuIcon);
       FadeTransition menuLTransition1 = new FadeTransition(Duration.seconds(0.5),menu);
       menuTransition1.setFromValue(0);
       menuLTransition1.setFromValue(0);
       menuTransition1.setToValue(0.36);
       menuLTransition1.setToValue(0.73);
       menuTransition1.play();
       menuLTransition1.play();
       
       TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),RadioTaskpane);
       
       
       TranslateTransition translateTransition1 = new TranslateTransition (Duration.seconds(0.5),pane2);
       if(s == false){
            translateTransition1.setFromX(-600);
            translateTransition1.setToX(0);
            translateTransition1.play();
            s = true;
       }
       else if(s == true){
            translateTransition1.setFromX(0);
            translateTransition1.setToX(-600);
            translateTransition1.play();
            s =false;
            if(radio == true){
            RadioTaskpaneTransition1.setFromX(0);
            RadioTaskpaneTransition1.setToX(-800);
            RadioTaskpaneTransition1.play();
            radio =false;
            }
      }
       });
     
       add1.setOnMouseMoved(event ->{
           
               FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1),add1);
               fadeTransition2.setFromValue(0.20);
               fadeTransition2.setToValue(.66);
               fadeTransition2.playFrom(Duration.ONE);
               
               FadeTransition addTransition = new FadeTransition(Duration.seconds(1),AddIcon1);
               addTransition.setFromValue(0.30);
               addTransition.setToValue(.55);
               addTransition.playFrom(Duration.ONE);
               TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),RadioTaskpane); 
              if(radio == true){
                RadioTaskpaneTransition1.setFromX(0);
                RadioTaskpaneTransition1.setToX(-800);
                RadioTaskpaneTransition1.play();
                radio =false;
            }
           });
       tasks1.setOnMouseMoved(event ->{
           
               FadeTransition taskTransition = new FadeTransition(Duration.seconds(1),tasks1);
               taskTransition.setFromValue(0.20);
               taskTransition.setToValue(.66);
               taskTransition.playFrom(Duration.ONE);
               
               FadeTransition taskLTransition = new FadeTransition(Duration.seconds(1),TaskIcon1);
               taskLTransition.setFromValue(0.20);
               taskLTransition.setToValue(.48);
               taskLTransition.playFrom(Duration.ONE);
           });
        tasks1.setOnMouseClicked(event ->{
            TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),RadioTaskpane);
            if(radio == false){
                RadioTaskpaneTransition1.setFromX(-800);
                RadioTaskpaneTransition1.setToX(0);
                RadioTaskpaneTransition1.play();
                radio = true;
            }
            else if(radio == true){
                RadioTaskpaneTransition1.setFromX(0);
                RadioTaskpaneTransition1.setToX(-800);
                RadioTaskpaneTransition1.play();
                radio =false;
            }
       });
       if( AllTaskPane.isVisible()){
       obj.setOnMouseClicked(event ->{
           
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskListDetails.fxml"));
                Parent root = loader.load();
                
                
                TaskListDetailsController tc = loader.getController();
                int x = obj.getSelectionModel().getSelectedIndex();
                tc.setIndex(x);
                fileRead();
                tc.setItemforDetails(list);
               
                
                
                
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex);
            }
          
           
       });
       }
        if( todayTaskpane.isVisible()){
        todayListview.setOnMouseClicked(event ->{
           
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskListDetails.fxml"));
                Parent root = loader.load();
                
                
                TaskListDetailsController tc = loader.getController();
                String y =  todayListview.getSelectionModel().getSelectedItem();
                int x = 1;
                while(!obj.getSelectionModel().isEmpty()){
                   if(y.contentEquals(obj.getSelectionModel().getSelectedItem())){
                       
                       tc.setIndex(x);
                        
                   }
                   else{
                       x++;
                       obj.getSelectionModel().select(x);
                   }
                }
              
                fileRead();
                tc.setItemforDetails(list);
               
                
                
                
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex);
            }
          
           
       });
        }
       if(upTaskPane.isVisible()){
       upComingListview.setOnMouseClicked(event ->{
           
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskListDetails.fxml"));
                Parent root = loader.load();
                
                
                TaskListDetailsController tc = loader.getController();
                int x =  upComingListview.getSelectionModel().getSelectedIndex();
                tc.setIndex(x);
                fileRead();
                tc.setItemforDetails(list);
               
                
                
                
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex);
            }
          
           
       });
       }
       settings1.setOnMouseMoved(event ->{
           
               FadeTransition settingsTransition = new FadeTransition(Duration.seconds(1),settings1);
               settingsTransition.setFromValue(0.20);
               settingsTransition.setToValue(.66);
               settingsTransition.playFrom(Duration.ONE);
               
               FadeTransition settingsLTransition = new FadeTransition(Duration.seconds(1),settingsIcon1);
               settingsLTransition.setFromValue(0.20);
               settingsLTransition.setToValue(.53);
               settingsLTransition.playFrom(Duration.ONE);
               
                TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),RadioTaskpane); 
              if(radio == true){
                RadioTaskpaneTransition1.setFromX(0);
                RadioTaskpaneTransition1.setToX(-800);
                RadioTaskpaneTransition1.play();
                radio =false;
            }
           });
        about1.setOnMouseMoved(event ->{
           
               FadeTransition aboutTransition = new FadeTransition(Duration.seconds(1),about1);
              aboutTransition.setFromValue(0.20);
               aboutTransition.setToValue(.66);
               aboutTransition.playFrom(Duration.ONE);
               
               FadeTransition aboutLTransition = new FadeTransition(Duration.seconds(1),infIcon1);
               aboutLTransition.setFromValue(0.20);
               aboutLTransition.setToValue(.55);
               aboutLTransition.playFrom(Duration.ONE);
               
                TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),RadioTaskpane); 
              if(radio == true){
                RadioTaskpaneTransition1.setFromX(0);
                RadioTaskpaneTransition1.setToX(-800);
                RadioTaskpaneTransition1.play();
                radio =false;
            }
           });
       add1.setOnMouseClicked(event ->{
           try {
               FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5),add1);
               fadeTransition2.setFromValue(0);
               fadeTransition2.setToValue(.66);
               fadeTransition2.play();
               
               FadeTransition addTransition = new FadeTransition(Duration.seconds(0.5),AddIcon1);
               addTransition.setFromValue(0);
               addTransition.setToValue(.55);
               addTransition.play();
              
               FXMLLoader loader = new FXMLLoader(getClass().getResource("addNewTask.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root);
 
               AddNewTaskController wc = loader.getController();
               fileRead();
               wc.setItem(list);
              //setItemAgain();
              
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.setTitle("Add New Task!!");
               stage.show();
               
               
              
           } catch (IOException ex) {
               //Logger.getLogger(ToDoListWindowController.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println(ex);
           }
           
       }  
);
      
     
    }
     public void fileWrite(List<TaskData> obj) throws IOException{
        FileWriter  wri = new FileWriter("TASK.txt");
       // PrintWriter w = new PrintWriter(wri);
         int size = obj.size();
       for(int i =0; i < size;i++){
             //w.append(list.get(i).sDate+" to "+list.get(i).dDate+" "+list.get(i).sTask+"\n");   
            wri.write/*println*/(obj.get(i).sDate+"\n");
            wri.write/*println*/(obj.get(i).dDate+"\n");
            wri.write/*println*/(obj.get(i).sTask+"\n");
            wri.write/*println*/(obj.get(i).lTask+"\n");           
            }
            //w.close();
            wri.close();
    }
     public void fileRead() throws NullPointerException, IOException{
         String sDate ="";
         String dDate ="";
         String lTask ="";
         String sTask = "";
         BufferedReader reader = new  BufferedReader(new FileReader("TASK.txt"));
        
        
        while((sDate = reader.readLine()) != null){
            dDate = reader.readLine();
            sTask = reader.readLine();
            lTask = reader.readLine();
            
            TaskData obj1 = new TaskData(sDate,dDate,sTask,lTask);
            list.add(obj1);
        }
        reader.close();
     }
    public void setItemAgain(List<TaskData> list) throws IOException {
      
     // List<TaskData> list = new ArrayList<>();
     
        obj.getSelectionModel().selectAll();
        ObservableList<Integer> indices = obj.getSelectionModel().getSelectedIndices();
        for(int index : indices) {
            obj.getSelectionModel().getSelectedItems().remove(index);
        }
       
         for(int i=0; i<list.size();i++){
        info.add(list.get(i).sDate+" to "+list.get(i).dDate+" "+list.get(i).sTask);
        if(LocalDate.parse(list.get(i).dDate).toEpochDay() >= LocalDate.now().toEpochDay() && LocalDate.parse(list.get(i).sDate).toEpochDay() <= LocalDate.now().toEpochDay())
            today.add(list.get(i).sDate+" to "+list.get(i).dDate+" "+list.get(i).sTask);
        if(LocalDate.parse(list.get(i).sDate).toEpochDay() > LocalDate.now().toEpochDay())
            up.add(list.get(i).sDate+" to "+list.get(i).dDate+" "+list.get(i).sTask);
       } 
       
       obj.getItems().addAll(info);  
       todayListview.getItems().addAll(today);
       upComingListview.getItems().addAll(up);
       
    
   }
    public void showItemAgain() throws IOException {
        list.clear();
        obj.getSelectionModel().selectAll();
        ObservableList<Integer> indices = obj.getSelectionModel().getSelectedIndices();
        for(int index : indices) {
            obj.getSelectionModel().getSelectedItems().remove(index);
        }
        
       fileRead();
       
       for(int i=0; i<list.size();i++){
        info.add(list.get(i).sDate+" to "+list.get(i).dDate+" "+list.get(i).sTask);
        if(LocalDate.parse(list.get(i).dDate).toEpochDay() >= LocalDate.now().toEpochDay() && LocalDate.parse(list.get(i).sDate).toEpochDay() <= LocalDate.now().toEpochDay())
            today.add(list.get(i).sDate+" to "+list.get(i).dDate+" "+list.get(i).sTask);
        if(LocalDate.parse(list.get(i).sDate).toEpochDay() > LocalDate.now().toEpochDay())
            up.add(list.get(i).sDate+" to "+list.get(i).dDate+" "+list.get(i).sTask);
       } 
       
       obj.getItems().addAll(info);  
       todayListview.getItems().addAll(today);
       upComingListview.getItems().addAll(up);
      
    
   }
    @FXML
    void ShowTaskPane(ActionEvent event) {
        if(allTask.isSelected()){
            todayTaskpane.setVisible(false);
            upTaskPane.setVisible(false);
            AllTaskPane.setVisible(true);
            
           TranslateTransition todayTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),todayTaskpane);
           if(to){
                todayTaskpaneTransition1.setFromY(0);
                todayTaskpaneTransition1.setToY(-600);
                todayTaskpaneTransition1.play();
                to = false;
           }
           
           TranslateTransition upTaskPaneTransition1 = new TranslateTransition (Duration.seconds(0.5),upTaskPane);
           if(ups){
           upTaskPaneTransition1.setFromY(0);
           upTaskPaneTransition1.setToY(-600);
           upTaskPaneTransition1.play();
           ups = false;
           }
          TranslateTransition AllTaskPaneTransition1 = new TranslateTransition (Duration.seconds(0.5),AllTaskPane);
          if(!all)
          {
           AllTaskPaneTransition1.setFromY(-600);
           AllTaskPaneTransition1.setToY(0);
           AllTaskPaneTransition1.play();
           all = true;
          }
        }
        else if(upTask.isSelected()){
            todayTaskpane.setVisible(false);
            upTaskPane.setVisible(true);
            AllTaskPane.setVisible(false);
            TranslateTransition AllTaskPaneTransition1 = new TranslateTransition (Duration.seconds(0.5),AllTaskPane);
            TranslateTransition todayTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),todayTaskpane);
            TranslateTransition upTaskPaneTransition1 = new TranslateTransition (Duration.seconds(0.5),upTaskPane);
           if(all){
            AllTaskPaneTransition1.setFromY(0);
            AllTaskPaneTransition1.setToY(-600);
            AllTaskPaneTransition1.play(); 
            all = false;
           }
          
           if(to){
             todayTaskpaneTransition1.setFromY(0);
             todayTaskpaneTransition1.setToY(-600);
             todayTaskpaneTransition1.play();
             to = false;
           }
           
          if(!ups)
          {
           upTaskPaneTransition1.setFromY(-600);
           upTaskPaneTransition1.setToY(0);
           upTaskPaneTransition1.play();
           ups = true;
          }
        }
        else if(todayTask.isSelected()){
            todayTaskpane.setVisible(true);
            upTaskPane.setVisible(false);
            AllTaskPane.setVisible(false);
            
            TranslateTransition AllTaskPaneTransition1 = new TranslateTransition (Duration.seconds(0.5),AllTaskPane);
            TranslateTransition todayTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),todayTaskpane);
            TranslateTransition upTaskPaneTransition1 = new TranslateTransition (Duration.seconds(0.5),upTaskPane);
           if(all){
            AllTaskPaneTransition1.setFromY(0);
            AllTaskPaneTransition1.setToY(-600);
            AllTaskPaneTransition1.play(); 
            all = false;
           }
           
          
           if(ups){
           upTaskPaneTransition1.setFromY(0);
           upTaskPaneTransition1.setToY(-600);
           upTaskPaneTransition1.play();
           ups = false;
           }
          if(!to)
          {
           todayTaskpaneTransition1.setFromY(-600);
           todayTaskpaneTransition1.setToY(0);
           todayTaskpaneTransition1.play();
           to = true;
          }
        }
            
    }
    
}
