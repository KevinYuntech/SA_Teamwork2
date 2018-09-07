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
public class PhysicalState {
    private int physicalStateNumber;//initial key
    private double Temperature ;//Temperature store
    private double Pulse ;      //Pulse store
    private double ShakingCount ;//ShakingCount store
    
    //get last data
    public int getphysicalStateNumber(){
        return physicalStateNumber;
    }
    
    public double getShakingCount() {
        return ShakingCount;
    }

    public double getTemperature() {
        return Temperature;
    }

    public double getPulse() {
        return Pulse;
    }
    //add new data
    public void setShakingCount(double shakingCount) {
        this.ShakingCount = shakingCount;
    }
    
    public void setTemperature(double temperature) {
        this.Temperature = temperature;
    }

    public void setPulse(double pulse) {
        this.Pulse = pulse;
    }
    
    public void setphysicalStateNumber(){
        this.physicalStateNumber=  (int)(Math.random()*(1000-100+1)+100);
    }

}
