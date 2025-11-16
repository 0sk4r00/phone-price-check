package org.example.helpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cookies {
    public void accept(WebDriverWait wait){
        By cookieButtonSelector = By.xpath("//span[text()='Akceptuj wszystkie pliki cookie']");
        WebElement acceptButton = wait.until(
                ExpectedConditions.elementToBeClickable(cookieButtonSelector)
        );
        assert acceptButton != null;
        acceptButton.click();
    }
}
