package nus.iss.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {

    private static String getRandomCookie(String cookieFilePath) {
        String randomCookie = "";

        List<String> cookies = new LinkedList<String>();

        try {
            cookies = getDataFromCookieFile(cookieFilePath);

            Random r = new Random();
            int index = r.nextInt();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "cookie-text_cookie";
    }

    private static List<String> getDataFromCookieFile(String cookieFilePath) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(cookieFilePath));
        List<String> cookies = new LinkedList<>();
        String line;

        while ((line = br.readLine()) != null) {


        
        }
        return cookies;

    }


}
