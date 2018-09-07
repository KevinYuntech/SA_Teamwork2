/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamwork2;


/**
 *
 * @author User
 */
public class DailyState {
    private int dailyStateNumber ;//primary key initial = 0
    private double IdleTime = 0;//idleTime store
    private double Roomtemperature;//roomtemperature store



    //get last
    public int getdailyStateNumber(){
        return dailyStateNumber;
    }
    
    public double getRoomtemperature() {
        return Roomtemperature;
    }
   
    public double getIdleTime() {
        return this.IdleTime;
    }
    
    public void setRoomtemperature(double roomtemperature) {
        this.Roomtemperature = roomtemperature;
    }
    
    public void setIdleTime(double idleTime) {
        this.IdleTime = idleTime;
    }
    
    public void setdailyStateNumber(){
        this.dailyStateNumber=  (int)(Math.random()*(1000-100+1)+100);
    }
    
}
