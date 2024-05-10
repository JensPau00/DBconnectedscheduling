package helper;

import ObjectsFromERD.Country;
import ObjectsFromERD.FirstDiv;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface FirstDivDAO extends DAO<FirstDiv>{
    ObservableList<FirstDiv> getCountry(Country country) throws SQLException;
}
