package MySQLConnection;

import main.java.DAO.AirDao;
import main.java.DAO.DaoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {

    private String user = "root";
    private String password = "";
    private String url = "";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера


    @Override
    public Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, user, password);
    }

    @Override
    public AirDao getGroupDao(Connection connection) {
        return new MySqlAirDao(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
