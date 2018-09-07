/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamwork2;

//use for communicating with MySQL

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBserverListener {
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    
     //insert user data
    public void  sendDataToMySQLServer(user currentUser){
        String driver = "com.mysql.jdbc.Driver"; 
        String url = "jdbc:mysql://localhost:3306/sa"; 
        String user = "root"; 
        String password = ""; 
        try { 
            Class.forName(driver); 
            conn = (Connection) DriverManager.getConnection(url,user, password);       
        } 
        catch(ClassNotFoundException e) { 
            System.out.println("can't find driver"); 
            e.printStackTrace(); 
        } 
        catch(SQLException e) { 
            e.printStackTrace(); 
        }
      
        int account = currentUser.getAccount();
        String userpassword = currentUser.getPassword();
        String username = currentUser.getUserName();
        String contact_phone = currentUser.getemergencyContactPersonNumber();
        String contact_person = currentUser.getEmergencyContactPerson();
        String address = currentUser.getAddress();
        this.insertuserData(account,userpassword,username,contact_phone,contact_person,address);
        
         try {
            conn.close();   //close SQL
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    //insert recording data
    public  void sendRecordingDataToMySQLServer(WristBandSystem wrist){
        String driver = "com.mysql.jdbc.Driver"; 
        String url = "jdbc:mysql://localhost:3306/sa"; 
        String user = "root"; 
        String password = ""; 
        try { 
            Class.forName(driver); 
            conn = (Connection) DriverManager.getConnection(url,user, password);       
        } 
        catch(ClassNotFoundException e) { 
            System.out.println("can't find driver"); 
            e.printStackTrace(); 
        } 
        catch(SQLException e) { 
            e.printStackTrace(); 
        }
        user currentUser = wrist.getCurrentUser();
        MedicalHistory medicalHistory = wrist.getMh();
        DailyState DailyState = medicalHistory.getDailyState();
        PhysicalState PhysicalState = medicalHistory.getPhysicalState();
        //insert daily data
        int dailyNumber = DailyState.getdailyStateNumber();
        double idleTime =DailyState.getIdleTime();
        double roomTemperature = DailyState.getRoomtemperature();
        this.insertDailyStateData(dailyNumber,idleTime,roomTemperature);
        //insert physical data
        int physicalNumber =PhysicalState.getphysicalStateNumber();
        double bodyTemperature = PhysicalState.getTemperature();
        double pulse = PhysicalState.getPulse();
        double shakeCount = PhysicalState.getShakingCount();
        this.insertPhysicalStateData(physicalNumber, bodyTemperature, pulse, shakeCount);

        //insert medical data
        int medicalNumber = medicalHistory.getMedical_number();
        int account = currentUser.getAccount();
        this.insertMedicalStateData(medicalNumber,account,physicalNumber,dailyNumber);
        try {
            conn.close();   //close SQL
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //send system data
    public void sendSystemDatatoMySQLServer(WristBandSystem wrist){
        
        String driver = "com.mysql.jdbc.Driver"; 
        String url = "jdbc:mysql://localhost:3306/sa"; 
        String user = "root"; 
        String password = ""; 
        try { 
            Class.forName(driver); 
            conn = (Connection) DriverManager.getConnection(url,user, password);       
        } 
        catch(ClassNotFoundException e) { 
            System.out.println("can't find driver"); 
            e.printStackTrace(); 
        } 
        catch(SQLException e) { 
            e.printStackTrace(); 
        }
        user currentUser = wrist.getCurrentUser();

        //insert system data
        int sucessfulornot = wrist.isSucessfullornot();
        int account = currentUser.getAccount();
        this.insertSystemStateData(sucessfulornot,account);

        try {
            conn.close();   //close SQL
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    //insert data in DailyState
    public void insertDailyStateData(int Number,double Time,double Temperature){
        try {
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO `daily state table` "
				+ "(dailyNumber, idleTime, roomTemperature) VALUES"
				+ "(?,?,?)";
        
			preparedStatement = conn.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, Number);
			preparedStatement.setDouble(2, Time);
			preparedStatement.setDouble(3, Temperature);

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

            //conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //insert data in PhysicalState
    public void insertPhysicalStateData(int physicalNumber,double bodyTemperature,double pulse,double shakeCount){
        try{
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO `physical state table` "
				+ "(physicalNumber, bodyTemperature, pulse,shakeCount) VALUES"
				+ "(?,?,?,?)";
        
			preparedStatement = conn.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, physicalNumber);
			preparedStatement.setDouble(2, bodyTemperature);
			preparedStatement.setDouble(3, pulse);
                        preparedStatement.setDouble(4, shakeCount);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

            //conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      
    }
    
    public void insertuserData(int account,String userpassword,String username,String emergency_phoneNumber,String emergency_contact_person,String address){
        try{
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO `user` "
				+ "(account, password, userName,emergency_phoneNumber,emergency_contact_person,address) VALUES"
				+ "(?,?,?,?,?,?)";
        
			preparedStatement = conn.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, account);
			preparedStatement.setString(2, userpassword);
			preparedStatement.setString(3, username);
                        preparedStatement.setString(4, emergency_phoneNumber);
                        preparedStatement.setString(5, emergency_contact_person);
                        preparedStatement.setString(6, address);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

            //conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    private void insertSystemStateData(int sucessfulornot, int account) {
        try{
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO `wristband system table` "
				+ "(sucessfulornot, account) VALUES"
				+ "(?,?)";
        
			preparedStatement = conn.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, sucessfulornot);
			preparedStatement.setInt(2, account);
                        
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

            //conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    private void insertMedicalStateData(int medicalNumber,int account ,int physicalNumber, int dailyNumber) {
        try{
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO `medical history table` "
				+ "(medical_number,account,physical_number,daily_number) VALUES"
				+ "(?,?,?,?)";
        
			preparedStatement = conn.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, medicalNumber);
                        preparedStatement.setInt(2, account);
                        preparedStatement.setInt(3, physicalNumber);
                        preparedStatement.setInt(4, dailyNumber);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
}
