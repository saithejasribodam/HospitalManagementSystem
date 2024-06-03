package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private Scanner scanner;
    private Connection connection; //encapsulation , driver class
    public Doctor(Connection connection){
        this.connection=connection;
        this.scanner=scanner;
    }



    public void viewDoctors(){
        String query="select * from doctors";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultset=preparedStatement.executeQuery(query);
//            System.out.println("Patients: ");
            System.out.println("-------------+----------------+---------------+-------------");
            System.out.println("| Doctor Id | Name            | specilization     |");
            System.out.println("-------------+----------------+---------------+-------------");
            while(resultset.next()){
                int id=resultset.getInt("id");// id in getint is sql table field name
                String name=resultset.getString("name");
                String specilization=resultset.getString("specilization");
                System.out.printf(" |%-12s|%-20s|%-18s| \n ",id,name,specilization);//%-12s-to give spaces
                System.out.println("-------------+----------------+---------------+-------------");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }




    public boolean getDoctorById(int id){
        String query ="select * from doctors where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            } else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
