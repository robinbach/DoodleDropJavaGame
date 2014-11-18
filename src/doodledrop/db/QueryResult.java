package doodledrop.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class QueryResult {
  abstract void getResult(ResultSet rs) throws SQLException;
}
