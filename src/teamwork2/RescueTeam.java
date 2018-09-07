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
public class RescueTeam {
       private RescueTeamServer rescueTeamServer;
       //use flag to discriminate which rescueTeam Server to be assigned, use for auto
        public boolean notifyEmergency(String flag){
            if(flag.equals("waterguard")){
                rescueTeamServer = new WaterguardServer();
            }
            else if(flag.equals("firefighter")){
                rescueTeamServer = new FirefighterServer();
            }
            else if(flag.equals("moutainguard")){
                rescueTeamServer = new MoutainguardServer();
            }
            return rescueTeamServer.checkMsg();
        }
        //overloading notifyEmergency use for manual
         public boolean notifyEmergency(user currentUser){
            rescueTeamServer = currentUser.getEcps();//get emergencyContactPersonServer
            return ((EmergencyContactPersonServer)rescueTeamServer).checkMsg(currentUser);
        }
}
