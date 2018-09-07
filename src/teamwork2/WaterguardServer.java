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
public class WaterguardServer implements RescueTeamServer{
    private final String name = "waterguard";
    @Override//implements checkMsg
    public boolean checkMsg() {
        boolean confirm = true; //select by server
        if(confirm){
            WristBandGUI.displaMessage(this.name+" confirm");
            return true;
        }
        else{
            WristBandGUI.displaMessage(this.name+" doesn't confirm");
            return false;
        }
    }
}
