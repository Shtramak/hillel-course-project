package DAO;

public class PassengerDAO {

    private String passengierTableName = "airport.passenger";
    public List<Passenger> getAllPassengiers() {
        Statement statement = null;
        ResultSet resultSet = null;
        MainConnection mainConnection = new MainConnection();
        Connection connection = (Connection) mainConnection.getConnection();
        List<Passenger> passengerList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + passengierTableName + ";");
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
        Connection connection = (Connection) mainConnection.getConnection();
        String updatePassegerQuery = "UPDATE "+ passengierTableName +" SET " +
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
        String deleteQuery = "DELETE FROM " + passengierTableName + " WHERE passengerId=" + id +";";
        updateQuerry(deleteQuery);

    }
    public void addPassenger (Passenger passenger) {
        String addPassQuery = "INSERT INTO " + passengierTableName +
                " VALUES " +
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
        Connection connection = (Connection) mainConnection.getConnection();

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
}
