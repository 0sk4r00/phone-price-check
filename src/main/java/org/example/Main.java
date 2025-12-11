package org.example;

import org.example.helpFunctions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


// Pamiętaj o ustawieniu zależności Selenium w pliku build.gradle!

public class Main {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // <-- Najważniejsze: tryb bez okna
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Price price = new Price();
        Cookies cookies = new Cookies();
        Battery battery = new Battery();
        String connectionString = System.getenv("MONGO_URL");
        if (connectionString == null || connectionString.isEmpty()) {
            System.out.println("⚠️ Uruchomienie lokalne - używam fallback URL");
            // Tu wklej ten link z hasłem, który masz w notatniku, żeby testować w IntelliJ:
            connectionString = "mongodb+srv://admin:P0XYvSuhtjUOvybU@cluster0.mjspwdq.mongodb.net/?appName=Cluster0";
        }

        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            MongoDatabase database = mongoClient.getDatabase("Sklep");
            MongoCollection<Document> collection = database.getCollection("Iphony");
            driver.get("https://www.refurbed.pl/p/iphone-15/");
            cookies.accept(wait);
            battery.checkAndSetBattery(driver, "Nowy");
            Thread.sleep(2000);
            Variant variant = new Variant();
            variant.checkAndClickLikeAVariant(driver, "Premium");
            Thread.sleep(2000);
            Color color = new Color();
            color.selectCheapestColor(driver);
            Thread.sleep(2000 );
//            variant.checkAndClickLikeAVariant(driver, "Jak nowe");
            Double value = price.checkPrice(driver);
            System.out.println(value);
//            variant.checkAndClickLikeAVariant(driver, "Jak nowe");
            Thread.sleep(2000);
            Document wpisDoBazy = new Document()
                    .append("data", LocalDateTime.now().toString())
                    .append("model", "iPhone 15")
                    .append("wariant", "Premium")
                    .append("bateria", "Nowy")
                    .append("cena", value);
            collection.insertOne(wpisDoBazy);
            // Znajdź wrapper z data-test="product-attribute" i data-label="Wariant"
//            WebElement wrapper = driver.findElement(
//                    By.cssSelector("div[data-test='product-attribute'][data-label='Wariant']")
//            );
//
//// Kliknij dropdown wewnątrz tego wrappera
//            WebElement dropdown = wrapper.findElement(By.cssSelector("div.ss-single-selected"));
//            dropdown.click();
//            WebElement wrapperBattery = driver.findElement(
//                    By.cssSelector("div[data-test='label-title']:has(+ div[data-test='one-option-text'])")
//            ).findElement(By.xpath("following-sibling::div[1]"));
//
//            boolean isDropdown = wrapperBattery.getAttribute("class").contains("selector-wrapper");
//            boolean isStaticText = wrapperBattery.getAttribute("data-test").equals("one-option-text");
//
//            if (isDropdown) {
//                // Kliknij dropdown
//                WebElement dropdownBatterry = wrapperBattery.findElement(By.cssSelector("div.ss-single-selected"));
//                dropdownBatterry.click();
//                if(wrapperBattery.getText().contains("Optymalne")) {
//                    System.out.println("Optymalne");
//                }
//                else {
//                    wrapperBattery.click();
//                    WebElement nowyOption = wrapperBattery.findElement(
//                            By.xpath(".//span[contains(text(), 'Nowy')]/ancestor::div[contains(@class, 'ss-option')]")
//                    );
//                    nowyOption.click();
//                }
//            } else if (isStaticText) {
//                if(wrapperBattery.getText().contains("Optymalne")) {
//                    System.out.println("Optymalne");
//                }
//
//                System.out.println("To nie jest dropdown - tylko jedna opcja dostępna");
//            }
            // Znajdź wrapper z data-test="product-attribute" i data-label="Wariant"

        }
        catch (Exception e) {
            System.out.println("Ostrzeżenie: Nie udało się znaleźć lub kliknąć przycisku do akceptacji plików cookie w ciągu 10s. Kontynuowanie testu.");
             e.printStackTrace(); // Opcjonalnie: odkomentuj, aby zobaczyć pełny błąd
        }
        finally {
            driver.quit();
        }


    }
}