package ObjectsFromERD;

import helper.FirstDivDAOImp;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Customer {
    private String division_name;
    private int customer_ID;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private Timestamp create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;
    private int division_id;
    private FirstDivDAOImp divDAO=new FirstDivDAOImp();


    public String getDivision_name() {
        return division_name;
    }

    public Customer(int customer_ID, String customer_name, String address, String postal_code, String phone, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by, int div_id) throws SQLException {
        this.customer_ID = customer_ID;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = div_id;

        this.division_name = divDAO.get(div_id).getDivision();
    }

    public Customer(String customer_name, String address, String postal_code, String phone, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by, int div_id) {
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = div_id;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }
    public int getDivision_id() {
        return division_id;
    }

    public void setDivision_id(int division_id) throws SQLException {
        this.division_id = division_id;
        this.division_name = divDAO.get(division_id).getDivision();
    }

    @Override
    public String toString() {
        return customer_name;
    }
}
