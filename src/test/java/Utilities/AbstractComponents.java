package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AbstractComponents {
    By rightsReserved = By.xpath("//footer//p[contains(text(),'Reserved')]");
    By home = By.xpath("//a[text()='Home']");
    By about = By.xpath("//a[contains(text(),'About')]");
    By contact = By.xpath("//a[contains(text(),'Contact')]");
    By logo = By.xpath("//a[@href='/']");
    By search = By.xpath("//a[@href='/search'] | //button[@type='submit']");
    By companies_link = By.xpath("//a[contains(text(),'Companies')]");
    By terms = By.xpath("//a[contains(text(),'Terms')]");
    By privacy = By.xpath("//a[contains(text(),'Privacy')]");
    By cookie = By.xpath("//a[contains(text(),'Cookie')]");
    WebDriver driver;
    public void clickHome() {
        clickElement(elementStore(home));
    }
    public void logoclick() {
        clickElement(elementStore(logo));
    }
    public void clickabout() {
        clickElement(elementStore(about));
    }
    public void clicksearch(){
        clickElement(elementStore(search));
    }
    public void clickContact(){
        clickElement(elementStore(contact));
    }
    public void clickTerms(){
        clickElement(elementStore(terms));
    }
    public void clickPrivacy(){
        clickElement(elementStore(privacy));
    }
    public void clickCookie(){
        clickElement(elementStore(cookie));
    }
    public void gotoCompanies(){
        clickElement(elementStore(companies_link));
    }
    public String thisurl(){
        return driver.getCurrentUrl();
    }
    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement elementStore(By by) {
        return driver.findElement(by);
    }
    public String returnRightsReservedText(){
        return elementStore(rightsReserved).getText();
    }
    public List<WebElement> elements(By by) {
        return driver.findElements(by);
    }
    public void clickElement(WebElement elem) {
        try {
            elem.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", elem);
        }
    }
    public String Verify_OG_SiteName(){
        return driver.findElement(By.xpath("//meta[@property='og:site_name']")).getAttribute("content");
    }
}
