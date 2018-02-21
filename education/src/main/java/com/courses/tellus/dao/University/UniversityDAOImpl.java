package com.courses.tellus.dao.University;

import com.courses.tellus.dao.AbstractDao;
import com.courses.tellus.db_connection.ConnectionUtils;
import com.courses.tellus.db_connection.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityDAOImpl implements AbstractDao {
    private Connection connection = ConnectionUtils.getConnection();

 public  static University getResultUniversity(PreparedStatement preparedStatement){

     PreparedStatement ps = preparedStatement;
     University university = new University();
     try(ResultSet rSet=ps.executeQuery()) {
         while (rSet.next()) {
             university.setId(rSet.getInt("id"));
             university.setNameOfUniversity(rSet.getString("name"));
             university.setAddress(rSet.getString("address"));
             university.setSpecialization("specialization");
 }}

 catch (SQLException e){
         e.printStackTrace();
 }return  university;
 }

    @Override
    public List<University> getAll()  {
        List<University> listOfUniversities = new ArrayList<University>();
        try (PreparedStatement ps=connection.prepareStatement("SELECT*FROM Universities")){

            listOfUniversities.add( getResultUniversity(ps));

        } catch (SQLException e){
            e.printStackTrace();
        }
        return listOfUniversities;
    }



    @Override
    public Object getEntityById(Object id) {
        int idForSearch;
        List<University> listOfUniversitiesById = new ArrayList<University>();
        try (PreparedStatement ps=connection.prepareStatement("SELECT*FROM Universities WHERE id=?")){
            idForSearch = (int)id;
            ps.setInt(1,idForSearch);
            ps.executeQuery();
            listOfUniversitiesById.add(getResultUniversity(ps));
        } catch (SQLException | ClassCastException e){
            e.printStackTrace();
        }
        return listOfUniversitiesById;
    }


    @Override
    public void update(Object entity) {
        University u;
        int idForUpdate;
        try (PreparedStatement ps= connection.prepareStatement
                ("UPDATE Univarsities SET" + " nameOfUniversity = ? , address =?, specialization =?" +
                        "WHERE id= ?")) {

            u = (University) entity;
            idForUpdate = u.getId();
            String nameOfUniversity = u.getNameOfUniversity();
            String address = u.getAddress();
            String specialization = u.getSpecialization();
            ps.setString(1, nameOfUniversity);
            ps.setString(2, address);
            ps.setString(3, specialization);
            ps.setInt(4,idForUpdate);
            ps.executeUpdate();

        } catch (SQLException | ClassCastException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void delete(Object id) {
        int idForDelete;
        try (PreparedStatement ps=connection.prepareStatement("DELETE FROM Universities " +
                "WHERE id=?")){
            idForDelete = (int)id;
            ps.setInt(1,idForDelete);
            ps.executeUpdate();

        } catch (SQLException | ClassCastException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void create(Object entity)  {
        University u;

        try (PreparedStatement ps=connection.prepareStatement
                ("INSERT INTO Univerities(nameOfUniversity,address,specialization)" +
                        "VALUES (?,?,?)")){
            u=(University)entity;
            String nameOfUniversity = u.getNameOfUniversity();
            String address = u.getAddress();
            String specialization = u.getSpecialization();
            ps.setString(1,nameOfUniversity);
            ps.setString(2,address);
            ps.setString(3,specialization);
            ps.executeUpdate();

        } catch (SQLException | ClassCastException  e) {
            e.printStackTrace();
        }
    }
}
