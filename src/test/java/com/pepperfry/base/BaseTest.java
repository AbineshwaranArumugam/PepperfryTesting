package com.pepperfry.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome")String browser) throws InterruptedException {
    	driver = DriverFactory.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://www.pepperfry.com/");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

