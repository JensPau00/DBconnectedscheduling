package helper;

import ObjectsFromERD.Country;
import ObjectsFromERD.FirstDiv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CountryDAOImp implements CountryDAO{
    @Override
    public Country get(int id) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,id);
        Country country = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String name = rs.getString(2);
            Timestamp create_date = rs.getTimestamp(3);
            String created_by = rs.getString(4);
            Timestamp last_update = rs.getTimestamp(5);
            String last_updated_by = rs.getString(6);
            country = new Country(id,name,create_date,created_by,last_update,last_updated_by);
        }
        return country;
    }

    @Override
    public ObservableList<Country> getAll() throws SQLException {
        String sql = "SELECT * FROM countries";
        ObservableList<Country> countries = FXCollections.observableArrayList();
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        Country country = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Timestamp create_date = rs.getTimestamp(3);
            String created_by = rs.getString(4);
            Timestamp last_update = rs.getTimestamp(5);
            String last_updated_by = rs.getString(6);
            countries.add( new Country(id,name,create_date,created_by,last_update,last_updated_by));
        }
        return countries;
    }

    @Override
    public int insert(Country country) throws SQLException {
        String sql = "INSERT INTO countries (Country,Create_Date,Created_By,Last_Update,Last_Updated_By)VALUES(?,?,?,?,?)";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,country.getCountry());
        ps.setTimestamp(2,country.getCreate_date());
        ps.setString(3,country.getCreated_by());
        ps.setTimestamp(4,country.getLast_update());
        ps.setString(5, country.getLast_updated_by());
        int returnValue= ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        country.setCountry_ID(rs.getInt(1));
        return returnValue;
    }

    @Override
    public int update(Country country) throws SQLException {
        String sql = "UPDATE countries SET Country=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=? WHERE Country_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,country.getCountry());
        ps.setTimestamp(2,country.getCreate_date());
        ps.setString(3,country.getCreated_by());
        ps.setTimestamp(4,country.getLast_update());
        ps.setString(5, country.getLast_updated_by());
        ps.setInt(6,country.getCountry_ID());
        ps.executeUpdate();
        return ps.executeUpdate();
    }

    @Override
    public int delete(Country country) throws SQLException {
        String sql = "DELETE FROM countries WHERE Country_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,country.getCountry_ID());
        return ps.executeUpdate();
    }
}
