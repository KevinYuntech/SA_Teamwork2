/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamwork2;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class AppPageUi {
   DBserverListener DBserverListener = new DBserverListener();
    Scanner scanner = new Scanner(System.in);
    //login 
    public void login(user currentUser){
        String userId,password;
        //this.currentUser = currentUser;
        scanner = new Scanner(System.in);
                  WristBandGUI.displaMessage("Please log in  account\n");
                  WristBandGUI.displaMessage("Please input account:");
                  userId = scanner.next();
                  WristBandGUI.displaMessage("Please input password:");
                 password = scanner.next();
                 boolean result = currentUser.confirm(userId, password);
                if(result){
                    currentUser.connect();                                   //connect to device
                    return;
                 }
                else{
                    this.handleLoginError(currentUser);
                }
        
    }
    //handleLoginError
    void handleLoginError(user currentUser){
        while(true){
           WristBandGUI.displaMessage("Account or password is error, please input account again(1) or sign up(2)");
           String select = scanner.next();
           switch(select){
               case "1":
                   this.login(currentUser);
                   return;
               case "2":
                   this.signup(currentUser);
                   return;
               default:
                   WristBandGUI.displaMessage("Please input 1 or 2");
                   break;
           }
        }
    }
    //sign up
    public void signup(user currentUser){
        this.fillpersonalInformation(currentUser);
        this.login(currentUser);
    }
    //fill personal info
    public void fillpersonalInformation(user currentUser){
        String userId,password,emergencyContactPerson,addressNumber,address;

        WristBandGUI.displaMessage("First use, please signup account\n");
        WristBandGUI.displaMessage("Please input account:");
       userId = scanner.next();
       WristBandGUI.displaMessage("Please input password:");
       password = scanner.next();
        WristBandGUI.displaMessage("Please input emergencyContactPerson:");
       emergencyContactPerson = scanner.next();
       WristBandGUI.displaMessage("Please input emergencyContactPerson phone Number:");
       addressNumber = scanner.next();
       WristBandGUI.displaMessage("Please input address:");
       address = scanner.next();
       currentUser.record(userId, password,emergencyContactPerson,addressNumber,address);    //record new data in userDB
       WristBandGUI.displaMessage("Signup sucessfully\n") ;
       DBserverListener.sendDataToMySQLServer(currentUser);
    }
}
