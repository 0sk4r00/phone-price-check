package org.example.helpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Variant {
    public void checkAndClickLikeAVariant(WebDriver driver, String variant) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        try {
            // 1. Sprawdź obecną wartość
            String currentValue = driver.findElement(
                    By.cssSelector("div[data-label='Wariant'] div.ss-single-selected")
            ).getText().trim();
            System.out.println("currentValue" + currentValue);
            if (currentValue.equals(variant)) {
                System.out.println("✅ '" + variant + "' już wybrane");
                return;
            }

//             2. Otwórz dropdown
            driver.findElement(
                    By.cssSelector("div[data-test='product-attribute'][data-label='Wariant']")
            ).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[data-label='Wariant'] div.ss-list")
            ));

            // 3. Sprawdź czy opcja istnieje
        List<WebElement> options = driver.findElements(
                By.xpath("//div[@data-label='Wariant']//span[contains(text(), '" + variant + "')]/ancestor::div[contains(@class, 'ss-option')]")
        );

            if (options.isEmpty()) {
                // Opcja nie istnieje - zamknij dropdown i wyjdź
                System.out.println("⚠️ Opcja '" + variant + "' nie istnieje - pomijam");
                driver.findElement(
                        By.cssSelector("div[data-label='Wariant'] div.ss-single-selected")
                ).click();
                return;  // ← PO PROSTU WYJDŹ
            }

            // 4. Opcja istnieje - kliknij
            wait.until(ExpectedConditions.elementToBeClickable(options.getFirst())).click();
            System.out.println("✅ Wybrano: " + variant);

//        } catch (Exception e) {
//            System.out.println("⚠️ Nie można wybrać '" + variant + "' - pomijam" + e);
//        }
    }
}
