//package com.pepperfry.pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.*;
//
//public class BenchesPage {
//    WebDriver driver;
//    WebDriverWait wait;
//
//    @FindBy(xpath = "//div[normalize-space()='Benches']")
//    WebElement benchesCategory;
//
//    @FindBy(xpath = "//button[@id='Material']")
//    WebElement materialFilter;
//
//    @FindBy(xpath = "//label[normalize-space()='Metal']")
//    WebElement metalCheckbox;
//
//    @FindBy(xpath = "//span[normalize-space()='APPLY']")
//    WebElement applyButton;
//
//    @FindBy(xpath = "/html/body/app-root/main/app-category/pf-clip/div/div[2]/pf-clip-product-listing/div[1]/div/span[2]")
//    WebElement metalBenchCount;
//
//    public BenchesPage(WebDriver driver, WebDriverWait wait) {
//        this.driver = driver;
//        this.wait = wait;
//        PageFactory.initElements(driver, this);
//    }
//
//    public void clickBenchesCategory() {
//        benchesCategory.click();
//    }
//
//    public void filterByMetalMaterial() {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", materialFilter);
//        wait.until(ExpectedConditions.elementToBeClickable(materialFilter)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(metalCheckbox)).click();
//        applyButton.click();
//    }
//
//    public int getMetalBenchCount() {
//        wait.until(ExpectedConditions.visibilityOf(metalBenchCount));
//        String countText = metalBenchCount.getText().replaceAll("[^0-9]", "");
//        return Integer.parseInt(countText);
//    }
//
//    public WebElement getMetalBenchCountElement() {
//        return metalBenchCount;
//    }
//}



package com.pepperfry.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class BenchesPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[normalize-space()='Benches']")
    WebElement benchesCategory;

    @FindBy(xpath = "//button[@id='Material']")
    WebElement materialFilter;

    @FindBy(xpath = "//label[normalize-space()='Metal']")
    WebElement metalCheckbox;

    @FindBy(xpath = "//span[normalize-space()='APPLY']")
    WebElement applyButton;

    @FindBy(xpath = "/html/body/app-root/main/app-category/pf-clip/div/div[2]/pf-clip-product-listing/div[1]/div/span[2]")
    WebElement metalBenchCount;

    @FindBy(xpath = "//*[@id='clip-heder-desktop']/div/owl-carousel-o/div/div[1]/owl-stage/div/div/div[3]/pf-clip-category-list/div/a/div/div[2]")
    WebElement benchesCountElement;

    @FindBy(xpath = "//*[@id='clip-heder-desktop']/div/owl-carousel-o/div/div[1]/owl-stage/div/div/div[2]/pf-clip-category-list/div/a/div/div[2]")
    WebElement setteesCountElement;

    @FindBy(xpath = "//*[@id='clip-heder-desktop']/div/owl-carousel-o/div/div[1]/owl-stage/div/div/div[4]/pf-clip-category-list/div/a/div/div[2]")
    WebElement recamiersCountElement;

    public BenchesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickBenchesCategory() {
        benchesCategory.click();
    }

    public void filterByMetalMaterial() throws InterruptedException {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", materialFilter);
        Thread.sleep(800);
        wait.until(ExpectedConditions.elementToBeClickable(materialFilter)).click();
        wait.until(ExpectedConditions.elementToBeClickable(metalCheckbox)).click();
        applyButton.click();
    }

    public int getMetalBenchCount() {
        wait.until(ExpectedConditions.visibilityOf(metalBenchCount));
        String countText = metalBenchCount.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(countText);
    }

    public WebElement getMetalBenchCountElement() {
        return metalBenchCount;
    }

    public int getBenchesCount() {
        wait.until(ExpectedConditions.visibilityOf(benchesCountElement));
        String text = benchesCountElement.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(text);
    }

    public int getSetteesCount() {
        wait.until(ExpectedConditions.visibilityOf(setteesCountElement));
        String text = setteesCountElement.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(text);
    }

    public int getRecamiersCount() {
        wait.until(ExpectedConditions.visibilityOf(recamiersCountElement));
        String text = recamiersCountElement.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(text);
    }
}
