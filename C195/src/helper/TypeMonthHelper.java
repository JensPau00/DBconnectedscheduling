package helper;

import Controller.TypeMonthViewController;
import ObjectsFromERD.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class TypeMonthHelper {
    private static ObservableList<String> monthList= FXCollections.observableArrayList();
    private ObservableList<Types> type = FXCollections.observableArrayList();
    private ObservableList<Integer> typeNumb = FXCollections.observableArrayList();
    private AppointmentsDAOImp apts;
    private ObservableList<Appointment> appointments;
    public static void setMonths(){
        if (monthList.isEmpty())
        monthList.add("January");
        monthList.add("February");
        monthList.add("March");
        monthList.add("April");
        monthList.add("May");
        monthList.add("June");
        monthList.add("July");
        monthList.add("August");
        monthList.add("September");
        monthList.add("October");
        monthList.add("November");
        monthList.add("December");

    }
    public TypeMonthHelper(){
        apts = new AppointmentsDAOImp();
    }
    public void setType(String month) throws SQLException {
        appointments = apts.getAll();
        boolean isNewType=true;
        type = FXCollections.observableArrayList();
        for (Appointment appointment:appointments){
            if (appointment.getStart().toLocalDateTime().getMonth().toString().equals(month)){
                for (Types type:this.type){
                    if (appointment.getType().equals(type.getType())) {
                        isNewType=false;
                        type.incNum();

                    }
                }
                if (isNewType){
                    this.type.add(new Types(appointment.getType()));
                }
            }
        }
    }
    public static ObservableList<String>getMonths(){
        return monthList;
    }
    public ObservableList<Types>getTypes(){
        return this.type;
    }
}
