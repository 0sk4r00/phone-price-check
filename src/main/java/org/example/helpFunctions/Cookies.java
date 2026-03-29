package org.example.helpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cookies {
    public void accept(WebDriverWait wait) {
        By cookieButtonSelector = By.xpath("//span[text()='Akceptuj wszystkie pliki cookie']");

        try {
            WebElement acceptButton = wait
                    .withTimeout(Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(cookieButtonSelector));

            acceptButton.click();
            System.out.println("Ciasteczka zaakceptowane.");
        } catch (TimeoutException e) {
            System.out.println("Okno cookies nie pojawiło się – pomijam.");
        }
    }
}
