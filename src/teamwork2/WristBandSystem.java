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
public class WristBandSystem {
    //define attribute
    private final MedicalHistory mh = new MedicalHistory();
    private final AppPageUi appui = new AppPageUi();
    private final DangerDetermin dangerDetermin = new DangerDetermin();
    private boolean sucessfullornot = false;
    private final RescueTeam rescueTeam = new RescueTeam();
    private user currentUser; 
    private DBserverListener DBserverListener = new DBserverListener();
    private GPS gpsAPI = new GPS();            //modified to save gps locaion to system
    private String currentLocation;             //system store the gps data
    public void addUser(user currentUser){
        this.currentUser = currentUser;
    }
    //connect
    public AppPageUi connect(){ //
        WristBandGUI.displaMessage("System start!!");
        WristBandGUI.displaMessage("please loging!!");
        return appui;
    }
    
    //start to recording 
    public boolean Recording(double bodytemperature,double pulse,double shakingCount,double roomtemperature,double idleTime){
        boolean normalState = true;//define normal state
        normalState = mh.record(bodytemperature,pulse,shakingCount,roomtemperature,idleTime);  //send bodyTemperature,pulse,shakingCount,roomtemperature idleTime
        DBserverListener.sendRecordingDataToMySQLServer(this);//send recording data

        if(currentUser.pressEmergencyButton(currentUser)){
            DBserverListener.sendSystemDatatoMySQLServer(this);//send system data to server
            return false;
        }
        if(normalState == true){
            return true;//Date is normal, keep tracking~~
        }else {
        currentLocation = gpsAPI.locateCurrentPosition();       //MODIFIED locate position
        WristBandGUI.displaMessage(currentLocation);
            try{
                String situation = dangerDetermin.identify(mh.getBodyTemperature(),mh.getIdleTime(),mh.getShakingCount(),mh.getRoomtemperature());//identify situation
                
                String tmp = "Discriminate situation is "+situation;
                WristBandGUI.displaMessage(tmp);
                    if(situation.equals("")){
                       throw (new IdentifyException());
                    }
                this.notifyRescueTeam(situation);//finish notify
                WristBandGUI.displaMessage("Notify sucessfully");
              }catch(IdentifyException e){
                  WristBandGUI.displaMessage(e.getMessage());
                  return false;
              }
        normalState  = false;           //return normalState is false
        }
        DBserverListener.sendSystemDatatoMySQLServer(this);//send system data
        return normalState;
    }
    //use for auto
    public void notifyRescueTeam(String situation){
        WristBandGUI.displaMessage("Ready to notify");
            if(situation.equals("drowning")){                           //select which notify rescue team
            while(sucessfullornot == false){
                String flag = "waterguard";
                sucessfullornot = rescueTeam.notifyEmergency(flag);
            }
        }
        else if(situation.equals("firing")){
            while(sucessfullornot == false){
             String flag = "firefighter";
                sucessfullornot = rescueTeam.notifyEmergency(flag);
            }
        }
        else if(situation.equals("moutainAccident")){
            while(sucessfullornot == false){
             String flag = "moutainguard";
                sucessfullornot = rescueTeam.notifyEmergency(flag);
            }
        }
    }
    //overolading notifyRescueTeam use for manual
    public void notifyRescueTeam(user currentUser){
        WristBandGUI.displaMessage("Ready to notify "+currentUser.getEmergencyContactPerson()+" person");
            while(sucessfullornot == false){
                sucessfullornot = rescueTeam.notifyEmergency(currentUser);
            }
        WristBandGUI.displaMessage("Notify sucessfully");
    }

    public MedicalHistory getMh() {
        return mh;
    }

    public int isSucessfullornot() {
        if(sucessfullornot == true){
            return 1;
        }
        return 0;
    }

    public user getCurrentUser() {
        return currentUser;
    }
    

}
