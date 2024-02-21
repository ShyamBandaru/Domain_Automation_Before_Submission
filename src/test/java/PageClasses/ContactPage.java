package PageClasses;

import Utilities.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends AbstractComponents {
    WebDriver driver;
    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    private By mailIdLocator = By.xpath("//a[starts-with(@href,'mailto')]");
    private By nameInputLocator = By.xpath("//input[@name='name']");
    private By subjectInputLocator = By.xpath("//input[@name='subject']");
    private By emailInputLocator = By.xpath("//input[@name='email']");
    private By phoneInputLocator = By.xpath("//input[@name='phone']");
    private By messageInputLocator = By.xpath("//textarea[@name='message']");
    private By submitButtonLocator = By.xpath("//button[@type='submit']");
    private By nameErrorMessage = By.xpath("//label[@id='name-error']");
    private By subjectErrorMessage = By.xpath("//label[@id='subject-error']");
    private By emailErrorMessage = By.xpath("//label[@id='email-error']");
    private By phoneErrorMessage = By.xpath("//label[@id='phone-error']");
    private By successMessage = By.xpath("//div[@id='successmsg']");

    public void clickSubmit() {
        clickElement(elementStore(submitButtonLocator));
    }
    public void sendName(String s) {
        elementStore(nameInputLocator).sendKeys(s);
    }

    public void sendSubject(String s) {
        elementStore(subjectInputLocator).sendKeys(s);
    }

    public void sendEmail(String s) {
        elementStore(emailInputLocator).sendKeys(s);
    }

    public void sendContact(String s) {
        elementStore(phoneInputLocator).sendKeys(s);
    }

    public boolean NameErrorValidator() {
        boolean i;
        if (!elementStore(nameErrorMessage).isDisplayed()) {
            System.out.println("Name Validation Message is not displayed");
            i=false;
        }
        else {
            i=true;
        }
        return i;
    }
    public boolean SubjectErrorValidator() {
        boolean i ;
        if (!elementStore(subjectErrorMessage).isDisplayed()) {
            System.out.println("Subject Validation Message is not displayed");
            i=false;
        }
        else {
            i=true;
        }
        return i;
    }
    public boolean EmailErrorValidator() {
        boolean i ;
        if (!elementStore(emailErrorMessage).isDisplayed()) {
            System.out.println("Subject Validation Message is not displayed");
            i=false;
        }
        else {
            i=true;
        }
        return i;
    }
    public boolean PhoneErrorValidator() {
        boolean i ;
        if (!elementStore(phoneErrorMessage).isDisplayed()) {
            System.out.println("Subject Validation Message is not displayed");
            i=false;
        }
        else {
            i=true;
        }
        return i;
    }
    public boolean SuccessMessageValidator(){
        boolean i;
        if (!elementStore(successMessage).isDisplayed()) {
            System.out.println("Name Validation Message is not displayed");
            i=false;
        }
        else {
            i=true;
        }
        return i;
    }
}
