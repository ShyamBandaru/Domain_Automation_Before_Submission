package Tests;

import PageClasses.ContactPage;
import PageClasses.HomePage;
import basetest.baseclass;
import org.testng.annotations.Test;

public class ContactPageTests extends baseclass {
    @Test
    public void VerifyNameError() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        hp.clickContact();
        ContactPage cp = new ContactPage(driver);
        cp.sendSubject("Test");
        cp.sendEmail("test@123.com");
        cp.sendContact("9876543210");
        cp.clickSubmit();
        boolean name = cp.NameErrorValidator();
        if(!name){
            assert1.fail("Name Error is not displaying");
        }
        cp.sendName("sa");
        cp.clickSubmit();
        boolean name2 = cp.NameErrorValidator();
        if (!name2){
            assert1.fail("Invalid Name error is not displaying");
        }
        assert1.assertAll();
    }
    @Test
    public void VerifySubjectError() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        hp.clickContact();
        ContactPage cp = new ContactPage(driver);
        cp.sendName("Test");
        cp.sendEmail("test@123.com");
        cp.sendContact("9876543210");
        cp.clickSubmit();
        boolean subject = cp.SubjectErrorValidator();
        if(!subject){
            assert1.fail("Subject Error is not displaying");
        }
        cp.sendSubject("aa");
        cp.clickSubmit();
        boolean subject2 = cp.SubjectErrorValidator();
        if (!subject2){
            assert1.fail("Invalid Subject Error is not displaying");
        }
        assert1.assertAll();
    }
    @Test
    public void VerifyEmailError() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        hp.clickContact();
        ContactPage cp = new ContactPage(driver);
        cp.sendName("Test");
        cp.sendSubject("Test");
        cp.sendContact("9876543210");
        cp.clickSubmit();
        boolean email = cp.EmailErrorValidator();
        if(!email){
            assert1.fail("Email Error is not displaying");
        }
        Thread.sleep(3000);
        cp.sendEmail("test@123");
        cp.clickSubmit();
        boolean email2 = cp.EmailErrorValidator();
        if (!email2){
            assert1.fail("Error is not throwing for invalid Email Format");
        }
        assert1.assertAll();
    }
    @Test
    public void VerifyPhoneError(){
        HomePage hp = new HomePage(driver);
        hp.clickContact();
        ContactPage cp = new ContactPage(driver);
        cp.sendName("Test");
        cp.sendSubject("Test");
        cp.sendEmail("test@123.com");
        cp.clickSubmit();
        boolean ph = cp.PhoneErrorValidator();
        if(!ph){
            assert1.fail("Phone Validation is missing");
        }
        cp.sendContact("987654");
        cp.clickSubmit();
        boolean ph2 = cp.PhoneErrorValidator();
        if (!ph2){
            assert1.fail("Invalid Phone Number Validation is missing");
        }
        assert1.assertAll();
    }
    @Test
    public void contactPageValidation() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        hp.clickContact();
        ContactPage cp = new ContactPage(driver);
        cp.sendName("Test");
        cp.sendSubject("Test");
        cp.sendEmail("test@123.com");
        cp.sendContact("9876543210");
        cp.clickSubmit();
        Thread.sleep(2000);
        boolean success = cp.SuccessMessageValidator();
        if (!success){
            assert1.fail("Success Message is not displaying");
        }
        assert1.assertAll();
    }
    @Test
    public void ContactPage_OG_Canonical_Validation(){
        HomePage hp = new HomePage(driver);
        hp.clickContact();
        assert1.assertTrue(OG_URL_Validator(),"OG:URL value is mismatching with current url - "+driver.getCurrentUrl());
        assert1.assertTrue(Canonical_Validator(),"Canonical Value is Mismatching with the Current url -"+driver.getCurrentUrl());
        assert1.assertAll();
    }
}
