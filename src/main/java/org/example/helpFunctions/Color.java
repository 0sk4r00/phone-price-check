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

// 1. Kliknij w dropdown "Kolor" (otwórz listę)
        WebElement wrapper = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[data-selector='selector-wrapper'][data-label='Kolor']")
        ));
        wrapper.click();
        List<WebElement> options = driver.findElements(
                By.cssSelector("div[data-label='Kolor'] div.ss-list div.ss-option")
        );
// 4. Pętla szukająca PIERWSZEGO minusa
        for (WebElement option : options) {
            String text = option.getText();

            // Debug - żebyś widział w konsoli co sprawdza
            System.out.println("Sprawdzam: " + text);

            // Jeśli tekst zawiera minus "-"
            if (text.contains("-")) {
                System.out.println("✅ Znaleziono pierwszą opcję z minusem! Klikam w: " + text);

                // Klikamy w ten element
                option.click();

                // RETURN jest kluczowy - natychmiast wychodzi z metody,
                // żeby nie klikać w kolejne ani nie zamykać listy na dole.
                return;
            }
        }

        // 5. Jeśli pętla się skończyła i nic nie kliknęliśmy (brak minusów)
        System.out.println("ℹ️ Nie znaleziono żadnej opcji z minusem. Zamykam listę.");
        wrapper.click();
    }
}