package helper;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class mainScreenTableViewStorage {
    private static CustomerDAOImp cus = new CustomerDAOImp();
    private static AppointmentsDAOImp appt = new AppointmentsDAOImp();
    private static ObservableList<Customer> customers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> appointmentsShow = FXCollections.observableArrayList();
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    public mainScreenTableViewStorage() throws SQLException {
        customers = cus.getAll();
        appointments = appt.getAll();
    }
    public void deleteCus(Customer customer) throws SQLException {
        for (Appointment appointment:appointments){
            if (customer.getCustomer_ID()==appointment.getCustomer_id()){
                appt.delete(appointment);
                appointmentsShow.add(appointment);
            }
        }
        for(Appointment appointment:appointmentsShow){
            appointments.remove(appointment);
        }
        appointmentsShow.clear();
        cus.delete(customer);
        customers.remove(customer);
    }
    public void deleteAppt(Appointment appointment) throws SQLException {
        appt.delete(appointment);
        appointments.remove(appointment);
    }
    public static ObservableList<Appointment> weeklySort(){
        LocalDate today= LocalDate.now();
        ObservableList<Appointment> appointmentsWeekly= FXCollections.observableArrayList();
        for(Appointment appointment:appointments){
            if (appointment.getStart().toLocalDateTime().toLocalDate().compareTo(today.plusDays(7))<=0 && appointment.getStart().toLocalDateTime().toLocalDate().compareTo(today)>=0){
                appointmentsWeekly.add(appointment);
            }

        }
        return appointmentsWeekly;
    }
    public static ObservableList<Appointment> monthlySort(){
        LocalDate today= LocalDate.now();
        ObservableList<Appointment> appointmentsMonthly= FXCollections.observableArrayList();
        for(Appointment appointment:appointments){
            if (appointment.getStart().toLocalDateTime().toLocalDate().getMonth()==LocalDate.now().getMonth() && appointment.getStart().toLocalDateTime().getYear()==LocalDateTime.now().getYear()){
                appointmentsMonthly.add(appointment);
            }

        }
        return appointmentsMonthly;
    }
    public ObservableList<Customer> getCustomers(){
        return customers;}
    public ObservableList<Appointment> getAppointments(){
        return appointments;}
}
