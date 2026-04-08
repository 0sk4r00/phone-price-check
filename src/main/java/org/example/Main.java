package org.example;

import org.example.helpFunctions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;



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
            ScrapingReport scrapingReport = Helper.findCheapest(driver, wait, iphone15);
            Mongo.saveDataInDb("iphone 15", scrapingReport.bestPrice(), scrapingReport.successRate(),scrapingReport.bestColor());
            ScrapingReport scrapingReport1 = Helper.findCheapest(driver, wait, iphone15Pro);
            Mongo.saveDataInDb("iphone 15 pro", scrapingReport1.bestPrice(), scrapingReport1.successRate(),scrapingReport1.bestColor());

        } finally {
            driver.quit();
        }





    }
}