package helper;

import ObjectsFromERD.Country;
import ObjectsFromERD.FirstDiv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FirstDivDAOImp implements FirstDivDAO{
    @Override
    public FirstDiv get(int id) throws SQLException {
        FirstDiv firstDiv=null;
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String division = rs.getString(2);
            Timestamp create_date = rs.getTimestamp(3);
            String created_by = rs.getString(4);
            Timestamp last_update = rs.getTimestamp(5);
            String last_updated_by = rs.getString(6);
            int country_ID = rs.getInt(7);
            firstDiv = new FirstDiv(id,division,create_date,created_by,last_update,last_updated_by,country_ID);
        }
        return firstDiv;
    }

    @Override
    public ObservableList<FirstDiv> getAll() throws SQLException {
        ObservableList<FirstDiv> firstDivs= FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String division = rs.getString(2);
            Timestamp create_date = rs.getTimestamp(3);
            String created_by = rs.getString(4);
            Timestamp last_update = rs.getTimestamp(5);
            String last_updated_by = rs.getString(6);
            int country_ID = rs.getInt(7);
            firstDivs.add(new FirstDiv(rs.getInt(1),division,create_date,created_by,last_update,last_updated_by,country_ID));
        }
        return firstDivs;
    }
    public ObservableList<FirstDiv> getCountry(Country country) throws SQLException {
        ObservableList<FirstDiv> firstDivs= FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,country.getCountry_ID());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String division = rs.getString(2);
            Timestamp create_date = rs.getTimestamp(3);
            String created_by = rs.getString(4);
            Timestamp last_update = rs.getTimestamp(5);
            String last_updated_by = rs.getString(6);
            int country_ID = rs.getInt(7);
            firstDivs.add(new FirstDiv(rs.getInt(1),division,create_date,created_by,last_update,last_updated_by,country_ID));
        }
        return firstDivs;
    }

    @Override
    public int insert(FirstDiv firstDiv) throws SQLException {
        String sql = "INSERT INTO first_level_divisions (Division,Create_Date,Created_By,Last_Update,Last_Updated_By,Country_ID) VALVES (?,?,?,?,?,?)";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1, firstDiv.getDivision());
        ps.setTimestamp(2,firstDiv.getCreate_date());
        ps.setString(3, firstDiv.getCreated_by());
        ps.setTimestamp(4,firstDiv.getLast_update());
        ps.setString(5,firstDiv.getLast_updated_by());
        ps.setInt(6,firstDiv.getCountry_ID());
        int returnValue= ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        firstDiv.setId(rs.getInt(1));
        return returnValue;
    }

    @Override
    public int update(FirstDiv firstDiv) throws SQLException {
        String sql = "UPDATE first_level_divisions SET Division=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=?,Country_ID=? WHERE Division_ID = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1, firstDiv.getDivision());
        ps.setTimestamp(2,firstDiv.getCreate_date());
        ps.setString(3, firstDiv.getCreated_by());
        ps.setTimestamp(4,firstDiv.getLast_update());
        ps.setString(5,firstDiv.getLast_updated_by());
        ps.setInt(6,firstDiv.getCountry_ID());
        ps.setInt(7,firstDiv.getId());
        return ps.executeUpdate();
    }

    @Override
    public int delete(FirstDiv firstDiv) throws SQLException {
        String sql = "DELETE FROM first_level_divisions WHERE Division_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,firstDiv.getId());
        return ps.executeUpdate();
    }

}
