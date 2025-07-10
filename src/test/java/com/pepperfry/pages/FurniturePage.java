package com.pepperfry.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FurniturePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "Furniture")
    WebElement furnitureMenu;

    @FindBy(linkText = "Settees & Benches")
    WebElement setteesAndBenches;

    public FurniturePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToSetteesAndBenches() throws InterruptedException {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	Thread.sleep(500);
        Actions actions = new Actions(driver);
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(furnitureMenu))).perform();
        wait.until(ExpectedConditions.elementToBeClickable(setteesAndBenches)).click();
    }
}