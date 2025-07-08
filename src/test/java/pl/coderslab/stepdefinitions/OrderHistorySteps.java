package pl.coderslab.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pl.coderslab.pages.CheckoutPage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Klasa definiująca kroki związane z historią zamówień, w tym weryfikację potwierdzenia zamówienia i statusu w historii.
public class OrderHistorySteps {

    // Współdzielony driver z klasy LoginSteps
    private WebDriver driver = LoginSteps.driver;

    // Obiekt strony CheckoutPage, który posiada metody do interakcji z historią zamówień
    private CheckoutPage checkoutPage;

    // Statyczne pole przechowujące potwierdzoną kwotę zamówienia, aby była dostępna między różnymi krokami.
    private static String confirmationAmount;

    // Konstruktor inicjalizujący obiekt CheckoutPage
    public OrderHistorySteps() {
        this.checkoutPage = new CheckoutPage(driver);
    }

    @And("The user sees the confirmation with amount and saves screenshot")
    public void theUserSeesConfirmationAndSavesScreenshot() {
        Assert.assertTrue("Order confirmation was not displayed.", checkoutPage.isOrderConfirmed());

        confirmationAmount = checkoutPage.getTotalAmount();
        System.out.println("✅ Confirmed order amount: 💶 " + confirmationAmount);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filePath = "screenshots/order-confirmation-" + timestamp + ".png";
        checkoutPage.takeScreenshot(filePath);
    }

    @Then("The user goes to order history")
    public void theUserGoesToOrderHistory() {
        checkoutPage.goToOrderHistory();
        System.out.println("📜 Navigating to order history...");
    }

    // Krok: Weryfikacja, czy zamówienie o określonym statusie i kwocie jest na liście zamówień.
    @And("The user checks if order is on the list with status {string} and the same amount as two steps before")
    public void theUserChecksOrderStatusAndAmount(String expectedStatus) {
        Assert.assertNotNull("Confirmed order amount must be saved from two steps before", confirmationAmount);

        System.out.println("🔎 Checking if order with status '" + expectedStatus + "' and amount '" + confirmationAmount + "' is on the list...");

        boolean found = checkoutPage.isOrderWithStatusAndAmountOnList(expectedStatus, confirmationAmount);
        Assert.assertTrue("Order with status '" + expectedStatus + "' and amount '" + confirmationAmount + "' not found.", found);
    }
}
