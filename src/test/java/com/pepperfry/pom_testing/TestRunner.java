//package com.pepperfry.pom_testing;

//import com.pepperfry.pages.*;
//import com.pepperfry.utils.*;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Test;
//
//import java.time.Duration;
//
//public class TestRunner {
//    WebDriver driver;
//    WebDriverWait wait;
//    HomePage homePage;
//    FurniturePage furniturePage;
//    BenchesPage benchesPage;
//    int benchesCount, setteesCount, recamiersCount, metalBenchesCount;
//
//    @Optional("chrome") 
//    @Parameters("browser")
//    @BeforeClass
//    public void diverSetup(String browser) {
//        if (browser.equalsIgnoreCase("edge")) {
//            driver = new EdgeDriver();
//        } else { 
//            driver = new ChromeDriver();
//        }
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        driver.get("https://www.pepperfry.com/");
//        homePage = new HomePage(driver, wait);
//        furniturePage = new FurniturePage(driver, wait);
//        benchesPage = new BenchesPage(driver, wait);
//    }
//
//    @Test
//    public void validateTitle() {
//    	homePage.closeLoginPopup();
//        String title = homePage.getPageTitle();
//        Assert.assertTrue(title.contains("Online Furniture Shopping Store"), "Title validation failed!");
//        System.out.println("Page Title: " + title);
//    }
//
//    @Test
//    public void navigateToSetteesAndBenches() {
//        furniturePage.navigateToSetteesAndBenches();
//    }
//
//    @Test
//    public void displayFurnitureTypes() {
//    	benchesCount = benchesPage.getBenchesCount();
//    	setteesCount = benchesPage.getSetteesCount();
//    	recamiersCount = benchesPage.getRecamiersCount();
//        System.out.println("Benches Count: " + benchesCount);
//        System.out.println("Settees Count: " + setteesCount);
//        System.out.println("Recamiers Count: " + recamiersCount);
//        
//    }
//
//    @Test
//    public void filterByMetalBenches() {
//        benchesPage.clickBenchesCategory();
//        benchesPage.filterByMetalMaterial();
//    }
//
//    @Test
//    public void validateMetalBenches() throws InterruptedException {
//        metalBenchesCount = benchesPage.getMetalBenchCount();
//        System.out.println("Metal Benches Count: " + metalBenchesCount);
//        WebElement countElement = benchesPage.getMetalBenchCountElement();
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countElement);
//        Thread.sleep(1000);
//        ScreenshotUtils.takeScreenshot(driver);
//        
//        ExcelUtils.writeFurnitureCounts(benchesCount, setteesCount, recamiersCount, metalBenchesCount);
//    }
//
//    @AfterClass
//    public void closeBrowser() {
//        driver.quit();
//    }
//}



package com.pepperfry.pom_testing;

import com.pepperfry.pages.*;
import com.pepperfry.utils.*;
import com.pepperfry.base.BaseTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestRunner extends BaseTest {
    HomePage homePage;
    FurniturePage furniturePage;
    BenchesPage benchesPage;
    int benchesCount, setteesCount, recamiersCount, metalBenchesCount;

    @Test(priority = 1)
    public void validateTitle() {
    	SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        homePage.closeLoginPopup();
        String title = homePage.getPageTitle();
        String expectedTitle = "Buy Furniture & Home Decor Online – Up to 60% Off at Best Prices in India | Pepperfry";
        System.out.println("Page Title: " + title);
        softAssert.assertEquals(title, expectedTitle, "Title validation failed!");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void navigateToSetteesAndBenches() throws InterruptedException {
    	SoftAssert softAssert = new SoftAssert();
        furniturePage = new FurniturePage(driver);
        furniturePage.navigateToSetteesAndBenches();
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        softAssert.assertTrue(currentUrl.contains("settees-and-benches"), "Navigation failed!");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void displayFurnitureTypes() {
    	SoftAssert softAssert = new SoftAssert();
        benchesPage = new BenchesPage(driver);
        benchesCount = benchesPage.getBenchesCount();
        setteesCount = benchesPage.getSetteesCount();
        recamiersCount = benchesPage.getRecamiersCount();

        System.out.println("Benches Count: " + benchesCount);
        System.out.println("Settees Count: " + setteesCount);
        System.out.println("Recamiers Count: " + recamiersCount);

        softAssert.assertTrue(benchesCount > 0, "Benches count should be greater than 0");
        softAssert.assertTrue(setteesCount > 0, "Settees count should be greater than 0");
        softAssert.assertTrue(recamiersCount > 0, "Recamiers count should be greater than 0");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void filterByMetalBenches() throws InterruptedException {
        benchesPage.clickBenchesCategory();
        benchesPage.filterByMetalMaterial();
    }

    @Test(priority = 5)
    public void validateMetalBenches() throws InterruptedException {
    	SoftAssert softAssert = new SoftAssert();
        metalBenchesCount = benchesPage.getMetalBenchCount();
        System.out.println("Metal Benches Count: " + metalBenchesCount);
        
		softAssert.assertTrue(metalBenchesCount < benchesCount, "Metal benches count should be greater than 0");
		softAssert.assertTrue(metalBenchesCount > 0, "Metal benches count should be lower than benches count");
        WebElement countElement = benchesPage.getMetalBenchCountElement();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countElement);
        Thread.sleep(1000);
        ScreenshotUtils.takeScreenshot(driver);
        ExcelUtils.writeFurnitureCounts(benchesCount, setteesCount, recamiersCount, metalBenchesCount);
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void compareBenchAndMetalBenchCount() {
    	SoftAssert softAssert = new SoftAssert();
    	softAssert.assertTrue(metalBenchesCount <= benchesCount, "Filtered metal benches should not exceed total benches");
    	softAssert.assertAll();
    }
}

