package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataUtil {
    @DataProvider(name = "URLs")
    public String[] getURLs() throws IOException {

        String path = "./src/main/resources/UserData/DomainURLs.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        String url[] = new String[rownum];
        for (int i = 1; i <= rownum; i++) {
            url[i-1] = xl.getCellData("Sheet1",i,1);
        }
        return url;
    }
    @DataProvider(name = "title")
    public String[] gettitle() throws IOException {
        String path = "./src/main/resources/UserData/Book_1.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        String url[] = new String[rownum];
        for (int i = 1; i <= rownum; i++) {
            url[i-1] = xl.getCellData("Sheet1",i,1);
        }
        return url;
    }
}
