package helper;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Customer;
import sample.AlertError;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class cusValidation {
    static AlertError lambdaAlert = (header, content, error) -> {
        AlertError.alert.setHeaderText(header);
        AlertError.alert.setContentText(content);
        AlertError.alert.setTitle(error);
        AlertError.alert.showAndWait();};
    private static mainScreenTableViewStorage ms;

    static {
        try {
            ms = new mainScreenTableViewStorage();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public cusValidation() throws SQLException {
    }

    public static boolean validate(Customer cus, LocalDateTime checkTimeStart,LocalDateTime checkTimeEnd,Appointment curApt){
        boolean isValid = true;
        for (Appointment appointment:ms.getAppointments()){
            if(appointment.getCustomer_id()==cus.getCustomer_ID() &&curApt.getAppointment_id()!=appointment.getAppointment_id()){
                if ((checkTimeStart.isBefore(appointment.getEnd().toLocalDateTime())||checkTimeStart.isEqual(appointment.getEnd().toLocalDateTime()))&& (checkTimeStart.isAfter(appointment.getStart().toLocalDateTime())||checkTimeStart.isEqual(appointment.getStart().toLocalDateTime()))){
                    lambdaAlert.displayAlert("ERROR","","A customer can not be scheduled for two appointments at the same time");
                    isValid = false;
                    break;
                }
                if ((checkTimeEnd.isBefore(appointment.getEnd().toLocalDateTime())||checkTimeEnd.isEqual(appointment.getEnd().toLocalDateTime()))&& ((checkTimeEnd.isAfter(appointment.getStart().toLocalDateTime()))||checkTimeEnd.isEqual(appointment.getStart().toLocalDateTime()))){
                    lambdaAlert.displayAlert("ERROR","","A customer can not be scheduled for two appointments at the same time");
                    isValid = false;
                    break;

                }
                if ((appointment.getStart().toLocalDateTime().isBefore(checkTimeEnd)||checkTimeStart.isEqual(appointment.getEnd().toLocalDateTime()))&& ((appointment.getStart().toLocalDateTime().isAfter(checkTimeStart)||checkTimeStart.isEqual(appointment.getStart().toLocalDateTime())))){
                    lambdaAlert.displayAlert("ERROR","","A customer can not be scheduled for two appointments at the same time");
                    isValid = false;
                    break;

                }
                if ((appointment.getEnd().toLocalDateTime().isBefore(checkTimeEnd)||checkTimeEnd.isEqual(appointment.getEnd().toLocalDateTime()))&& ((appointment.getEnd().toLocalDateTime().isAfter(checkTimeStart)||checkTimeStart.isEqual(appointment.getEnd().toLocalDateTime())))){
                    lambdaAlert.displayAlert("ERROR","","A customer can not be scheduled for two appointments at the same time");
                    isValid = false;
                    break;

                }
            }
        }
        return isValid;
    }
}
