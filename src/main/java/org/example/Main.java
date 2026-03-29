package org.example;

import org.example.helpFunctions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new"); // <-- Najważniejsze: tryb bez okna
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Price price = new Price();
        Cookies cookies = new Cookies();
        Map<String, String> iphone15 = Map.ofEntries(
                Map.entry("98177", "niebieski"),
                Map.entry("98185", "zielony"),
                Map.entry("98154", "czarny")
        );

        iphone15.forEach((id, kolor) -> {
            try {
                System.out.println("Sprawdzam kolor: " + kolor + " (ID: " + id + ")");

                driver.get("https://www.refurbed.pl/o/" + id + "/");

                cookies.accept(wait);

                String selector = "#wrapper > div:nth-child(2) > div.row > div.col-12.col-md-8.ml-auto.mr-auto > table > tbody > tr:nth-child(1)";
                WebElement row = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));

                row.click();

                double value = price.checkPrice(driver);
                System.out.println("Cena dla " + kolor + ": " + value + " zl");

            } catch (Exception e) {
                System.err.println("Błąd przy ID " + id + ": " + e.getMessage());
            }
        });
        driver.quit();
//        String connectionString = System.getenv("MONGO_URL");
//        if (connectionString == null || connectionString.isEmpty()) {
//            System.out.println("⚠️ Uruchomienie lokalne - używam fallback URL");
//            // Tu wklej ten link z hasłem, który masz w notatniku, żeby testować w IntelliJ:
//            connectionString = "sth";
//        }
//
//        try (MongoClient mongoClient = MongoClients.create(connectionString)){
//            MongoDatabase database = mongoClient.getDatabase("Sklep");
//            MongoCollection<Document> collection = database.getCollection("Iphony");
//            driver.get("https://www.refurbed.pl/p/iphone-15/");
//            cookies.accept(wait);
//            battery.checkAndSetBattery(driver, "Nowy");
//            Thread.sleep(2000);
//            Variant variant = new Variant();
//            variant.checkAndClickLikeAVariant(driver, "Premium");
//            Thread.sleep(2000);
//            Color color = new Color();
//            color.selectCheapestColor(driver);
//            Thread.sleep(2000 );
////            variant.checkAndClickLikeAVariant(driver, "Jak nowe");
//            Double value = price.checkPrice(driver);
//            System.out.println(value);
////            variant.checkAndClickLikeAVariant(driver, "Jak nowe");
//            Thread.sleep(2000);
//            Document wpisDoBazy = new Document()
//                    .append("data", LocalDateTime.now().toString())
//                    .append("model", "iPhone 15")
//                    .append("wariant", "Premium")
//                    .append("bateria", "Nowy")
//                    .append("cena", value);
//            collection.insertOne(wpisDoBazy);
//
//        }
//        catch (Exception e) {
//            System.out.println("Ostrzeżenie: Nie udało się znaleźć lub kliknąć przycisku do akceptacji plików cookie w ciągu 10s. Kontynuowanie testu.");
//             e.printStackTrace(); // Opcjonalnie: odkomentuj, aby zobaczyć pełny błąd
//        }
//        finally {
//            driver.quit();
//        }


    }
}