package org.example;

import org.example.helpFunctions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;


// Pamiętaj o ustawieniu zależności Selenium w pliku build.gradle!

public class Main {
    static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            driver.get("https://www.refurbed.pl/p/iphone-15/");
            Cookies cookies = new Cookies();
            cookies.accept(wait);
            Variant variant = new Variant();
            variant.checkAndClickLikeAVariant(driver, "Premium");
            Thread.sleep(2000);
            Battery battery = new Battery();
            battery.checkAndSetBattery(driver, "Nowy");
            Thread.sleep(2000);
            Color color = new Color();
            color.selectCheapestColor(driver);
            Thread.sleep(2000 );
//            variant.checkAndClickLikeAVariant(driver, "Jak nowe");
            Price price = new Price();
            price.checkPrice(driver);
            variant.checkAndClickLikeAVariant(driver, "Jak nowe");
            Thread.sleep(2000);

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