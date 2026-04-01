package org.example.helpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class Helper {

    public static ScrapingReport findCheapest(WebDriver driver, WebDriverWait wait, Map<String, String> products) {
        double lowestPrice = Double.MAX_VALUE;
        String cheapestColor = "Brak";

        int total = products.size();
        int successCount = 0;

        for (Map.Entry<String, String> entry : products.entrySet()) {
            String id = entry.getKey();
            String kolor = entry.getValue();

            try {
                driver.get("https://www.refurbed.pl/o/" + id + "/");
                Cookies.accept(wait);

                String selector = "#wrapper > div:nth-child(2) > div.row > div.col-12.col-md-8.ml-auto.mr-auto > table > tbody > tr:nth-child(1)";
                WebElement row = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
                row.click();

                double currentPrice = Price.checkPrice(driver);

                successCount++;

                if (currentPrice < lowestPrice) {
                    lowestPrice = currentPrice;
                    cheapestColor = kolor;
                }

            } catch (Exception e) {
                System.err.println("Błąd przy pobieraniu: " + kolor);
            }
        }

        // Obliczanie procentu: (sukcesy / wszystkie) * 100
        double successRate = (total > 0) ? ((double) successCount / total) * 100 : 0;

        System.out.println(String.format(">> Skuteczność: %.2f%%", successRate));
        System.out.println(">> Najtańszy: " + cheapestColor + " (" + lowestPrice + " zł)");

        return new ScrapingReport(cheapestColor, lowestPrice, successRate);
    }
}
