package pl.coderslab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    private WebDriver driver;

    @FindBy(css = "h1.page-title")
    private WebElement accountHeader;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOnAccountPage() {
        try {
            return accountHeader.isDisplayed() && accountHeader.getText().contains("My account");
        } catch (Exception e) {
            return false;
        }
    }
}
