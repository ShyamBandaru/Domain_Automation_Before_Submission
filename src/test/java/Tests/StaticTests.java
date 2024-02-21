package Tests;

import PageClasses.HomePage;
import basetest.baseclass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class StaticTests extends baseclass {
    @Test
    public void About_Static(){
        driver.get(url);
        HomePage hp = new HomePage(driver);
        hp.clickabout();
        String About_Content = driver.findElement(By.xpath("//body")).getText();
        assert1.assertFalse(Static_Content_LU_Verifier(About_Content),"Last Updated Word does exist in About Page of Domain -"+url);
        assert1.assertFalse(Static_Content_MD_Verifier(About_Content),"My Domain Word does exist in About Page of Domain -"+url);
        assert1.assertFalse(Static_Content_HTTPS_Verifier(About_Content),"Https word does exist in About Page of Domain -"+url);
        assert1.assertFalse(Static_Content_Website_Verifier(About_Content),"Website word does exist in About Page of Domain -"+url);
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
    @Test
    public void Terms_Static(){
        driver.get(url);
        HomePage hp = new HomePage(driver);
        hp.clickTerms();
        String Terms_Content = driver.findElement(By.xpath("//body")).getText();
        assert1.assertFalse(Static_Content_LU_Verifier(Terms_Content),"Last Updated Word does exist in Terms Page of Domain -"+url);
        assert1.assertFalse(Static_Content_MD_Verifier(Terms_Content),"My Domain Word does exist in Terms Page of Domain -"+url);
        assert1.assertFalse(Static_Content_HTTPS_Verifier(Terms_Content),"Https word does exist in Terms Page of Domain -"+url);
        assert1.assertFalse(Static_Content_Website_Verifier(Terms_Content),"Website word does exist in Terms Page of Domain -"+url);
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
    @Test
    public void Privacy_Static(){
        driver.get(url);
        HomePage hp = new HomePage(driver);
        hp.clickPrivacy();
        String Privacy_Content = driver.findElement(By.xpath("//body")).getText();
        assert1.assertFalse(Static_Content_LU_Verifier(Privacy_Content),"Last Updated Word does exist in Privacy Page of Domain -"+url);
        assert1.assertFalse(Static_Content_MD_Verifier(Privacy_Content),"My Domain Word does exist in Privacy Page of Domain -"+url);
        assert1.assertFalse(Static_Content_HTTPS_Verifier(Privacy_Content),"Https word does exist in Privacy Page of Domain -"+url);
        assert1.assertFalse(Static_Content_Website_Verifier(Privacy_Content),"Website word does exist in Privacy Page of Domain -"+url);
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
    @Test
    public void Cookie_Static(){
        driver.get(url);
        HomePage hp = new HomePage(driver);
        hp.clickCookie();
        String Cookie_Content = driver.findElement(By.xpath("//body")).getText();
        assert1.assertFalse(Static_Content_LU_Verifier(Cookie_Content),"Last Updated Word does exist in Cookie Page of Domain -"+url);
        assert1.assertFalse(Static_Content_MD_Verifier(Cookie_Content),"My Domain Word does exist in Cookie Page of Domain -"+url);
        assert1.assertFalse(Static_Content_Website_Verifier(Cookie_Content),"Website word does exist in Cookie Page of Domain -"+url);
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
}
