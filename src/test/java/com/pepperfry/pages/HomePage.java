package com.pepperfry.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage {
	WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@class='close-modal']")
    WebElement closeModal;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeLoginPopup() {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(closeModal)).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}


