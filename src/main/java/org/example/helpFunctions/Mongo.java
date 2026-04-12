package org.example.helpFunctions;
import com.mongodb.ConnectionString;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.time.Instant;

import java.util.Date;

public class Mongo {
    public static void saveDataInDb(String model, double price, double successRate, String color) {
        Dotenv dotenv = Dotenv.load();

        String user = System.getenv("MONGO_USER");
        String pass = System.getenv("MONGO_PASS");

        if (user == null || user.isEmpty()) {
            user = dotenv.get("MONGO_USER");
        }

        if (pass == null || pass.isEmpty()) {
            pass = dotenv.get("MONGO_PASS");
        }

        String connectionString = String.format("mongodb+srv://%s:%s@cluster0.mjspwdq.mongodb.net/?appName=Cluster0",user, pass);
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();



        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("Sklep");
            MongoCollection<Document> collection = database.getCollection("Iphony");
            // Wysyłamy ping do bazy, żeby sprawdzić czy działa
            Document produkt = new Document()
                    .append("nazwa", model)
                    .append("cena", price)
                    .append("successRate", successRate)
                    .append("color", color)
                    .append("data", Instant.now());

            // 4. Zapis do bazy
            collection.insertOne(produkt);
        } catch (Exception e) {
            System.err.println("Błąd połączenia z chmurą: " + e.getMessage());
        }
    }
}
