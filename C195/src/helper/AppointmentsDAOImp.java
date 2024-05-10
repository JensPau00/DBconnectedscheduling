package helper;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Customer;
import ObjectsFromERD.FirstDiv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AppointmentsDAOImp implements AppointmentsDAO{

    @Override
    public Appointment get(int id) throws SQLException {
        Appointment appointment= null;
        String sql = "Select * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int Apt_id = (id);
            String created_by = (rs.getString("Created_By"));
            Timestamp createDate = (rs.getTimestamp("Create_Date"));
            String setDescription = (rs.getString("Description"));
            Timestamp setStart = (rs.getTimestamp("Start"));
            Timestamp setEnd = (rs.getTimestamp("End"));
            Timestamp setLast_update = (rs.getTimestamp("Last_Update"));
            String setLast_updated_by=(rs.getString("Last_Updated_By"));
            int setCustomer_id = (rs.getInt("Customer_ID"));
            int setContact_id = (rs.getInt("Contact_ID"));
            int setUser_id = (rs.getInt("User_ID"));
            String setType = (rs.getString("Type"));
            String setTitle = (rs.getString("Title"));
            String setLocation = (rs.getString("Location"));
            appointment = new Appointment(Apt_id,setTitle,setDescription,setLocation,setType,setStart,setEnd,createDate,created_by,setLast_update,setLast_updated_by,setCustomer_id,setUser_id,setContact_id);
        }
        return appointment;
    }

    @Override
    public ObservableList<Appointment> getAll() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String sql = "Select * FROM appointments";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int Apt_id = (rs.getInt("Appointment_ID"));
            String created_by = (rs.getString("Created_By"));
            Timestamp createDate = (rs.getTimestamp("Create_Date"));
            String setDescription = (rs.getString("Description"));
            Timestamp setStart = (rs.getTimestamp("Start"));
            Timestamp setEnd = (rs.getTimestamp("End"));
            Timestamp setLast_update = (rs.getTimestamp("Last_Update"));
            String setLast_updated_by = (rs.getString("Last_Updated_By"));
            int setCustomer_id = (rs.getInt("Customer_ID"));
            int setContact_id = (rs.getInt("Contact_ID"));
            int setUser_id = (rs.getInt("User_ID"));
            String setType = (rs.getString("Type"));
            String setTitle = (rs.getString("Title"));
            String setLocation = (rs.getString("Location"));
            appointments.add(new Appointment(Apt_id, setTitle, setDescription, setLocation, setType, setStart, setEnd, createDate, created_by, setLast_update, setLast_updated_by, setCustomer_id, setUser_id, setContact_id));
        }
        return appointments;
    }

    public ObservableList<Appointment> getAllByContactID(int ID) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String sql = "Select * FROM appointments where contact_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int Apt_id = (rs.getInt("Appointment_ID"));
            String created_by = (rs.getString("Created_By"));
            Timestamp createDate = (rs.getTimestamp("Create_Date"));
            String setDescription = (rs.getString("Description"));
            Timestamp setStart = (rs.getTimestamp("Start"));
            Timestamp setEnd = (rs.getTimestamp("End"));
            Timestamp setLast_update = (rs.getTimestamp("Last_Update"));
            String setLast_updated_by = (rs.getString("Last_Updated_By"));
            int setCustomer_id = (rs.getInt("Customer_ID"));
            int setContact_id = (rs.getInt("Contact_ID"));
            int setUser_id = (rs.getInt("User_ID"));
            String setType = (rs.getString("Type"));
            String setTitle = (rs.getString("Title"));
            String setLocation = (rs.getString("Location"));
            appointments.add(new Appointment(Apt_id, setTitle, setDescription, setLocation, setType, setStart, setEnd, createDate, created_by, setLast_update, setLast_updated_by, setCustomer_id, setUser_id, setContact_id));
        }
        return appointments;
    }


    @Override
    public int insert(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (Title,Description,Location,Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        try{
            ps.setString(1,appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3,appointment.getLocation());
            ps.setString(4,appointment.getType());
            ps.setTimestamp(5, appointment.getStart());
            ps.setTimestamp(6, appointment.getEnd());
            ps.setTimestamp(7,appointment.getCreationDate());
            ps.setString(8, appointment.getCreated_by());
            ps.setTimestamp(9, appointment.getLast_update());
            ps.setString(10,appointment.getLast_updated_by());
            ps.setInt(11,appointment.getCustomer_id());
            ps.setInt(12,appointment.getUser_id());
            ps.setInt(13,appointment.getContact_id());
        }
        catch (SQLException e){
            System.out.println("Error!");
        }
        int returnValue= ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        appointment.setAppointment_id(rs.getInt(1));
        return returnValue;
    }

    @Override
    public int update(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointments set Title=?,Description=?,Location=?,Type=?,Start=?,End=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=?,Customer_ID=?,User_ID=?,Contact_ID=? WHERE Appointment_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setString(1,appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3,appointment.getLocation());
        ps.setString(4,appointment.getType());
        ps.setTimestamp(5, appointment.getStart());
        ps.setTimestamp(6, appointment.getEnd());
        ps.setTimestamp(7,appointment.getCreationDate());
        ps.setString(8, appointment.getCreated_by());
        ps.setTimestamp(9, appointment.getLast_update());
        ps.setString(10,appointment.getLast_updated_by());
        ps.setInt(11,appointment.getCustomer_id());
        ps.setInt(12,appointment.getUser_id());
        ps.setInt(13,appointment.getContact_id());
        ps.setInt(14,appointment.getAppointment_id());
        return ps.executeUpdate();
    }

    @Override
    public int delete(Appointment appointment) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,appointment.getAppointment_id());
        return ps.executeUpdate();
    }

    @Override
    public int deleteByCustomerID(Customer customer) throws SQLException {
        String sql = "DELETE FROM appointment WHERE Customer_ID=?";
        PreparedStatement ps = jdbc.connection.prepareStatement(sql);
        ps.setInt(1,customer.getCustomer_ID());
        return ps.executeUpdate();
    }
}
