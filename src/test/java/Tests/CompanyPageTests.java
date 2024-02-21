package Tests;

import PageClasses.HomePage;
import basetest.baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CompanyPageTests extends baseclass {
    @Test
    public void CompanyPage_CompaniesPage_Validation() throws IOException {
        HomePage hp = new HomePage(driver);
        hp.click_ba_companies();
        List<WebElement> TotalLinks = driver.findElements(By.tagName("a"));
        if (TotalLinks.size()>LinkCount) {
            List<WebElement> Companies = driver.findElements(By.xpath("//p//child::a"));
            String window1 = driver.getWindowHandle();
            for (WebElement company : Companies) {
                String companyLink = company.getAttribute("href");
                driver.switchTo().newWindow(WindowType.TAB);
                driver.get(companyLink);
                int links = driver.findElements(By.tagName("a")).size();
                if (links>LinkCount){
                int Jobs = driver.findElements(By.xpath("//a[contains(text(),'Apply')]")).size();
                if(!(ListingPageCount >=Jobs)){
                    assert1.fail("More Jobs are Listed in Page "+hp.thisurl());
                    assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
                    assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
                    takescreenshot(driver,"Company");
                }
                }
                else {
                    assert1.fail("No Jobs are displaying in "+driver.getCurrentUrl());
                }
                driver.close();
                driver.switchTo().window(window1);
            }
        }
        else {
            assert1.fail("No Companies are displaying in the Companies Page");
        }
        assert1.assertAll();
    }
    @Test
    public void CompaniesPage_OGURL_Canonical(){
        HomePage hp = new HomePage(driver);
        hp.click_ba_companies();
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
}
