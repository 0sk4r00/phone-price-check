package org.example;

import org.example.helpFunctions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

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

        try{
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
            price.checkPrice(driver);
//            variant.checkAndClickLikeAVariant(driver, "Jak nowe");
            Thread.sleep(2000); ;

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