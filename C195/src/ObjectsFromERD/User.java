package ObjectsFromERD;

import java.sql.Timestamp;

public class User {
    private int user_id;
    private String user_name;
    private String password;
    private Timestamp created_date;
    private String create_by;
    private Timestamp last_update;
    private String last_updated_by;

    public User(int user_id, String user_name, String password, Timestamp created_date, String create_by, Timestamp last_update, String last_updated_by) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.created_date = created_date;
        this.create_by = create_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
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
    @Override
    public String toString() {
        return user_name;
    }
}
