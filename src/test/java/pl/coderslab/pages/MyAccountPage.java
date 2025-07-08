package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


// Page Object Model dla strony "My Account" użytkownika.

public class MyAccountPage {

    private WebDriver driver;

    // ======= Lokatory =======

    @FindBy(css = "h1.page-title")
    private WebElement accountHeader;

    // ======= Konstruktor =======

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ======= Metody działania =======


     // Sprawdza, czy użytkownik znajduje się na stronie "My Account".  return true jeśli nagłówek jest widoczny i zawiera tekst "My account"

    public boolean isOnAccountPage() {
        try {
            return accountHeader.isDisplayed() && accountHeader.getText().contains("My account");
        } catch (Exception e) {
            return false;
        }
    }
}
