package com.courses.airport.dao;

import com.courses.airport.mysqlconnection.MainConnection;
import com.courses.airport.essences.Passenger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PassengerDAO {

//    private String passengierTableName = "";
    public List<Passenger> getAllPassengiers() {
        Statement statement;
        ResultSet resultSet = null;
        MainConnection mainConnection = new MainConnection();
        Connection connection = mainConnection.connect();
        List<Passenger> passengerList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM  airports ;");
            while (resultSet.next()) {
                passengerList.add(new Passenger(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passengerList;
    }

    public void updatePassenger(int id, Passenger passenger) {
        MainConnection mainConnection = new MainConnection();
        Connection connection = (Connection) mainConnection.connect();
        String updatePassegerQuery = "UPDATE airports SET " +
                "passengerId = " + passenger.getPassengerId() +", " +
                "name = '" + passenger.getName() + "', " +
                "lastName = '" + passenger.getLastName() + "' , " +
                "age=" + passenger.getAge() +", " +
                "sex = " + passenger.getSex() + ", " +
                "kmFlightScore = " + passenger.getKmFlightScore() +" " +
                "WHERE " +
                "passengerId = " + id + ";";
        updateQuerry(updatePassegerQuery);
    }

    public void deletePassenger (int id) {
        String deleteQuery = "DELETE FROM airports WHERE passengerId=" + id +";";
        updateQuerry(deleteQuery);

    }

    public void addPassenger (Passenger passenger) {
        String addPassQuery = "INSERT INTO airports VALUES " +
                "(" +   passenger.getPassengerId() +", '" +
                passenger.getName() + "', '" +
                passenger.getLastName() + "', " +
                passenger.getAge() + ", " +
                passenger.getSex() +", " +
                passenger.getKmFlightScore() + ");";
        updateQuerry(addPassQuery);
    }

    private void updateQuerry (String query) {
        MainConnection mainConnection = new MainConnection();
        Connection connection = (Connection) mainConnection.connect();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Passenger getById (int passId) {
        Statement statement = null;
        ResultSet resultSet = null;
        Passenger uploadedPass = null;
        MainConnection mainConnection = new MainConnection();
        Connection connection = (Connection) mainConnection.connect();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM airports WHERE passengerId = " + passId +";");
            resultSet.next();
            uploadedPass = new Passenger(   passId,
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uploadedPass;
    }

}
