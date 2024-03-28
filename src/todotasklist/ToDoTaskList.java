/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todotasklist;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
/**
 *
 * @author User
 */
public class ToDoTaskList extends Application {
    
    List<TaskData> list = new ArrayList<>();
    double x,y =0;
    @Override
    public void start(Stage stage) throws Exception {
    try{
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("toDoListWindow.fxml"));
        Parent root = loader.load();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        ToDoListWindowController sc = loader.getController();
       
        //sc.showItemAgain();
       
        sc.showItemAgain();
       
        root.setOnMousePressed(event ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });
         root.setOnMouseDragged(event ->{
           stage.setX( event.getSceneX() - x);
           stage.setY(event.getSceneY()-y);
        });
        stage.setScene(scene);
        stage.show();
    }catch(Exception ex){
        System.out.println(ex);
    }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
