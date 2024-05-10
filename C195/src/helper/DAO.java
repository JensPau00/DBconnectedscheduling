package helper;

import ObjectsFromERD.FirstDiv;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DAO<Gen_Object> {
    Gen_Object get(int id) throws SQLException; //read
    ObservableList<Gen_Object> getAll() throws SQLException; //read
    int insert(Gen_Object genObject) throws SQLException; //create
    int update(Gen_Object genObject) throws SQLException; //update
    int delete(Gen_Object genObject) throws SQLException; //delete
}
