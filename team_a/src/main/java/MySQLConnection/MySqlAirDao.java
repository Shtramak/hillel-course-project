package main.java.MySQLConnection;

import main.java.airoport.Airport;
import main.java.DAO.AirDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlAirDao implements AirDao {

    private final Connection connection;

    @Override
    public Airport create() {
        return null;
    }

    @Override
    public Airport read(int key) throws SQLException{
        String sql = "SELECT * FROM tellus.Airport WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setInt(1, key);

        ResultSet rs = stm.executeQuery();
        rs.next();
        Airport g = new Airport();
        g.setAirportId(rs.getInt("id Airport"));
        g.setNumberTerminal(rs.getInt("number of terminal"));
        g.setNameAirport(rs.getString("Name Airport"));
        return g;
    }

    @Override
    public void update(Airport airport) {

    }

    @Override
    public void delete(Airport airport) {

    }

    @Override
    public List<Airport> getAll() throws SQLException {
        String sql = "SELECT * FROM tellus.Airport;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Airport> list = new ArrayList<Airport>();
        while (rs.next()) {
            Airport g = new Airport();
            g.setAirportId(rs.getInt("id Airport"));
            g.setNumberTerminal(rs.getInt("number of terminal"));
            g.setNameAirport(rs.getString("Name Airport"));
            list.add(g);
        }
        return list;
    }

    public MySqlAirDao(Connection connection) {
        this.connection = connection;
    }
}
