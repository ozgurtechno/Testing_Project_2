import Utils.BaseStaticDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class Main extends BaseStaticDriver {

    @Test
    public void test1(){
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        Actions actions = new Actions(driver);
        Random random = new Random();

        //login page
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("Admin");
        Bekle(2);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Admin123");
        Bekle(2);

        List<WebElement> locations = driver.findElements(By.cssSelector("ul#sessionLocation>li"));
        for(WebElement el : locations){
            actions.moveToElement(el).build().perform();
            Assert.assertEquals("rgba(0, 127, 255, 1)", el.getCssValue("background-color"));
            Bekle(1);
        }

        locations.get(random.nextInt(6)).click();
        driver.findElement(By.id("loginButton")).click();

        // https://demo.openmrs.org/openmrs/index.htm
        WebElement adminBtn = driver.findElement(By.cssSelector("li>i[class=\"icon-user small\"]"));
        actions.moveToElement(adminBtn).build().perform();

        WebElement myAccount = driver.findElement(By.id("user-account-menu"));
        Bekle(3);
        Assert.assertTrue(myAccount.isDisplayed());

        myAccount.click();
        Assert.assertEquals(driver.getTitle(), "My Account");

        WebElement languageButton = driver.findElement(By.partialLinkText("Languages"));
        languageButton.click();

        // Tum diller secilsin... Secim Assert isChecked() mi bakilsin...
        // Select ten rastgele bir tane secilince digerlerinin unchecked oldugu teyit edilsin...
        // logout yapilsin...
        // vs vs vs




        BekleKapat();
    }
}
