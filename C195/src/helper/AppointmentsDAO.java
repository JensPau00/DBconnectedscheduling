package helper;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Customer;

import java.sql.SQLException;

public interface AppointmentsDAO extends DAO<Appointment> {

    public int deleteByCustomerID(Customer customer) throws SQLException;
}
