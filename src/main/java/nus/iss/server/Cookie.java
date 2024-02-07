package nus.iss.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {

    public static String getRandomCookie(String cookieFilePath) {
        String randomCookie = "No cookie for you !";

        List<String> cookies = new LinkedList<>();

        try {
            cookies = getDataFromCookieFile(cookieFilePath);

            Random r = new Random();
            int cookieSize = cookies.size();
            if (cookieSize > 0) {
                int index = r.nextInt(cookieSize);
                randomCookie = cookies.get(index);
            }
            System.out.println(randomCookie);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomCookie;
    }

    private static List<String> getDataFromCookieFile(String cookieFilePath) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(cookieFilePath));
        List<String> cookies = new LinkedList<>();
        String line;

        while ((line = br.readLine()) != null) {
            cookies.add(line);
        }
        return cookies;
    }

}
