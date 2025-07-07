package pl.coderslab.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.coderslab.pages.SearchProductPage;
import pl.coderslab.pages.ProductDetailsPage;

import java.time.Duration;

public class PurchaseSteps {

    private WebDriver driver = LoginSteps.driver;
    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        if (driver == null) {
            throw new IllegalStateException("Driver nie został zainicjalizowany. Uruchom login przed zakupem.");
        }
        searchProductPage = new SearchProductPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @And("The user searches for {string} and selects the product")
    public void theUserSearchesForAndSelectsTheProduct(String productName) {
        searchProductPage.searchAndSelectProduct(productName);
    }

    @And("The user selects size {string} and quantity {int}")
    public void theUserSelectsSizeAndQuantity(String size, int quantity) {
        productDetailsPage.selectSize(size);
        productDetailsPage.setQuantity(quantity);
    }

    @And("The user adds the product to the cart and proceeds to checkout")
    public void theUserAddsProductToCartAndProceedsToCheckout() {
        productDetailsPage.addToCart();
        productDetailsPage.proceedToCheckoutFromModal(); // ✅ kliknięcie w modalowy przycisk
    }

    @After
    public void tearDown() {
        try {
            Thread.sleep(5000); // ⏳ pauza, aby zobaczyć test
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
