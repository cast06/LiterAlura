package com.literalura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:books.db";

    // Crear tabla si no existe
    public void createTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, " +
                    "author TEXT, " +
                    "publisher TEXT, " +
                    "publishedDate TEXT)";
            statement.execute(createTableSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insertar libro en la base de datos
    public void insertBook(String title, String author, String publisher, String publishedDate) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO books (title, author, publisher, publishedDate) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, publisher);
            preparedStatement.setString(4, publishedDate);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Consultar todos los libros de la base de datos
    public void queryBooks() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Título: " + resultSet.getString("title"));
                System.out.println("Autor: " + resultSet.getString("author"));
                System.out.println("Editorial: " + resultSet.getString("publisher"));
                System.out.println("Fecha de publicación: " + resultSet.getString("publishedDate"));
                System.out.println("-------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
