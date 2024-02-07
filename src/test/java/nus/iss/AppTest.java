package nus.iss;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nus.iss.server.Cookie;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testGetRandomCookieName()
    {
        String cookieName = Cookie
            .getRandomCookie("C:\\Users\\User\\Desktop\\TFIP\\SDF\\Day-4-workshop\\cookie_file.txt");
        System.out.println(">>>>>>" + cookieName);
        assertNotNull( cookieName );
    }
}