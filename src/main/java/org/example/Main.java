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
import java.time.LocalDateTime;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.helpFunctions.Helper.findCheapest;


public class Main {
    static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Map<String, String> iphone15 = Map.ofEntries(
                Map.entry("98177", "niebieski"),
                Map.entry("98185", "zielony"),
                Map.entry("98154", "czarny")
        );
        Map<String, String> iphone15Pro = Map.ofEntries(
                Map.entry("98114", "bialy"),
                Map.entry("98083", "czarny"),
                Map.entry("98119", "niebieski")
        );

        try {
            System.out.println("--- SPRAWDZANIE IPHONE 15 ---");
            ScrapingReport scrapingReport = Helper.findCheapest(driver, wait, iphone15);
            System.out.println(scrapingReport.bestColor());
            System.out.println(scrapingReport.bestPrice());
//            Document wpisDoBazy = new Document()
//                    .append("data", LocalDateTime.now().toString())
//                    .append("color", priceResult.color())
//                    .append("model", "iPhone 15")
//                    .append("wariant", "Jak nowe")
//                    .append("bateria", "Optymalny")
//                    .append("cena", priceResult.price());
//            collection.insertOne(wpisDoBazy);

            System.out.println("\n--- SPRAWDZANIE IPHONE 15 PRO ---");
            ScrapingReport scrapingReport1 = Helper.findCheapest(driver, wait, iphone15Pro);
//            Document wpisDoBazy = new Document()
//                    .append("data", LocalDateTime.now().toString())
//                    .append("model", "iPhone 15")
//                    .append("wariant", "Premium")
//                    .append("bateria", "Nowy")
//                    .append("cena", value);
//            collection.insertOne(wpisDoBazy);

        } finally {
            driver.quit();
        }





    }
}