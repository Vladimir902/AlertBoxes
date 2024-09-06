package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args)  {

        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/alerts");

        driver.manage().window().maximize();

        //clicking on alertbox and clicking OK
        try {

            WebElement element = driver.findElement(By.id("alertButton"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            if (element == null) {
                throw new IllegalArgumentException("There is no such alert box");
            }
            driver.switchTo().alert().accept();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        //Clicking on timer Alert Button and waiting for the box to be present 10 seconds
        try {
            driver.findElement(By.id("timerAlertButton")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert timerAlert = driver.switchTo().alert();
            timerAlert.accept();
        } catch (TimeoutException e) {
            System.out.println("Timed alert did not appear within 10 seconds: " + e.getMessage());
        }

        //Locating confirm Button and clicking accept(OK) on alert box
        try {
            driver.findElement(By.id("confirmButton")).click();
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("Confirmation alert not present: " + e.getMessage());
        }

        /*Locating prompt Button, clicking it and sending value inside of it then
        clicking confirm (OK)
         */
        try {
            WebElement element = driver.findElement(By.id("promtButton"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
            Alert promptAlert = driver.switchTo().alert();
            promptAlert.sendKeys("Vladimir");
            promptAlert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("Prompt alert not present: " + e.getMessage());
        }


    }
}