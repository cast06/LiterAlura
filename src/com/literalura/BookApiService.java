package com.literalura;

import okhttp3.*;
import com.google.gson.Gson;
import com.literalura.models.ApiResponse;
import com.literalura.models.BookItem;
import com.literalura.models.VolumeInfo;

import java.io.IOException;

public class BookApiService {

    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public static void fetchBooks(String query) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + query)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                Gson gson = new Gson();

                // Analizar la respuesta JSON
                ApiResponse apiResponse = gson.fromJson(jsonResponse, ApiResponse.class);

                if (apiResponse.getItems() != null) {
                    for (BookItem bookItem : apiResponse.getItems()) {
                        VolumeInfo info = bookItem.getVolumeInfo();
                        String title = info.getTitle();
                        String authors = (info.getAuthors() != null) ? String.join(", ", info.getAuthors()) : "No authors available";
                        String publisher = info.getPublisher();
                        String publishedDate = info.getPublishedDate();

                        // Mostrar en consola
                        System.out.println("Title: " + title);
                        System.out.println("Authors: " + authors);
                        System.out.println("Publisher: " + publisher);
                        System.out.println("Published Date: " + publishedDate);
                        System.out.println("-------------------------------");

                        // Guardar en la base de datos
                        DatabaseManager databaseManager = new DatabaseManager();  // Crear una instancia
                        databaseManager.insertBook(title, authors, publisher, publishedDate);  // Llamar al método no estático
                    }
                } else {
                    System.out.println("No se encontraron libros para la búsqueda: " + query);
                }
            } else {
                System.out.println("Error en la solicitud: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error al realizar la solicitud a la API: " + e.getMessage());
        }
    }
}
