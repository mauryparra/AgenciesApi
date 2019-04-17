package com.meli.itacademy;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meli.itacademy.models.Agency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Entrada Api propia que llama a API Mercado Libre
        get("/agencias", (request, response) -> {
            response.type("application/json");
            Agency[] agencies;

            if (request.queryParams("site_id").equals("") ||
                    request.queryParams("payment_method_id").equals("")
            ) {
                return new Gson().toJson("Parametros faltantes");
            }

            try {
                String data = readUrl("https://api.mercadolibre.com/sites/" +
                        request.queryParams("site_id") +
                        "/payment_methods/" +
                        request.queryParams("payment_method_id") +
                        "/agencies?near_to=" + request.queryParams("near_to") +
                        "&limit=" + request.queryParams("limit") +
                        "&offset=" + request.queryParams("offset") +
                        "&order_by" + request.queryParams("order_by")
                );

                JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();

                agencies = new Gson().fromJson(jsonObject.get("results"), Agency[].class);

                return new Gson().toJsonTree(agencies);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ocurrio un error al obtener las agencias");
                return new Gson().toJson("Ocurrio un error al obtener las agencias");
            }
        });
    }

    private static String readUrl(String urlString) throws IOException {

        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

            int read;
            StringBuilder buffer = new StringBuilder();
            char[] chars = new char[1024];

            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
