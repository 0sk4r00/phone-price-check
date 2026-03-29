package org.example.helpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class  Battery{
    public void checkAndSetBattery(WebDriver driver, String option){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String currentValue = driver.findElement(
                By.cssSelector("div[data-label='Wybierz baterię'] div.ss-single-selected")
        ).getText().trim();
        System.out.println("currentValue" + currentValue);
        if (currentValue.equals(option)) {
            System.out.println("✅ '" + option + "' już wybrane");
            return;
        }

        driver.findElement(
                By.cssSelector("div[data-test='product-attribute'][data-label='Wybierz baterię']")
        ).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[data-label='Wybierz baterię'] div.ss-list")
        ));

        List<WebElement> options = driver.findElements(
                By.xpath("//div[@data-label='Wybierz baterię']//span[contains(text(), '" + option + "')]/ancestor::div[contains(@class, 'ss-option')]")
        );

        if (options.isEmpty()) {
            System.out.println("⚠️ Opcja '" + option + "' nie istnieje - pomijam");
            driver.findElement(
                    By.cssSelector("div[data-label='Wariant'] div.ss-single-selected")
            ).click();
            return;
        }
        wait.until(ExpectedConditions.elementToBeClickable(options.getFirst())).click();
        System.out.println("✅ Wybrano: " + option);
    }
}
