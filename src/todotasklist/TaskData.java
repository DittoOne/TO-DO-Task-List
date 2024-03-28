/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todotasklist;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class TaskData {
    String sDate,dDate;
    String sTask,lTask;

    public TaskData() {
    }

    public TaskData(String sDate, String dDate, String sTask, String lTask) {
        this.sDate = sDate;
        this.dDate = dDate;
        this.sTask = sTask;
        this.lTask = lTask;
    }
    
    
}
