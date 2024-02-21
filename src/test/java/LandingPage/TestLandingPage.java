package LandingPage;

import Utilities.DataUtil;
import basetest.baseclass;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class TestLandingPage extends baseclass {
    @Test(dataProvider = "URLs",dataProviderClass = DataUtil.class)
    public void lpVerification(String url) {
        JSONObject jsonObject = null;
        driver.get(url);
        //getting page source to validate invalid br tags are present in the page
        String pgs = driver.getPageSource();
        boolean br_Verifier = pgs.contains("<br />");
        if (br_Verifier){
            System.out.println("Invalid br tag is present in the "+driver.getCurrentUrl());
        }
        //OG title length verification
        WebElement ogtitle = driver.findElement(By.xpath("//meta[@property='og:title']"));
        String ogtitlecontent = ogtitle.getAttribute("content");
        int ogtitlelenght = ogtitlecontent.length();
        String metatitle = driver.getTitle();
        if (ogtitlelenght<35){
            System.out.println("OG Title less than 35 characters in "+driver.getCurrentUrl());
            assert1.fail("OG Title less than 35 characters");
        } else if (ogtitlelenght>60) {
            System.out.println("OG Title greater than 60 characters in "+driver.getCurrentUrl());
            assert1.fail("OG Title greater than 60 characters");
        }
        //title to meta title validation
        if (!(ogtitlecontent.equals(metatitle))){
            assert1.fail("OG Title and Meta Title are not matching in "+driver.getCurrentUrl());
        }
        // meta description length verification
        WebElement metadesc = driver.findElement(By.xpath("//meta[@property='og:description']"));
        String metadesccontent = metadesc.getAttribute("content");
        int metadesclength = metadesccontent.length();
        if (metadesclength<100){
            System.out.println("Meta Description less than 100 Characters in "+driver.getCurrentUrl());
            assert1.fail("Meta Description less than 100 Characters");
        } else if (metadesclength>160) {
            System.out.println("Meta Description greater than 160 characters in "+driver.getCurrentUrl());
            assert1.fail("Meta Description greater than 160 characters in "+driver.getCurrentUrl());
        }
        //logo verification
        WebElement com_logo = driver.findElement(By.xpath("//img//following::img"));
        String logo_url = com_logo.getAttribute("src");
        boolean logo_pattern = imageextension(logo_url);
        if(!logo_pattern){
            System.out.println("Invalid Logo extension "+logo_url);
            assert1.fail("Invalid logo Extension "+logo_url);
        }
        int logo_resp = response(logo_url);
        if(logo_resp!=200){
            System.out.println("Received "+logo_resp+" for "+logo_url);
            assert1.fail("Received "+logo_resp+" for "+logo_url);
        }
        //landing page description class verification
        if(!divclass_verifier(driver)){
            System.out.println("LP Description Styling is not applied");
            assert1.fail("LP Description Styling is not applied");
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
        if (title_data.isEmpty()){
            System.out.println("Title data is empty in Ld+Json of Page " + driver.getCurrentUrl());
            assert1.fail("Empty Title");
        }
        JSONObject companydetails = (JSONObject) jsonObject.get("hiringOrganization");
        JSONObject joblocation = (JSONObject) jsonObject.get("jobLocation");
        //verifying Geo Coordinates
        if(pgs.contains("GeoCoordinates")){
            JSONObject geo = (JSONObject) joblocation.get("geo");
            String lat = geo.get("latitude").toString();
            String lon = geo.get("longitude").toString();
            if(lat==null){
                System.out.println("null is present in the Latitude in "+driver.getCurrentUrl());
            }
            if (lon==null){
                System.out.println("null is present in the Longitude in "+driver.getCurrentUrl());
            }
        }
        //Capturing WebElement of Location
        WebElement ui_loc = driver.findElement(By.xpath("//*[contains(text(),'Location :')]//parent::p"));
        String loc_det = ui_loc.getText();
        String loc_sort = loc_det.replace("Location : ", "");
        String[] sorted = loc_sort.split(",");
        String ui_city = sorted[0];
        String ui_state = sorted[1].trim();
        String ui_country = sorted[2].trim();
        String ui_postal = sorted[3].trim();
        //validating with Ld+json content
        JSONObject jobaddress = (JSONObject)joblocation.get("address");
        String lj_city = jobaddress.get("addressLocality").toString();
        String lj_state = jobaddress.get("addressRegion").toString();
        String lj_country = jobaddress.get("addressCountry").toString();
        String lj_postal = jobaddress.get("postalCode").toString();
        if(ui_postal.length()<5&&lj_postal.length()<5){
            System.out.println("Postal Code length is less than 5 characters in "+driver.getCurrentUrl());
        }
        if (!ui_city.equals(lj_city)){
            System.out.println(ui_city+"-"+lj_city);
            System.out.println("City name is mismatching in UI and Ld Json in "+driver.getCurrentUrl());
        }
        if (!ui_state.equals(lj_state)){
            System.out.println(ui_state+"-"+lj_state);
            System.out.println("State name is mismatching in UI and Ld Json in "+driver.getCurrentUrl());
        }
        if (!ui_country.equals(lj_country)){
            System.out.println(ui_country+"-"+lj_country);
            System.out.println("Country name is mismatching in UI and Ld Json in "+driver.getCurrentUrl());
        }
        if (!ui_postal.equals(lj_postal)){
            System.out.println(ui_postal+"-"+lj_postal);
            System.out.println("Postal Code is mismatching in UI and Ld Json in "+driver.getCurrentUrl());
        }
        //validating hiring organisation details
        String ui_company_name = driver.findElement(By.tagName("h2")).getText();
        String lj_company_name = companydetails.get("name").toString();
        if(!ui_company_name.equals(lj_company_name)){
            System.out.println("Company Name id different on UI and on Ld Json in"+driver.getCurrentUrl());
        }
        String lj_sameas = companydetails.get("sameAs").toString();
        if(lj_sameas.contains("http:")){
            System.out.println("Same As address is in http format in "+driver.getCurrentUrl());
        }



        //logic for Valid Through
        long dateposted = epoch_converter((String) jsonObject.get("datePosted"));
        long validthrough = epoch_converter((String) jsonObject.get("validThrough"));
        long expected = 1000L *60*60*24*28;
        long actual = validthrough-dateposted;
        if (actual!=expected){
            assert1.fail("Valid Through Time is not equal to 28 days");
        }
        WebElement title = driver.findElement(By.xpath("//h1"));
        String ui_title = title.getText();
        if(!Objects.equals(ui_title, title_data)){
            System.out.println("Ui Title mismatches with Ld Json Title");
        }
        assert1.assertAll();
    }
}
