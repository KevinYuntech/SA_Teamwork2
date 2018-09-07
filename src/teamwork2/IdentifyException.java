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
public class IdentifyException extends Exception{
    @Override
    public String getMessage(){
       return "Sorry, the system can't identify situation, please use emergency button to contact";
    }
}
