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

    // 🔁 Odbiór drivera z klasy LoginSteps
    private WebDriver driver = LoginSteps.driver;

    private SearchProductPage searchProductPage;
    private ProductDetailsPage productDetailsPage;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // ✅ Sprawdzenie, czy driver jest zainicjalizowany (czy uruchomiono logowanie)
        if (driver == null) {
            throw new IllegalStateException("Driver nie został zainicjalizowany. Uruchom login przed zakupem.");
        }

        // 🧱 Inicjalizacja obiektów Page Object
        searchProductPage = new SearchProductPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);

        // ⏱ Ustawienie waita
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // 🌐 Upewnienie się, że jesteśmy na stronie głównej
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @When("The user searches for {string} and selects the product")
    public void theUserSearchesForAndSelectsTheProduct(String productName) {
        // 🔍 Wyszukiwanie i wybór produktu
        System.out.println("🔍 Searching for product: " + productName);
        searchProductPage.searchAndSelectProduct(productName);
    }

    @Then("The product discount should be {string}")
    public void theProductDiscountShouldBe(String expectedDiscount) {
        // 💸 Weryfikacja rabatu produktu
        String actualDiscount = productDetailsPage.getProductDiscount();
        System.out.println("💸 Checking product discount: " + actualDiscount);
        Assert.assertEquals("Discount is incorrect", expectedDiscount, actualDiscount);
    }

    @And("The user selects size {string} and quantity {int}")
    public void theUserSelectsSizeAndQuantity(String size, int quantity) {
        // 📏 Wybór rozmiaru i ilości produktu
        productDetailsPage.selectSize(size);
        productDetailsPage.setQuantity(quantity);
        System.out.println("📏 Size: " + size + " ✖️ Quantity: " + quantity);
    }

    @And("The user adds the product to the cart and proceeds to checkout")
    public void theUserAddsProductToCartAndProceedsToCheckout() {
        // 🛒 Dodanie produktu do koszyka i przejście do koszyka (modal)
        productDetailsPage.addToCart();
        productDetailsPage.proceedToCheckoutFromModal();
        System.out.println("🛒 Product added to cart");
    }
}


