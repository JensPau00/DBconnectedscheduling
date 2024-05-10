package helper;

import ObjectsFromERD.User;

import java.sql.SQLException;

public interface UsersDAO extends DAO<User> {
    public String select(String userName,String pass) throws SQLException;
}
