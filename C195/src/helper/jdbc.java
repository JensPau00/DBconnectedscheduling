package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class jdbc {

    private static final String protcol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String dbName = "client_schedule";
    private static final String jdbUrl = protcol+vendor+location+dbName+"?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    public static void openConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbUrl, userName,password);
            System.out.println("connection successful");
        }
        catch(Exception e){
            System.out.println("error"+e.getMessage());
        }
    }
    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("connection closed");
        }
        catch(Exception e){
            System.out.println("error"+e.getMessage());
        }
    }





}
