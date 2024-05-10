package helper;

import ObjectsFromERD.FirstDiv;
import ObjectsFromERD.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UsersDAOImp implements UsersDAO {
    public User selectUser(String userName) throws SQLException {
        User user =null;
        String sql = "SELECT *FROM USERS WHERE User_Name = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String passWord = rs.getString(3);
            Timestamp createDate = rs.getTimestamp(4);
            String createBy = rs.getString(5);
            Timestamp lastUpdate = rs.getTimestamp(6);
            String lastUpdatedBy = rs.getString(7);
            user = new User(id,userName,passWord,createDate,createBy,lastUpdate,lastUpdatedBy);
        }
        return user;
    }
    public User get(int id) throws SQLException {
        User user =null;
        String sql = "Select * FROM users WHERE User_ID = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String userName = rs.getString(2);
            String passWord = rs.getString(3);
            Timestamp createDate = rs.getTimestamp(4);
            String createBy = rs.getString(5);
            Timestamp lastUpdate = rs.getTimestamp(6);
            String lastUpdatedBy = rs.getString(7);
            user = new User(id,userName,passWord,createDate,createBy,lastUpdate,lastUpdatedBy);
        }
        return user;
    }
    public User getName(String userName) throws SQLException {
        User user =null;
        String sql = "Select * FROM users WHERE User_Name = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id= rs.getInt(1);
            userName = rs.getString(2);
            String passWord = rs.getString(3);
            Timestamp createDate = rs.getTimestamp(4);
            String createBy = rs.getString(5);
            Timestamp lastUpdate = rs.getTimestamp(6);
            String lastUpdatedBy = rs.getString(7);
            user = new User(id,userName,passWord,createDate,createBy,lastUpdate,lastUpdatedBy);
        }
        return user;
    }

    @Override
    public ObservableList<User> getAll() throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String sql = "Select * FROM users";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String userName = rs.getString(2);
            String passWord = rs.getString(3);
            Timestamp createDate = rs.getTimestamp(4);
            String createBy = rs.getString(5);
            Timestamp lastUpdate = rs.getTimestamp(6);
            String lastUpdatedBy = rs.getString(7);
            userList.add(new User(id,userName,passWord,createDate,createBy,lastUpdate,lastUpdatedBy));
        }
        return userList;
    }

    @Override
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO users (User_Name,Password,Create_Date,Created_By,Last_Update,Last_Updated_By) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,user.getUser_name());
        ps.setString(2,user.getPassword());
        ps.setTimestamp(3,user.getCreated_date());
        ps.setString(4,user.getCreate_by());
        ps.setTimestamp(5,user.getLast_update());
        ps.setString(6,user.getLast_updated_by());
        return ps.executeUpdate();
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = "UPDATE users SET User_Name=?,Password=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=? WHERE User_ID =? ";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,user.getUser_name());
        ps.setString(2,user.getPassword());
        ps.setTimestamp(3,user.getCreated_date());
        ps.setString(4,user.getCreate_by());
        ps.setTimestamp(5,user.getLast_update());
        ps.setString(6,user.getLast_updated_by());
        ps.setInt(7,user.getUser_id());
        int returnValue= ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        user.setUser_id(rs.getInt(1));
        return returnValue;
    }

    @Override
    public int delete(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE User_ID= ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,user.getUser_id());
        return ps.executeUpdate();
    }

    public String select(String userName,String pass) throws SQLException {
        String sql = "SELECT *FROM USERS WHERE User_Name = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();
        String isCorrect;
        isCorrect = null;
        if (rs.next()){ // username is unique so there is no need for a while loop here
            String password = rs.getString("Password");
            if (pass.equals(password)){
                isCorrect = pass+" is correct!";
            }
            else{
                isCorrect = "pass";
            }
        }
        else{
            isCorrect = "user";
        }
        return isCorrect;

    }
}
