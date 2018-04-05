import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class loginSuccessTest {

    private static final By USERNAME_INPUT = By.id("Username");
    private static final By PASSWORD_INPUT = By.name("Password");
    private static final By LOGIN_BUTTON = By.className("submit-button");
    private static final By REMEMBER_ME_CHECKBOX = By.xpath("//div[@class='remember-chBox']/label");
    private static final By SKYPE_DATA_IMAGE = By.cssSelector(".skype-container img");
    private static final By ISSOFT_SOLUTIONS_LINK = By.linkText("ISsoft Solutions");
    private static final By WEX_HEALTH_CLOUD_LINK = By.partialLinkText("WEX Health Cloud");
    public static final By INDIVIDUAL_IMAGE = By.cssSelector("img[alt='Individual image']");

    private WebDriver driver;

    @BeforeEach
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://192.168.0.100/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest() {
        boolean rememberMe = false;
        String username = "AlexanderSavenok";
        String password = "*********";
        String expectedSkype = "csi.alexandersavenok";

        populateLogin(username, password, rememberMe);

        WebElement skypeDataImage = driver.findElement(SKYPE_DATA_IMAGE);
        String skypeDataImageAttribute = skypeDataImage.getAttribute("alt");

        WebElement isSoftSolutionsLink = driver.findElement(ISSOFT_SOLUTIONS_LINK);
        WebElement wexHealthCloudLink = driver.findElement(WEX_HEALTH_CLOUD_LINK);
        WebElement individualImage = driver.findElement(INDIVIDUAL_IMAGE);
        assertEquals(expectedSkype, skypeDataImageAttribute, String.format("Expected skype value: %s\nActual skype value: %s", expectedSkype, skypeDataImageAttribute));
        assertTrue(isSoftSolutionsLink.isDisplayed(), "IsSoft Solutions link does not displayed");
        assertTrue(wexHealthCloudLink.isDisplayed(), "Wex Health Cloud link does not displayed");
        assertTrue(individualImage.isDisplayed(), "Individual image does not displayed");
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    private void populateLogin(String username, String password, boolean rememberMe) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        selectRememberMeCheckbox(rememberMe);
        driver.findElement(LOGIN_BUTTON).click();
    }

    private void selectRememberMeCheckbox(boolean select) {
        WebElement rememberMeCheckbox = driver.findElement(REMEMBER_ME_CHECKBOX);
        if(!rememberMeCheckbox.isSelected() && select) {
            rememberMeCheckbox.click();
        }
    }
}
