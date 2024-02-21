package LandingPage;

import Utilities.DataUtil;
import basetest.baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
public class newLandingPageScript_practice extends baseclass {
    @Test(dataProvider = "URLs", dataProviderClass = DataUtil.class)
    public void landingpageverification(String domain) throws InterruptedException, IOException {
        driver.get(domain);
        WebElement home_search = driver.findElement(By.xpath("//a[contains(@href,'search')] | //button[contains(text(),'earch') or contains(text(),'EARCH')] | //button[contains(text(),'JOBS') or contains(text(),'Jobs')]"));
        click(driver, home_search);
        WebElement catDD = driver.findElement(By.xpath("//select[contains(@name,'company')]"));
        Select com_select = new Select(catDD);
        List<WebElement> option = driver.findElements(By.xpath("//select[contains(@name,'company')]//option[starts-with(@value,'A')]"));
        for (WebElement opt : option) {
            String options = opt.getText();
            com_select.selectByValue(options);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            driver.navigate().back();
        }
    }
    @Test
    public void aboutbody(){
        driver.get("https://recruitzusa.com");
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        String about_body = driver.findElement(By.xpath("//body")).getText();
        System.out.println(about_body);
    }
    @Test
    public void wwwTest(){
        driver.get("https://www.recruitzusa.com");
        System.out.println(driver.getCurrentUrl());
    }
}
