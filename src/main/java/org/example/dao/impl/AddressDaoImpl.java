package org.example.dao.impl;

import org.example.dao.AddressDao;
import org.example.models.Address;
import org.example.models.Group;

import java.sql.*;
import java.util.List;


public class AddressDaoImpl implements AddressDao {

    public AddressDaoImpl(Address address) {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Connecting to database...");
            connection = getConnection();
            System.out.println("Connection succeeded");

            String ddlQuery = "CREATE TABLE IF NOT EXISTS tb_addresses(" +
                    "id        BIGSERIAL, " +
                    "state     VARCHAR(50) NOT NULL, ";

            System.out.println("Creating statement...");
            statement = connection.createStatement();
            System.out.println("Executing create table statement...");
            statement.execute(ddlQuery);
            System.out.println(ddlQuery);

        } catch (SQLException e) {
            System.out.println("Some error occurred");
            e.printStackTrace();
        }finally {
            close(statement);
            close(connection);
        }
    }

    public AddressDaoImpl() {

    }

    @Override
    public Address save(Address address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Address savedAddress = null;

        try {
            System.out.println("Connecting to database...");
            connection = getConnection();
            System.out.println("Connection succeeded");

            String createQuery = "INSERT INTO tb_groups(" +
                    "state, city, region, district, street, apartment, date_created) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(createQuery);
            preparedStatement.setString(1, address.getState());
            preparedStatement.setString(2,address.getCity());
            preparedStatement.setString(3, address.getRegion());
            preparedStatement.setString(4, address.getDistrict());
            preparedStatement.setString(5, address.getStreet());
            preparedStatement.setString(6, address.getApartment());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(address.getDateCreated()));

            preparedStatement.execute();
            close(preparedStatement);

            String readQuery = "SELECT * FROM tb_addresses";

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            savedAddress = new Address();
            savedAddress.setId(resultSet.getLong("id"));
            savedAddress.setState(resultSet.getString("state"));
            savedAddress.setCity(resultSet.getString("city"));
            savedAddress.setRegion(resultSet.getString("region"));
            savedAddress.setDistrict(resultSet.getString("district"));
            savedAddress.setStreet(resultSet.getString("street"));
            savedAddress.setApartment(resultSet.getString("apartment"));
            savedAddress.setDateCreated(resultSet.getTimestamp("data_created").toLocalDateTime());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return savedAddress;
    }

    @Override
    public Address findById(Long id) {
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Address address  = null;

        try {
            connection = getConnection();

            String readQuery = "SELECT * FROM tb_addresses WHERE id = ?";

            preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            address = new Address();
            address.setId(resultSet.getLong("id"));
            address.setState(resultSet.getString("state"));
            address.setCity(resultSet.getString("city"));
            address.setRegion(resultSet.getString("region"));
            address.setDistrict(resultSet.getString("district"));
            address.setStreet(resultSet.getString("street"));
            address.setApartment(resultSet.getString("apartment"));
            address.setDateCreated(resultSet.getTimestamp("data_created").toLocalDateTime());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }

        return address;
    }

    @Override
    public List<Address> getAll() {
        return null;
    }


    private void close(AutoCloseable closeable){
        try {
            System.out.println(closeable.getClass().getSimpleName() + " closing...");
            closeable.close();
            System.out.println(closeable.getClass().getSimpleName() + " closed.");
        }catch (Exception e){
            System.out.println("Could not close " + closeable.getClass().getSimpleName());
            e.printStackTrace();
        }
    }

}


