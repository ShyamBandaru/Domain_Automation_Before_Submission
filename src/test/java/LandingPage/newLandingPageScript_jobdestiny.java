package LandingPage;

import Utilities.DataUtil;
import basetest.baseclass;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class newLandingPageScript_jobdestiny extends baseclass {
    @Test(dataProvider = "URLs", dataProviderClass = DataUtil.class)
    public void landingpageverification(String domain) throws InterruptedException, IOException {
        driver.get(domain);
        WebElement home_search = driver.findElement(By.xpath("//a[contains(@href,'search')] | //button[contains(text(),'earch') or contains(text(),'EARCH')] | //button[contains(text(),'JOBS') or contains(text(),'Jobs')]"));
        click(driver, home_search);
        WebElement catDD = driver.findElement(By.xpath("//select[contains(@name,'company')]"));
        Select com_slct = new Select(catDD);
        List<WebElement> catOpt = com_slct.getOptions();
        String modified_Domain_Name = domainnamesorter(domain);
        for (int i = 1; i < catOpt.size(); i++) {
            Thread.sleep(100);
            com_slct.selectByIndex(i);
            Thread.sleep(100);
            WebElement search = driver.findElement(By.xpath("//button[@type='submit' or contains(text(),'earch') or contains(text(),'EARCH')]"));
            search.click();
            String window1 = driver.getWindowHandle();
            Thread.sleep(100);
            //fetching the Job
            String job_url = driver.findElement(By.xpath("//a[contains(text(),'Apply') or contains(text(),'APPLY') or contains(text(),'View Job')]")).getAttribute("href");
            if (response(job_url) == 200) {
                driver.switchTo().newWindow(WindowType.TAB);
                driver.get(job_url);
                JSONObject jsonObject = null;
                //getting page source to validate invalid br tags are present in the page
                String pgs = driver.getPageSource();
                boolean br_Verifier = pgs.contains("<br />");
                if (br_Verifier) {
                    System.out.println("Invalid br tag is present in the " + driver.getCurrentUrl());
                }
                //OG title length verification

                WebElement ogtitle = driver.findElement(By.xpath("//meta[@property='og:title']"));
                String ogtitlecontent = ogtitle.getAttribute("content");
                int ogtitlelenght = ogtitlecontent.length();
                String metatitle = driver.getTitle();
//                if (ogtitlelenght < 35) {
//                    System.out.println("OG Title less than 35 characters in " + driver.getCurrentUrl());
//                    assert1.fail("OG Title less than 35 characters");
//                } else if (ogtitlelenght > 60) {
//                    System.out.println("OG Title greater than 60 characters in " + driver.getCurrentUrl());
//                    assert1.fail("OG Title greater than 60 characters");
//                }
                //title to meta title validation
                if (!(ogtitlecontent.equals(metatitle))) {
                    assert1.fail("OG Title and Meta Title are not matching in " + driver.getCurrentUrl());
                }
                // meta description length verification
                WebElement metadesc = driver.findElement(By.xpath("//meta[@property='og:description']"));
                String metadesccontent = metadesc.getAttribute("content");
                int metadesclength = metadesccontent.length();
//                if (metadesclength < 100) {
//                    System.out.println("Meta Description less than 100 Characters in " + driver.getCurrentUrl());
//                    assert1.fail("Meta Description less than 100 Characters in " + driver.getCurrentUrl());
//                } else if (metadesclength > 160) {
//                    System.out.println("Meta Description greater than 160 characters in " + driver.getCurrentUrl());
//                    assert1.fail("Meta Description greater than 160 characters in " + driver.getCurrentUrl());
//                }
                //logo verification
                WebElement com_logo = driver.findElement(By.xpath("//img//following::img"));
                String logo_url = com_logo.getAttribute("src");
                boolean logo_pattern = imageextension(logo_url);
                if (!logo_pattern) {
                    System.out.println("Invalid Logo extension " + logo_url);
                    assert1.fail("Invalid logo Extension " + logo_url);
                }
                int logo_resp = response(logo_url);
                if (logo_resp != 200) {
                    System.out.println("Received " + logo_resp + " for " + logo_url + " in " + job_url);
                    assert1.fail("Received " + logo_resp + " for " + logo_url + " in " + job_url);
                }
                //landing page description class verification
                if (!divclass_verifier(driver)) {
                    System.out.println("LP Description Styling is not applied in " + job_url);
                    assert1.fail("LP Description Styling is not applied in " + job_url);
                }
                //capturing ld Json which has description
                List<WebElement> elements = driver.findElements(By.cssSelector("script[type='application/ld+json']"));
                for (WebElement element : elements) {
                    String ldJson = element.getAttribute("innerHTML");
                    if (ldJson != null && ldJson.contains("description")) {
                        jsonObject = new JSONObject(ldJson);
                    }
                }
                assert jsonObject != null;
                String title_data = (String) jsonObject.get("title");
                if (title_data.isEmpty()) {
                    System.out.println("Title data is empty in Ld+Json of Page " + job_url);
                    assert1.fail("Empty Title in " + job_url);
                }
                JSONObject companydetails = (JSONObject) jsonObject.get("hiringOrganization");
                JSONObject joblocation = (JSONObject) jsonObject.get("jobLocation");
                //verifying Geo Coordinates
                if (pgs.contains("GeoCoordinates")) {
                    JSONObject geo = (JSONObject) joblocation.get("geo");
                    String lat = geo.get("latitude").toString();
                    String lon = geo.get("longitude").toString();
                    if (Objects.equals(lat, "null")) {
                        System.out.println("null is present in the Latitude in " + job_url);
                    }
                    if (Objects.equals(lon, "null")) {
                        System.out.println("null is present in the Longitude in " + job_url);
                    }
                }
                //Capturing WebElement of Location
                WebElement ui_loc = driver.findElement(By.xpath("//p[text()='Location']//following::p"));
                String loc_det = ui_loc.getText();
                String loc_sort = loc_det.replace("Location : ", "");
                String[] sorted = loc_sort.split(",");
                String ui_city = sorted[0];
                String ui_state = sorted[1].trim();
                String ui_country = sorted[2].trim();

                //validating with Ld+json content
                JSONObject jobaddress = (JSONObject) joblocation.get("address");
                String lj_city = jobaddress.get("addressLocality").toString();
                String lj_state = jobaddress.get("addressRegion").toString();
                String lj_country = jobaddress.get("addressCountry").toString();

                if (!ui_city.equals(lj_city)) {
                    System.out.println(ui_city + "-" + lj_city);
                    System.out.println("City name is mismatching in UI and Ld Json in " + job_url);
                }
                if (!ui_state.equals(lj_state)) {
                    System.out.println(ui_state + "-" + lj_state);
                    System.out.println("State name is mismatching in UI and Ld Json in " + job_url);
                }
                if (!ui_country.equals(lj_country)) {
                    System.out.println(ui_country + "-" + lj_country);
                    System.out.println("Country name is mismatching in UI and Ld Json in " + job_url);
                }

                //adding more code to optional postalCode
                if (pgs.contains("postalCode")) {
                    String ui_postal = sorted[3].trim();
                    String lj_postal = jobaddress.get("postalCode").toString();
                    if (ui_postal.length() < 5 && lj_postal.length() < 5) {
                        System.out.println("Postal Code length is less than 5 characters in " + job_url);
                    }
                    if (!ui_postal.equals(lj_postal)) {
                        System.out.println(ui_postal + "-" + lj_postal);
                        System.out.println("Postal Code is mismatching in UI and Ld Json in " + job_url);
                    }
                }
                //validating hiring organisation details
                String ui_company_name = driver.findElement(By.tagName("h2")).getText();
                String lj_company_name = companydetails.get("name").toString();
                if (!ui_company_name.equals(lj_company_name)) {
                    System.out.println("Company Name id different on UI and on Ld Json in" + job_url);
                }
                String lj_sameas = companydetails.get("sameAs").toString();
                if (lj_sameas.contains("http:")) {
                    System.out.println("Same As address is in http format in " + job_url);
                }
//                logic for Valid Through
                long dateposted = epoch_converter((String) jsonObject.get("datePosted"));
                long validthrough = epoch_converter((String) jsonObject.get("validThrough"));
                long expected = 1000L * 60 * 60 * 24 * 28;
                long actual = validthrough - dateposted;
                if (actual != expected) {
                    assert1.fail("Valid Through Time is not equal to 28 days in " + job_url);
                }
                WebElement title = driver.findElement(By.xpath("//h1"));
                String ui_title = title.getText();
                if (!Objects.equals(ui_title, title_data)) {
                    System.out.println("Ui Title mismatches with Ld Json Title in " + job_url);
                }
                //validating industry field
//                String industry = (String) jsonObject.get("industry");
//                if (industry.isEmpty()){
//                    System.out.println("industry field is null in "+job_url);
//                }
                //validating og sitename
                String ogsitename = driver.findElement(By.xpath("//meta[@property='og:site_name']")).getAttribute("content");
                if (!ogsitename.equals(modified_Domain_Name)) {
                    System.out.println("OG Site name does not matches with domain name in " + job_url);
                }
                //validating identifier value to domain
                JSONObject identifier = (JSONObject) jsonObject.get("identifier");
                String id_name = (String) identifier.get("name");
                if (!id_name.equals(modified_Domain_Name)) {
                    System.out.println("Name in Identifier and Domain Name is not matching in " + job_url);
                }
                //identifier value(JobID)
                String jobid = jobidsorter(job_url);
                String id_value = (String) identifier.get("value");
                if (!id_value.equals(jobid)) {
                    System.out.println("Job ID in Url does not matches with ID Value in " + job_url);
                }
                //og-url content matches with current url
                String og_url = driver.findElement(By.xpath("//meta[@property='og:url']")).getAttribute("content");
                if (!og_url.equals(job_url)) {
                    System.out.println("OG URL and Job Landing Page url mismatches in " + job_url);
                }
                //canonical link validation
                String can_link = driver.findElement(By.xpath("//link[@rel='canonical']")).getAttribute("href");
                if (!can_link.equals(job_url)) {
                    System.out.println("Canonical href attribute and Job url are mismatching in " + job_url);
                }
                //ld+json url validation
                String lj_url = (String) jsonObject.get("url");
                if (!lj_url.equals(job_url)) {
                    System.out.println("LD Json URL and job URL is mismatching in " + job_url);
                }
                String ho_logo = (String) companydetails.get("logo");
                if (!ho_logo.equals(logo_url)) {
                    System.out.println("LD+JSON logo and logo URL is mismatching in " + job_url);
                }
                WebElement applybtn = driver.findElement(By.xpath("//a[contains(text(),'Apply') or contains(text(),'APPLY')]"));
                String apply_href = applybtn.getAttribute("href");
                String onclick_pattern = "applyTo(`" + apply_href + "`)";
                String onclick = applybtn.getAttribute("onclick");
                if (pgs.contains("onclick=\"applyTo(")) {
                    if (!onclick.equals(onclick_pattern)) {
                        System.out.println("href and onclick attribute is mismatching in " + job_url);
                    }
                }
                String rel = applybtn.getAttribute("rel");
                if (!rel.equals("nofollow")) {
                    System.out.println("rel value does not have nofollow in " + job_url);
                }
                //keywords verification
                driver.close();
                driver.switchTo().window(window1);
            } else {
                System.out.println("Expired Job " + job_url);
            }
            driver.navigate().back();
        }
        assert1.assertAll();
    }
}
