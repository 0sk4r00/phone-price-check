package org.example.helpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Price {
    public static double checkPrice(WebDriver driver){
        WebElement priceElement = driver.findElement(
                By.cssSelector("div[data-test='price-component'] p[data-test='product-price']")
        );
        String price = priceElement.getText();
        price = price.replaceAll("[^0-9,\\.]", "");
        price = price.replace(",", ".");
        return Double.parseDouble(price);
    }
}
