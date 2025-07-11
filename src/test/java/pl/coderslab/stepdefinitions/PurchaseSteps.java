package pl.coderslab.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.coderslab.pages.SearchProductPage;
import pl.coderslab.pages.ProductDetailsPage;
import java.time.Duration;

public class PurchaseSteps {

    // Driver przekazywany z klasy LoginSteps
    private WebDriver driver = LoginSteps.driver;

    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Sprawdzam, czy driver został zainicjalizowany.
        if (driver == null) {
            throw new IllegalStateException("Driver nie został zainicjalizowany. Uruchom login przed zakupem.");
        }

        // Inicjalizacja Page Objectów
        searchProductPage = new SearchProductPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);

        // Ustawienie WebDriverWait – można go wykorzystać do bardziej zaawansowanego oczekiwania na elementy.
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Punkt startowy
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @When("The user searches for {string} and selects the product")
    public void theUserSearchesForAndSelectsTheProduct(String productName) {
        // Uruchamiam metodę, która wpisuje nazwę i wybiera produkt z wyników.
        System.out.println("🔍 Searching for product: " + productName);
        searchProductPage.searchAndSelectProduct(productName);
    }

    @Then("The product discount should be {string}")
    public void theProductDiscountShouldBe(String expectedDiscount) {
        // Sprawdzam, czy produkt ma rabat
        String actualDiscount = productDetailsPage.getProductDiscount();
        System.out.println("💸 Checking product discount: " + actualDiscount);
        Assert.assertEquals("Discount is incorrect", expectedDiscount, actualDiscount);
    }

    @And("The user selects size {string} and quantity {int}")
    public void theUserSelectsSizeAndQuantity(String size, int quantity) {
        // Użytkownik wybiera rozmiar i ilość produktu – przekazuję te dane z poziomu scenariusza (parametryzacja).
        productDetailsPage.selectSize(size);
        productDetailsPage.setQuantity(quantity);
        System.out.println("📏 Size: " + size + " ✖️ Quantity: " + quantity);
    }

    @And("The user adds the product to the cart and proceeds to checkout")
    public void theUserAddsProductToCartAndProceedsToCheckout() {
        // Dodaję produkt do koszyka i przechodzę dalej
        productDetailsPage.addToCart();
        productDetailsPage.proceedToCheckoutFromModal();
        System.out.println("🛒 Product added to cart");
    }
}
