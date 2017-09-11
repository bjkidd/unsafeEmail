package com.automation.webdev.selenium.pages.socialEngineering;

import java.util.ArrayList;
import com.automation.webdev.selenium.ObjectClasses.EmailObject;


public class InboxPage {

    public long timeout =30000;

    //Declare general web elements on the simulation inbox to be used in tests
    private final String pageUrl = "http://testoutlivecontent.blob.core.windows.net/secpro2017v6-en-us/en-us/sims/windows/xsimengine.html?simpackage=windows&simfile=email_social_eng_secpro6.html&dev=true&automation=true";
    private final String inboxPanelId = "mlbMailListBox.Grid.Border.ScrollViewer.Border.Panel.Viewport.ScrollContentPresenter.OuterElement";
    private final String deleteButtonId = "llDelete.LayoutRoot.TextBlock";
    private final String resultsScoreXpath = "//div[contains(@class, 'clsBoxText2') and text()='Your Score: 8 of 8 (100%)']";
    private final String scenarioDoneButtonXpath =".//*[@id='bDone.Grid.contentPresenter.TextBlock']";;
    private final String resultsDoneButtonXpath = ".//*[@id='btDone.Grid.contentPresenter.TextBlock']";
    private final String inboxPanelEmaiXpath = "//div[@data-typename='MailItem']";

    public String getInboxPanelEmailXpath(){
        return inboxPanelEmaiXpath;
    }

    public String getScenarioDoneButtonXpath() {
        return scenarioDoneButtonXpath;
    }

    public String getResultsScoreXpath() {

        return resultsScoreXpath;
    }
    public String getPageUrl() {

        return pageUrl;
    }

    public String getInboxPanelId() {

        return inboxPanelId;
    }

    public String getDeleteButtonWebElementId() {
        return deleteButtonId;
    }

    public String getEmailEntryWebElementXpath(String subjectLine) {
        String emailEntryXpath = "//div[contains(@class, 'clsFrameworkElement') and text()='"+subjectLine+"']";
        return emailEntryXpath;
    }


// Defined Email Specific Element Ids and test data
    public ArrayList<EmailObject> buildExpectedEmailList() {
        ArrayList<EmailObject> ExpectedEmailList = new ArrayList();
        // specify email data in an array of email objects to be used in tests and assertions
        ExpectedEmailList.add(createEmailObject("New Service Pack", "Microsoft Windows Update Center",".//*[@id='MailItem.Grid.cbCheckBox.Grid.Grid.MouseOverElement.Path']/svg",false));
        ExpectedEmailList.add(createEmailObject("Payment Pending", "Online Banking Department","cpContentPresenter.MailItem.Grid.cbCheckBox.Grid.Grid.PressedElement.Border",false));
        ExpectedEmailList.add(createEmailObject("FW: FW: FW: Virus Attack Warning", "Grandma Jacklin ", "InnerElement.ItemsPresenter.StackPanel.ListBoxItem2.Grid.cContentControl.cpContentPresenter.MailItem.Grid.cbCheckBox.Grid.Grid.PressedElement.Path", false));
        ExpectedEmailList.add(createEmailObject("Web Site Update", "Emily Smith","InnerElement.ItemsPresenter.StackPanel.ListBoxItem3.Grid.cContentControl.cpContentPresenter.MailItem.Grid.cbCheckBox.Grid.Grid.PressedElement.Path", false));
        ExpectedEmailList.add(createEmailObject("Wow!!", "Sara Goodwin","InnerElement.ItemsPresenter.StackPanel.ListBoxItem4.Grid.cContentControl.cpContentPresenter.MailItem.Grid.cbCheckBox.Grid.Grid.PressedElement.Border", false));
        ExpectedEmailList.add(createEmailObject("7 Yr Old with Cancer", "Grandma Jacklin","InnerElement.ItemsPresenter.StackPanel.ListBoxItem5.Grid.cContentControl.cpContentPresenter.MailItem.Grid.cbCheckBox.Grid.Grid.PressedElement.Border", false));
        ExpectedEmailList.add(createEmailObject("Re: Lunch Today?", "Joe Davis","InnerElement.ItemsPresenter.StackPanel.ListBoxItem6.Grid.cContentControl.cpContentPresenter.MailItem.Grid.cbCheckBox", false));
        ExpectedEmailList.add(createEmailObject("Executive Jobs", "Executive Recruiting","InnerElement.ItemsPresenter.StackPanel.ListBoxItem8.Grid.cContentControl.cpContentPresenter.MailItem.Grid.cbCheckBox.Grid.Grid.PressedElement.Path", false));
        return ExpectedEmailList;
    }


    private EmailObject createEmailObject(String subject, String sender,String elementId, boolean isSafe) {
        EmailObject email = new EmailObject();
        email.setSubjectLine(subject);
        email.setEmailSender(sender);
        email.setElementId(elementId);
        email.setSafe(isSafe);
        return email;
    }
}
