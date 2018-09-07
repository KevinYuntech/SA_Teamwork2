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
public class user {
    private WristBandSystem wbs ;
    public  int account = (int)(Math.random()*(1000-100+1)+100);//primary key
    private String userName = "Kevin";
    private String password  = "12345";
    private String emergencyContactPersonNumber = "default";
    private String address = "Dream Mall";
    private String emergencyContactPerson = "default";
    //get last
    public int getAccount() {
        return account;
    }

    public  String getemergencyContactPersonNumber() {
        return emergencyContactPersonNumber;
    }

    public  String getAddress() {
        return address;
    }
        
    public  void setemergencyContactPersonNumber(String emergencyContactPersonNumber) {
        this.emergencyContactPersonNumber = emergencyContactPersonNumber;
    }

    public void setAccount(int account) {
        this.account = account;
    }
    
    public  void setAddress(String address) {
        this.address = address;
    }
    
    
    private EmergencyContactPersonServer ecps;
    
    public user(WristBandSystem wbs){
        this.wbs = wbs;
         ecps = EmergencyContactPersonServer.getemergencyContactPersonServer();
    }

    public EmergencyContactPersonServer getEcps() {
        return ecps;
    }

    public void setEcps(EmergencyContactPersonServer ecps) {
        this.ecps = ecps;
    }
    
     //connect to this device
    public void connect(){//none
        //cellpone.connect();
        WristBandGUI.displaMessage("Connect system sucessfully");
    }
    //press EmergencyButton over 5 times
    public boolean pressEmergencyButton(user currentUser){
       double count = Math.random()*(5-0+1)+1; //define count
       //if count >= 5 active notify function
       if(count >= 5){                          
        WristBandGUI.displaMessage("You press emergency button over 5 times\nThe system will notify your emergency contact person");
        wbs.notifyRescueTeam(currentUser);
        return true;
       }
       else{
           return false;
       }
    }

    public void updateInformation(){ //sync user info
        
    }

    
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmergencyContactPerson() {
        return emergencyContactPerson;
    }

    public void setEmergencyContactPerson(String emergencyContactPerson) {
        this.emergencyContactPerson = emergencyContactPerson;
        ecps.setName(emergencyContactPerson);
    }
    
    
    
    public void record(String userName,String password,String emergencyContactPerson,String emergencyContactPersonNumber,String address){
        this.userName = userName;
        this.password = password;
        this.emergencyContactPerson = emergencyContactPerson;
        this.emergencyContactPersonNumber = emergencyContactPersonNumber;
        this.address = address;
    }
    public boolean confirm(String userName,String password){
        if(userName.equals(this.userName) && password.equals(this.password)){
            return true;
        }
        else{
            return false;
        }
    }
}
