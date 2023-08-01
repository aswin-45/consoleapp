package Hotel_booking;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;

public abstract class AbstractDAO<T> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract void create(T entity) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
    public abstract void update(T entity) throws SQLException;
    public abstract void delete(int id) throws SQLException;
}



