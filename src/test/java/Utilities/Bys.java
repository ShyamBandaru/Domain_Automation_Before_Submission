package Utilities;

import lombok.Getter;
import org.openqa.selenium.By;

public class Bys {
    @Getter
    public static By home_jbc = By.xpath("//*[contains(text(),'Compan')]//following::a[contains(text(),'All')  or contains(text(),'ALL') or contains(text(),'More') or contains(text(),'MORE') or contains(text(),'⇾') or contains(text(),'→')]");
    @Getter
    public static By home_jbl = By.xpath("//*[contains(text(),'Location')]//following::a[contains(text(),'All')  or contains(text(),'ALL') or contains(text(),'More') or contains(text(),'MORE') or contains(text(),'⇾') or contains(text(),'→')]");
    @Getter
    public static By home_fj = By.xpath("//*[contains(text(),'Featured')]//following::a[contains(text(),'All')  or contains(text(),'ALL') or contains(text(),'More') or contains(text(),'MORE') or contains(text(),'⇾') or contains(text(),'→')]");
    @Getter
    public static By l1 = By.xpath("//h5//parent::a");
    @Getter
    public static By l2 = By.xpath("//span//parent::a");
    @Getter
    public static By l3 = By.xpath("//h6//parent::a");
    @Getter
    public static By l4 = By.xpath("//span//parent::div//child::a");
    @Getter
    public static By l5 = By.xpath("//p//parent::a");
    @Getter
    public static By pagelinks = By.tagName("a");
    @Getter
    public static By home_jbc_more = By.xpath("//*[contains(text(),'Compan')]//following::a[contains(text(),'More') or contains(text(),'MORE')]");
}
