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
        System.out.println(options);

        if (options.isEmpty()) {
            System.out.println("⚠️ Brak opcji do wyboru");
            return;
        }
        WebElement first = options.getFirst();
        String colorName = first.findElement(By.cssSelector("span[data-test-option]")).getText().trim();
        boolean isSelected = first.getAttribute("class").contains("ss-option-selected");
        boolean isDisabled = first.getAttribute("class").contains("ss-disabled");

        // Sprawdź czy można kliknąć
        if (isSelected) {
            System.out.println("✅ Pierwszy element już wybrany - nic nie robię");
            WebElement wrapperOpened = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div.ss-single-selected.ss-open-below")
            ));
            wrapperOpened.click();
            return;
        }

        if (isDisabled) {
            System.out.println("⚠️ Pierwszy element jest wyłączony - nie można kliknąć");
            return;
        }

        // Można kliknąć - kliknij!
        System.out.println("Klikam w: " + colorName);
        wait.until(ExpectedConditions.elementToBeClickable(first));
        first.click();
        System.out.println("✅ Wybrano: " + colorName);
    }
}
