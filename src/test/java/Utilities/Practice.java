package Utilities;

import java.io.IOException;

public class Practice {
    public static void main(String[] args) throws IOException {
        ReadConfig readconfig = new ReadConfig();
        System.out.println(readconfig.getURL());
    }
}
