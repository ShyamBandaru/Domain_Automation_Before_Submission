package Tests;

import PageClasses.HomePage;
import PageClasses.SearchPage;
import basetest.baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

public class SearchPageTests extends baseclass {
    @Ignore
    @Test
    public void companyTests() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        hp.clicksearch();
        SearchPage sp = new SearchPage(driver);
        WebElement company = sp.getCompany();
        Select com_list = new Select(company);
        List<WebElement> index = com_list.getOptions();
        for (int i=1;i< index.size();i++){
            com_list.selectByIndex(i);
            sp.clickSearch();
            String window1 = driver.getWindowHandle();
            WebElement job = sp.Job();
            String job_url = job.getAttribute("href");
            if (response(job_url)==200){
                driver.switchTo().newWindow(WindowType.TAB);
                driver.get(job_url);
                driver.close();


            }
            driver.navigate().back();
        }
    }
    @Test
    public void SearchPage_OG_Canonical(){
        HomePage hp = new HomePage(driver);
        hp.clicksearch();
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
    @Test
    public void SearchPage_Count_Verification(){
        HomePage hp = new HomePage(driver);
        hp.clicksearch();
        int JobsCount = driver.findElements(By.xpath("//a[contains(text(),'Apply') or contains(text(),'APPLY')]")).size();
        if (SearchPageCount!=JobsCount){
            assert1.fail("No Of Jobs Listed in Search Page is mismatching"+driver.getCurrentUrl());
        }
        assert1.assertAll();
    }
    @Test
    public void SearchPage_Company_OG_Canonical(){
        HomePage hp = new HomePage(driver);
        hp.clicksearch();
        SearchPage sp = new SearchPage(driver);
        WebElement company = sp.getCompany();
        Select com_list = new Select(company);
        List<WebElement> index = com_list.getOptions();

    }

}
