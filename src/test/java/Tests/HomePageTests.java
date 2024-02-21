package Tests;

import basetest.baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTests extends baseclass {
    @Test
    public void LogosCopy() throws IOException {
        WebElement logo = driver.findElement(By.xpath("//img"));
        String Domain_Logo = logo.getAttribute("src");
        takescreenshot(logo,"Domain Logo");
        if (!Domain_Logo.contains(domainurl)){
            assert1.fail("Domain URL is not included in Logo URL address "+Domain_Logo+" in "+driver.getCurrentUrl());
        }
        assert1.assertAll();
    }
    @Test
    public void Favicon() throws InterruptedException, IOException {
        WebElement FaviconLogo = driver.findElement(By.xpath("//link[contains(@href,'favicon')]"));
        String Favicon_Add = FaviconLogo.getAttribute("href");
        driver.get(Favicon_Add);
        takescreenshot(driver,"Favicon Logo");
        if (!Favicon_Add.contains(domainurl)){
            assert1.fail("Domain URL is not included in Favicon URL address");
        }
        assert1.assertAll();
    }
    @Test
    public void HomeTitleTagVerification(){
        boolean i = TitleTagVerification();
        if (!i){
            assert1.fail("Title is Mismatching in Body,Meta,OG Title in "+driver.getCurrentUrl());
        }
        assert1.assertAll();
    }
    @Test
    public void HomeDescriptionValidation(){
        boolean i = DescriptionValidation();
        if(!i){
            assert1.fail("Description is Different in Meta Description and in OG Description in "+ driver.getCurrentUrl());
        }
        assert1.assertAll();
    }
    @Test
    public void HomeOGSiteName(){
        boolean i = OGSiteNameValidator();
        if (!i){
            assert1.fail("OG Site Name value is different than domain URL in "+driver.getCurrentUrl());
        }
    }
    @Test
    public void GTMVerifier(){
        String iframe = driver.findElement(By.xpath("//body//child::noscript")).getAttribute("innerHTML");
        if (!iframe.contains(GTM_ID)){
            assert1.fail("GTM ID is not updated in the Iframe Body of current Domain - "+url);
        }
        String Script2 = driver.findElement(By.xpath("//script//following::script")).getAttribute("innerHTML");
        if (Script2.contains(GTM_ID)){
            assert1.fail("GTM ID is not update in the Script Body of Current Domain -"+url);
        }
    }
    @Test
    public void OG_Locale_Verifier(){
        driver.get(url);
        String OG_Locale = driver.findElement(By.xpath("//meta[@property='og:locale']")).getAttribute("content");
        if (!OG_Locale.equals("en_US")){
            assert1.fail("OG Locale Value is an unexpected one in the Domain - "+url);
        }
        assert1.assertAll();
    }
    @Test
    public void OG_Type_Verifier(){
        String OG_Type = driver.findElement(By.xpath("//meta[@property='og:type']")).getAttribute("content");
        if (!OG_Type.equals("website")){
            assert1.fail("OG Type Value is an unexpected one in the Domain - "+url);
        }
        assert1.assertAll();
    }
    @Test
    public void Home_OG_URL_Canonical(){
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl()+ " is "+driver.findElement(By.xpath("//meta[@property='og:url']")).getAttribute("content"));
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
}
