package pl.coderslab.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.pages.LoginPage;
import pl.coderslab.pages.MyAccountPage;

public class LoginSteps {

    public static WebDriver driver; // udostępniony driver

    private LoginPage loginPage;
    private MyAccountPage myAccountPage;

    @Before
    public void setUp() {
        // Ustaw ścieżkę do chromedrivera, jeśli to konieczne np.:
        // System.setProperty("webdriver.chrome.driver", "ścieżka/do/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @After
    public void tearDown() {
        try {
            // ⏳ pauza 5 sekund, aby zobaczyć wynik testu przed zamknięciem
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }

    }

    @Given("The user is on the login page")
    public void theUserIsOnTheLoginPage() {
        System.out.println("🔐 User is on the login page");
        loginPage.open();
    }

    @When("The user logs in with email {string} and password {string}")
    public void theUserLogsInWithEmailAndPassword(String email, String password) {
        loginPage.login(email, password);
        System.out.println("🔑  User logged in successfully");

    }

}
