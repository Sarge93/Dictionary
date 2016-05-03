package ru.medvedev.dictionary.dao;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Сергей on 02.05.2016.
 */
public abstract class OracleController<E, K> implements Controller<E, K> {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "admin";

    private Connection connection;

    public OracleController() {

        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public abstract List<E> getAll();
    public abstract boolean update(K id, K field, K value);
    public abstract E getEntityByName(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);
}
