package helper;

import ObjectsFromERD.Customer;
import ObjectsFromERD.FirstDiv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerDAOImp implements CustomerDAO{
    @Override
    public Customer get(int id) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE Customer_id = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String cus_name = rs.getString(2);
            String add =  rs.getString(3);
            String pos = rs.getString(4);
            String phone = rs.getString(5);
            Timestamp create_date = rs.getTimestamp(6);
            String create_by = rs.getString(7);
            Timestamp last_update = rs.getTimestamp(8);
            String last_updated_by = rs.getString(9);
            int div_id = rs.getInt(10);
            customer = new Customer(id,cus_name,add,pos,phone,create_date,create_by,last_update,last_updated_by,div_id);
        }
        return customer;
    }

    @Override
    public ObservableList<Customer> getAll() throws SQLException {

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String cus_name = rs.getString(2);
            String add =  rs.getString(3);
            String pos = rs.getString(4);
            String phone = rs.getString(5);
            Timestamp create_date = rs.getTimestamp(6);
            String create_by = rs.getString(7);
            Timestamp last_update = rs.getTimestamp(8);
            String last_updated_by = rs.getString(9);
            int div_id = rs.getInt(10);
            customers.add( new Customer(rs.getInt(1),cus_name,add,pos,phone,create_date,create_by,last_update,last_updated_by,div_id));
        }
        return customers;
    }

    @Override
    public int insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer.getCustomer_name());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostal_code());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5,customer.getCreate_date());
        ps.setString(6, customer.getCreated_by());
        ps.setTimestamp(7,customer.getLast_update());
        ps.setString(8, customer.getLast_updated_by());
        ps.setInt(9,customer.getDivision_id());
        int returnValue= ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        customer.setCustomer_ID(rs.getInt(1));
        return returnValue;
    }

    @Override
    public int update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name=?,Address=?,Postal_Code=?,Phone=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=?,Division_ID=? WHERE Customer_ID = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1, customer.getCustomer_name());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostal_code());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5,customer.getCreate_date());
        ps.setString(6, customer.getCreated_by());
        ps.setTimestamp(7,customer.getLast_update());
        ps.setString(8, customer.getLast_updated_by());
        ps.setInt(9,customer.getDivision_id());
        ps.setInt(10,customer.getCustomer_ID());
        return ps.executeUpdate();
    }

    @Override
    public int delete(Customer customer) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,customer.getCustomer_ID());
        return ps.executeUpdate();
    }
}
