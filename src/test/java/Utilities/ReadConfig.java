package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    public Properties pro;

    public ReadConfig() {
        pro = new Properties();
        File file = new File("./src/test/Configuration/config.properties");
        try {
            FileInputStream fis = new FileInputStream(file);

            pro.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getURL() {
        return pro.getProperty("url");
    }

    public String getgtm() {
        return pro.getProperty("gtm-id");
    }

    public String GetSPCount() {
        return pro.getProperty("search-page-count");
    }

    public String GetLPCount() {
        return pro.getProperty("listing-page-count");
    }

    public String GetLinkCount() {
        return pro.getProperty("link-count");
    }
}
