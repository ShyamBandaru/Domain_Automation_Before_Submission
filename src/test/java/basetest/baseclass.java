package basetest;

import Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class baseclass {
    static ReadConfig readconfig = new ReadConfig();
    protected String GTM_ID = readconfig.getgtm();
    protected int SearchPageCount = Integer.parseInt(readconfig.GetSPCount());
    protected int ListingPageCount = Integer.parseInt(readconfig.GetLPCount());
    protected int LinkCount = Integer.parseInt(readconfig.GetLinkCount());
    protected String url = "https://"+readconfig.getURL();
    protected String domainurl = (readconfig.getURL().split("\\.",0))[0];
    protected WebDriver driver;
    protected SoftAssert assert1;
    public JavascriptExecutor js;
    public void takescreenshot(WebDriver driver) throws IOException {
        Date date = new Date();
        String timestamp = date.toString().replace(" ","_").replace(":","_");
        String filename = "screenshot_"+timestamp;
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File trg = new File("./src/screenshots/failed/"+filename+".png");
        FileUtils.copyFile(src,trg);
    }
    public void takescreenshot(WebDriver driver,String name) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File trg = new File("./src/screenshots/"+readconfig.getURL()+"/"+name+".png");
        FileUtils.copyFile(src,trg);
    }
    public void takescreenshot(WebElement element,String name) throws IOException {
        File src = element.getScreenshotAs(OutputType.FILE);
        File trg = new File("./src/screenshots/"+readconfig.getURL()+"/"+name+".png");
        FileUtils.copyFile(src,trg);
    }
    public boolean OGSiteNameValidator(){
        String OG_Site_Name = driver.findElement(By.xpath("//meta[@property='og:site_name']")).getAttribute("content");
        return OG_Site_Name.equals(domainurl);
    }
    public boolean TitleTagVerification() {
        String Body_Title = driver.getTitle();
        String Meta_Title = driver.findElement(By.xpath("//meta[@name='title']")).getAttribute("content");
        String OG_Title = driver.findElement(By.xpath("//meta[@property='og:title']")).getAttribute("content");
        return Body_Title.equals(Meta_Title) && Meta_Title.equals(OG_Title);
    }
    public boolean OG_URL_Validator(){
        String OG_URL = driver.findElement(By.xpath("//meta[@property='og:url']")).getAttribute("content");
        return driver.getCurrentUrl().contains(OG_URL);
    }
    public String domainnamesorter(String domain){
        return domain.replace("https://","").split("[.]",0)[0];
    }
    public String jobidsorter(String url){
        String[] url_1=url.split("-");
        int i= url_1.length;
        return url_1[i-1];
    }
    public void click(WebDriver driver, WebElement element) throws InterruptedException
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }
    public boolean divclass_verifier(WebDriver driver){
        try{
            WebElement desc_lp = driver.findElement(By.xpath("//div[contains(@class,'description-lp') or contains(@class,'main-content')]"));
            return desc_lp.isDisplayed();
        }
        catch (NoSuchElementException e){
            return false;
        }

    }
    public boolean Canonical_Validator(){
        String Canonical = driver.findElement(By.xpath("//link[@rel='canonical']")).getAttribute("href");
        return Canonical.equals(driver.getCurrentUrl());
    }
    public boolean Static_Content_LU_Verifier(String content){
        return content.contains("Last Updated");
    }
    public boolean Static_Content_HTTPS_Verifier(String content){
        return content.contains("https:")||content.contains("http:");
    }
    public boolean Static_Content_Website_Verifier(String content){
        return content.contains("\"Website\"")||content.contains("(Website)");
    }
    public boolean Static_Content_MD_Verifier(String content){
        return content.contains("My Domain");
    }
    public boolean LPTitleTagVerification() {
        String Body_Title = driver.getTitle();
        String OG_Title = driver.findElement(By.xpath("//meta[@property='og:title']")).getAttribute("content");
        return Body_Title.equals(OG_Title);
    }
    public boolean DescriptionValidation(){
        String Meta_Desc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
        String OG_Desc = driver.findElement(By.xpath("//meta[@property='og:description']")).getAttribute("content");
        return Meta_Desc.equals(OG_Desc);
    }
    public static int response(String url) {
        int res_code= 0;
        try {
            Response resp = given()
                    .when()
                    .get(url);
            res_code = resp.getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res_code;
    }
    public Boolean imageextension(String source){
        Boolean value = null;
        if(source.endsWith(".png")){
            value=true;
        } else if (source.endsWith(".PNG")) {
            value=true;
        } else if (source.endsWith(".jpg")){
            value=true;
        } else if (source.endsWith(".jpeg")) {
            value=true;
        } else if (source.endsWith(".gif")) {
            value=true;
        } else if (source.endsWith(".webp")) {
            value=true;
        } else if (source.endsWith(".svg")) {
            value=true;
        } else {
            value=false;
        }
        return value;
    }

    public long epoch_converter(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date, formatter);
        long time = offsetDateTime.toInstant().toEpochMilli();
        return time;
    }
    public boolean DivClass_Verifier(WebDriver driver){
        try{
            WebElement desc_lp = driver.findElement(By.xpath("//div[contains(@class,'description-lp') or contains(@class,'main-content')]"));
            return desc_lp.isDisplayed();
        }
        catch (NoSuchElementException e){
            return false;
        }

    }
    @BeforeMethod
    public void start_point(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless=new");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor)driver;
        assert1 = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(url);
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
