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
public class MedicalHistory {
    private final DailyState dailyState = new DailyState();
    private final PhysicalState physicalState = new PhysicalState();
    private int medical_number ;
    private double bodyTemperature;
    private double pulse;
    private double idleTime;
    private double shakingCount;
    private double roomtemperature;

    public DailyState getDailyState() {
        return dailyState;
    }

    public PhysicalState getPhysicalState() {
        return physicalState;
    }

    public int getMedical_number() {
        return medical_number;
    }
    
    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public double getShakingCount() {
        return shakingCount;
    }

    public double getRoomtemperature() {
        return roomtemperature;
    }
    

    public double getPulse() {
        return pulse;
    }

    public double getIdleTime() {
        return idleTime;
    }

    public void setMedical_number() {
        this.medical_number = (int)(Math.random()*(10000-1000+1)+1000);
    }
    
    
    public boolean record(double bodyTemperature,double Pulse,double shakingCount,double roomtemperature,double idleTime){//傳入資料
        //record data if annormal break;
        boolean normalState = true;
        //set info
        this.setMedical_number();
        physicalState.setTemperature(bodyTemperature);   //record bodyTemperature
        physicalState.setPulse(Pulse);                  //record Pulse
        physicalState.setShakingCount(shakingCount);    //record shakingCount
        dailyState.setRoomtemperature(roomtemperature);//record roomtemperature
        dailyState.setIdleTime(idleTime);           //record idleTime
        dailyState.setdailyStateNumber();           //record dailyState number
        physicalState.setphysicalStateNumber();    //record physicalState number
        WristBandGUI.displaMessage("Tracking your data");
        //get info
        this.bodyTemperature = physicalState.getTemperature();
        this.pulse = physicalState.getPulse();
        this.idleTime = dailyState.getIdleTime();
        this.shakingCount = physicalState.getShakingCount();
        this.roomtemperature = dailyState.getRoomtemperature();
        this.shakingCount = physicalState.getShakingCount();
        System.out.printf("bodyTemperature is %.2f oc\npulse is %.2f mmHg\nidleTime is %.2f hr\nroomtemperature is %.2foc\nshakingCount is %.2f per second\n",bodyTemperature,pulse,idleTime,roomtemperature,shakingCount);
        //detectAbnormal
        if(detectAbnormal(roomtemperature,idleTime,shakingCount,bodyTemperature)){
            WristBandGUI.displaMessage("Detect abnormal, system will go into emergency situation~~~");
            normalState = false;//detect set state false
        }else{
            WristBandGUI.displaMessage("Data is normal, keep tracking~~~\n\n\n");//keep tracking
        }
        return normalState;
    }
    //detectAbnormal function
    private boolean detectAbnormal(double roomTemperature,double idleTime,double shakingCount,double bodytemperature){//send roomTemperature,idleTime,shakingCount
  
        if((bodytemperature <= 30 && bodytemperature >= 45) || roomTemperature >= 150 ||idleTime >= 100 ||shakingCount >= 3){
            return true;
        }
        return false;
    }
}
