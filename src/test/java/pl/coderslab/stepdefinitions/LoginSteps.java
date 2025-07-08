package pl.coderslab.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.pages.LoginPage;
import pl.coderslab.pages.MyAccountPage;

import java.time.Duration;

public class LoginSteps {

    // 🔄 Udostępniony driver dla całego scenariusza
    public static WebDriver driver;

    private LoginPage loginPage;
    private MyAccountPage myAccountPage;

    @Before
    public void setUp() {
        // 🔧 Inicjalizacja przeglądarki i domyślnych timeoutów
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // 🧱 Inicjalizacja stron (Page Object)
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @After
    public void tearDown() {
        // 🧹 Zamknięcie przeglądarki po teście
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
