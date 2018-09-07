/*
Written by Kevin
 */
package teamwork2;
import java.util.Scanner;
public class TeamWork2 {
    //I add stamp type on the checkMsg method of EmergencyContactPersonServer class
    //Modified class name modified to uppercase except the user class
    //Modified gps class and add gps data in the system class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WristBandSystem wbs = new WristBandSystem();
        user currentUser = new user(wbs);
        wbs.addUser(currentUser);
       // String userId = "yuntech",  password = "12345";
        AppPageUi appui = wbs.connect();
        WristBandGUI.displaMessage("Please select login(1) or sign up(2)");
        String selection = scanner.next();
        
        Loop:outer:
        while(true){
        switch(selection){
            case"1":
                appui.login(currentUser);
                break outer;
            case"2":
                appui.signup(currentUser);
                break outer;
            default:
                WristBandGUI.displaMessage("Input error, please input login(1) or sign up(2) again");
                selection = scanner.next();
                        
               break;
        }
        }
 
        // start recording
        boolean normalState = true;
        //currentUser.pressEmergencyButton();//press button
        while(normalState == true){
            //normalState = wbs.Recording(37, 120, 1, 30, 50);//test manually
            normalState = wbs.Recording(Math.random()*(45-30+1)+30, Math.random()*(120-80+1)+80, Math.random()*3+1, Math.random()*(200-25+1)+25, Math.random()*(150-0+1)+0);//send bodyTemperature,pulse,shakingCount,roomtemperature idleTime
        }
        
    }
}
