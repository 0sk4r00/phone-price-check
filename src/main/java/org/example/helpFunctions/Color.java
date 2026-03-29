package org.example.helpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Color {
    public void selectCheapestColor(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement wrapper = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[data-selector='selector-wrapper'][data-label='Kolor']")
        ));
        wrapper.click();
        List<WebElement> options = driver.findElements(
                By.cssSelector("div[data-label='Kolor'] div.ss-list div.ss-option")
        );
        for (WebElement option : options) {
            String text = option.getText();

            System.out.println("Sprawdzam: " + text);

            if (text.contains("-")) {
                System.out.println("✅ Znaleziono pierwszą opcję z minusem! Klikam w: " + text);

                option.click();

                return;
            }
        }

        System.out.println("ℹ️ Nie znaleziono żadnej opcji z minusem. Zamykam listę.");
        wrapper.click();
    }
}