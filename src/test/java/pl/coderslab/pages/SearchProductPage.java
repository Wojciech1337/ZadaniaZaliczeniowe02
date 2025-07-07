package pl.coderslab.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProductPage {
    private WebDriver driver;

    @FindBy(name = "s")  // Pole wyszukiwania
    private WebElement searchInput;

    @FindBy(css = ".search") // Ikona lupy lub przycisk szukania
    private WebElement searchButton;

    @FindBy(css = "a.product-thumbnail") // Kliknięcie na produkt w wynikach
    private WebElement productLink;

    public SearchProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForProduct(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void clickOnFirstProduct() {
        productLink.click();
    }

    // Getter potrzebny do oczekiwania w PurchaseSteps
    public WebElement getProductLink() {
        return productLink;
    }

    // Opcjonalnie metoda łącząca wyszukiwanie i kliknięcie (jeśli potrzebujesz)
    public void searchAndSelectProduct(String productName) {
        searchForProduct(productName);
        clickOnFirstProduct();
    }
}
