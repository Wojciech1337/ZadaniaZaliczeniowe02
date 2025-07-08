package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


//Page Object Model dla strony logowania w aplikacji mystore-testlab.coderslab.pl

public class LoginPage {
    private WebDriver driver;

    // ======= Lokatory =======

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit-login")
    private WebElement loginButton;

    // ======= Konstruktor =======

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ======= Metody działania =======

    //Loguje użytkownika przy użyciu przekazanego e-maila i hasła.

    public void login(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();
    }

    //Otwiera stronę logowania bezpośrednio przez adres URL.

    public void open() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }
}
