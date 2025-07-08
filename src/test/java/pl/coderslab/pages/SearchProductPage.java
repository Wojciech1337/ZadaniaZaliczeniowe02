package pl.coderslab.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


  //Page Object dla strony wyszukiwania produktów.

public class SearchProductPage {

    private WebDriver driver;

    // ======= Lokatory =======

    @FindBy(name = "s")
    private WebElement searchInput;

    @FindBy(css = ".search")
    private WebElement searchButton;

    @FindBy(css = "a.product-thumbnail")
    private WebElement productLink;

    // ======= Konstruktor =======

    public SearchProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ======= Metody działania =======


     //Wpisuje nazwę produktu i zatwierdza wyszukiwanie klawiszem ENTER. Nazwa produktu do wyszukania.

    public void searchForProduct(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
    }

    // Kliknięcie w pierwszy produkt na liście wyników wyszukiwania.

    public void clickOnFirstProduct() {
        productLink.click();
    }

     //Getter do oczekiwania na widoczność pierwszego produktu. Return WebElement reprezentujący pierwszy produkt.

    public WebElement getProductLink() {
        return productLink;
    }

    //Wyszukuje produkt i od razu w niego klika.

    public void searchAndSelectProduct(String productName) {
        searchForProduct(productName);
        clickOnFirstProduct();
    }
}
