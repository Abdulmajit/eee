package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao <Model>{
    Model save(Model model);
    Model findById(Long id);
    List<Model> getAll();

    default Connection getConnection() throws SQLException{
        final String URL = "jdbc:postgresql://localhost:5432/crm";
        final String USERNAME = "postgres";
        final String PASSWORD = "5566";

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
