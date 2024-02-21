package PageClasses;

import Utilities.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractComponents {
    WebDriver driver;
    By featuredjobs_browseall = By.xpath("//*[contains(text(),'Featured') or contains(text(),'FEATURED')]//following::a[contains(text(),'All') or contains(text(),'ALL')]");
    By companies_browseall = By.xpath("//span[contains(text(),'Companies')]//following::a[contains(text(),'All') or contains(text(),'ALL')] | //a[contains(text(),'Companies')]");
    By AppliesOnHome = By.xpath("//a[contains(text(),'Apply') or contains(text(),'ALL')]");
    public void click_ba_companies(){
        clickElement(elementStore(companies_browseall));
    }
    public void click_ba_featuredJobs(){
        clickElement(elementStore(featuredjobs_browseall));
    }
    public void click_FirstJobListed(){
        clickElement(elementStore(AppliesOnHome));
    }
    public HomePage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}
