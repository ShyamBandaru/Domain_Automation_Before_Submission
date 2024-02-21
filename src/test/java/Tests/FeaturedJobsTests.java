package Tests;

import PageClasses.HomePage;
import basetest.baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class FeaturedJobsTests extends baseclass {
    @Test
    public void FJPageCount(){
        HomePage hp = new HomePage(driver);
        hp.click_ba_featuredJobs();
        List<WebElement> AllJobs = driver.findElements(By.xpath("//a[contains(text(),'Apply')]"));
        if(AllJobs.size()!=ListingPageCount){
            assert1.fail("Invalid Count of Jobs are displaying in Featured Jobs Page "+hp.thisurl());
        }
        assert1.assertAll();
    }
    @Test
    public void FJPageTest(){
        HomePage hp = new HomePage(driver);
        hp.click_ba_featuredJobs();
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
}
