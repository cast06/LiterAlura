package com.literalura;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createTable(); // Crear la tabla si no existe

        // Insertar libros de ejemplo al iniciar el programa
        insertSampleBooks(databaseManager);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido a LiterAlura");
            System.out.println("1. Buscar libros en la API");
            System.out.println("2. Mostrar libros almacenados en la base de datos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (option == 1) {
                System.out.print("Ingrese el título o autor para buscar: ");
                String query = scanner.nextLine();
                BookApiService.fetchBooks(query); // Buscar libros en la API
            } else if (option == 2) {
                System.out.println("Libros almacenados en la base de datos:");
                databaseManager.queryBooks(); // Consultar libros en la base de datos
            } else if (option == 3) {
                System.out.println("¡Hasta luego!");
                break;
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }


    private static void insertSampleBooks(DatabaseManager databaseManager) {
        databaseManager.insertBook("El señor de los anillos", "J.R.R. Tolkien", "Minotauro", "1954-07-29");
        databaseManager.insertBook("Cien años de soledad", "Gabriel García Márquez", "Sudamericana", "1967-05-30");
        databaseManager.insertBook("1984", "George Orwell", "Secker & Warburg", "1949-06-08");
        databaseManager.insertBook("Don Quijote de la Mancha", "Miguel de Cervantes", "Francisco de Robles", "1605-01-16");
        databaseManager.insertBook("Orgullo y prejuicio", "Jane Austen", "T. Egerton", "1813-01-28");
    }
}
