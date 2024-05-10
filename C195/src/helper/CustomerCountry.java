package helper;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Country;
import ObjectsFromERD.Customer;
import ObjectsFromERD.FirstDiv;

import java.sql.SQLException;

public class CustomerCountry {
    private int cusCount;
    private Country country;
    public CustomerCountry(Country country){
        this.country = country;
        this.cusCount=0;
    }

    public int getCusCount() {
        return cusCount;
    }

    public void setCusCount(int cusCount) {
        this.cusCount = cusCount;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void countCus() throws SQLException {
        CustomerDAOImp cus = new CustomerDAOImp();
        FirstDivDAOImp div = new FirstDivDAOImp();
        int cID;
        for (Customer customer:cus.getAll()){
            cID = div.get(customer.getDivision_id()).getCountry_ID();
            if (cID==country.getCountry_ID()){
                cusCount+=1;
            }
        }
    }
}
