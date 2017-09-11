package com.automation.webdev.selenium;

import com.automation.webdev.selenium.pages.socialEngineering.InboxPage;
import com.automation.webdev.selenium.ObjectClasses.EmailObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.*;
import org.testng.annotations.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SocialEngineeringModuleTests extends InboxPage {

    private WebDriver driver;
    private ArrayList<EmailObject> expectedEmailItems = new ArrayList();

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(getPageUrl());
    }
    @AfterClass
    public void tearDown(){

        driver.close();
    }

    @Test
    public void processSocialEngineeringEmailsTest()throws Exception {
        System.out.println("Starting test: "+ getTestName());

        //Simulation startup and page load and obtain web elements for all emails
        verifySimulationStartup();

        //Iterate through the email list and delete unsafe emails
        deleteUnsafeEmails();

        // Use the expected text when finding the WebElement on results page
        ExpectedConditions.presenceOfElementLocated(By.xpath(getResultsScoreXpath()));

        System.out.println("Ending test: "+ getTestName());
    }

    //Test Helper Methods

    public String getTestName(){
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace[2].getMethodName();
    }
    public void verifySimulationStartup() throws Exception {
        long emailCount = 0;
        Thread.sleep(timeout);
        try {
            // Verify that the inbox panel is loaded.
            ExpectedConditions.presenceOfElementLocated(By.id(getInboxPanelId()));
            System.out.println("Simulation scenario loaded.");
        }
        catch (Exception e) {
            System.out.println("Failure to load simulation within the expected " + timeout + " (ms). Throws Exception: " + e);
        }
//         Identify Emails in the inbox for processing
        WebElement inboxPanel = driver.findElement(By.id(getInboxPanelId()));
        List<WebElement> emails = inboxPanel.findElements(By.xpath(getInboxPanelEmailXpath()));

        //Verify that the expected count of emails are displayed in the inbox.
        emailCount = emails.size();
        Assert.assertEquals(12, emailCount);
    }

    public void deleteUnsafeEmails() {
        Actions action = new Actions(driver);
        expectedEmailItems = buildExpectedEmailList();
        int emailsProcessed = 0;
        for (int i = emailsProcessed; i < expectedEmailItems.size(); i++) {
            if (expectedEmailItems.get(i).getIsSafe() == false) {
                try {
                    //driver.findElement(By.xpath(expectedEmailItems.get(i).getElementId())).click();
                    driver.findElement(By.xpath(getEmailEntryWebElementXpath(expectedEmailItems.get(i).getSubjectLine()))).click();
                    driver.findElement(By.id(getDeleteButtonWebElementId())).click();
                    System.out.println("Email with subject line: " + expectedEmailItems.get(i).getSubjectLine() + " has been deleted");
                } catch (Exception e) {
                    System.out.println("Failed to select email: '" + expectedEmailItems.get(i).getSubjectLine() + "' for deletion. Exception: " + e);
                }
            }

        }
        // Identify and Press the done button
        driver.findElement(By.xpath(getScenarioDoneButtonXpath())).click();
    }
}
 