package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


 //Klasa reprezentuje stronę "Moje konto"
public class MyAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Link do wylogowania się z konta użytkownika
    @FindBy(css = "a[href*='mylogout']")
    private WebElement signOutLink;

    //Konstruktor klasy – inicjalizuje elementy strony oraz WebDriverWait.

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Inicjalizacja elementów oznaczonych @FindBy
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ustawienie explicite waita (10 sekund)
    }

    //Metoda sprawdzająca, czy link "Wyloguj się" jest widoczny.

    public boolean isSignOutVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(signOutLink)); // Czeka aż element będzie widoczny
            return signOutLink.isDisplayed();
        } catch (Exception e) {
            return false; // Gdy element nie istnieje lub jest niewidoczny
        }
    }
}
