package testsuite;

/**
 * 1. userSholdLoginSuccessfullyWithValidCredentials()
 *
 * * Enter “tomsmith” for the username
 * * Enter “SuperSecretPassword!” for the password
 * * Click on the ‘Login’ button
 * * Verify the text “Secure Area”
 * * Click on the ‘Logout’ button
 * * Verify the text ‘You logged out of the secure area!’
 *
 * 2. verifyTheUsernameErrorMessage()
 *
 * * Enter “tomsmith1” for the username
 * * Enter “SuperSecretPassword!” for the password
 * * Click on the ‘Login’ button
 * * Verify the error message “Your username is invalid!”
 *
 * 3. verifyThePasswordErrorMessage()
 *
 * * Enter “tomsmith” for the username
 * * Enter “SuperSecretPassword” for the password
 * * Click on the ‘Login’ button
 * * Verify the error message “Your password is invalid!”
 */

import browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void Setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("fa")).click();
        String actualText = driver.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(actualText, "Secure Area");

        driver.findElement(By.xpath("//a[@class='button secondary radius']")).click();
        String logoutMessage = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(logoutMessage.contains("You logged out of the secure area!"));
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();
        String errorMessage = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(errorMessage.contains("Your username is invalid!"));
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.className("radius")).click();
        String errorMessage = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(errorMessage.contains("Your password is invalid!"));
    }


}
