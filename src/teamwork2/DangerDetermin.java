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
public class DangerDetermin {
    public String identify(double bodytempature,double idleTime,double shakeCount,double roomteamerature){
        String situation = new String();
        if(((bodytempature <= 45&&bodytempature >= 30) && shakeCount >= 3)||bodytempature <= 30){
            situation =  "drowning";
        }
        else if(roomteamerature >= 150){
            situation = "firing";
        }
        else if(idleTime >= 100){//>=100hr
            situation = "moutainAccident";
        }
        return situation;
    } 
}
