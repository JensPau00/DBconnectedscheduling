package helper;

import ObjectsFromERD.Contact;
import ObjectsFromERD.FirstDiv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAOImp implements ContactDAO{
    @Override
    public Contact get(int id) throws SQLException {
        String sql = "Select * FROM contacts WHERE Contact_ID = ?";
        Contact contact = null;
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String name = rs.getString(2);
            String email = rs.getString(3);
            contact = new Contact(id,name,email);
        }
        return contact;
    }

    @Override
    public ObservableList<Contact> getAll() throws SQLException {
        ObservableList<Contact> contacts=FXCollections.observableArrayList();
        String sql = "Select * FROM contacts";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            contacts.add(new Contact(id,name,email));
        }
        return contacts;
    }

    @Override
    public int insert(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (Contact_Name,Email) VALUES (?,?)";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(2,contact.getContact_name());
        ps.setString(3, contact.getEmail());
        int returnValue= ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        contact.setContact_id(rs.getInt(1));
        return returnValue;
    }

    @Override
    public int update(Contact contact) throws SQLException {
        String sql = "UPDATE contacts set Contact_Name=?,Email = ? WHERE Contact_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,contact.getContact_name());
        ps.setString(2, contact.getEmail());
        ps.setInt(3,contact.getContact_id());
        return ps.executeUpdate();
    }

    @Override
    public int delete(Contact contact) throws SQLException {
        String sql = "DELETE FROM contacts WHERE Contact_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,contact.getContact_id());
        return ps.executeUpdate();
    }
}
