package pl.coderslab.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pl.coderslab.pages.CheckoutPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckoutSteps {

    private WebDriver driver = LoginSteps.driver;
    private CheckoutPage checkoutPage;
    private String confirmationAmount;

    public CheckoutSteps() {
        this.checkoutPage = new CheckoutPage(driver);
    }

    @And("The user proceeds from cart summary")
    public void theUserProceedsFromCartSummary() {
        try {
            checkoutPage.proceedFromCartSummary();
            System.out.println("🛒 User proceeded from cart summary");
        } catch (Exception e) {
            System.err.println("⚠️ Failed to click 'Proceed to checkout' button in modal: " + e.getMessage());
            throw e;
        }
    }

    @And("The user confirms the address")
    public void theUserConfirmsTheDeliveryAddress() {
        checkoutPage.confirmAddress();
        System.out.println("🏠 User confirmed the delivery address");
    }

    @And("The user chooses {string} delivery method and continues")
    public void theUserSelectsDeliveryMethod(String deliveryMethod) {
        checkoutPage.proceedFromDelivery();
        System.out.println("🚚 User chose delivery method: " + deliveryMethod);

    }

    @And("The user chooses payment method {string}")
    public void theUserSelectsPaymentMethod(String paymentMethod) {
        checkoutPage.choosePaymentMethod(paymentMethod);
        System.out.println("💳 User chose payment method: " + paymentMethod);
    }

    @And("The user agrees to the terms of service")
    public void theUserAgreesToTheTermsOfService() {
        checkoutPage.agreeToTerms();
        System.out.println("✔️📄 User agreed to the terms of service");
    }

    @And("The user confirms the order")
    public void theUserConfirmsTheOrder() {
        checkoutPage.confirmOrder();
        System.out.println("✅ Order has been confirmed!");
    }

    @Then("The order should be confirmed")
    public void theOrderShouldBeConfirmed() {
        boolean confirmed = checkoutPage.isOrderConfirmed();
        System.out.println("✅ Order confirmation visible? " + confirmed);
        Assert.assertTrue("❌ Order confirmation was not displayed.", confirmed);

    }

    @And("The user sees the confirmation with amount and saves screenshot")
    public void theUserSeesConfirmationAndSavesScreenshot() {
        Assert.assertTrue("Order confirmation was not displayed.", checkoutPage.isOrderConfirmed());

        confirmationAmount = checkoutPage.getTotalAmount();
        System.out.println("✅ Confirmed order amount: 💶 " + confirmationAmount);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filePath = "📸 screenshots/order-confirmation-" + timestamp + ".png";
        checkoutPage.takeScreenshot(filePath);
    }

    @Then("The user goes to order history")
    public void theUserGoesToOrderHistory() {
        checkoutPage.goToOrderHistory();
        System.out.println("📜 Navigating to order history...");
    }

    @And("The user checks if order is on the list with status {string} and the same amount as two steps before")
    public void theUserChecksOrderStatusAndAmount(String expectedStatus) {
        Assert.assertNotNull("Confirmed order amount must be saved from two steps before", confirmationAmount);
        System.out.println("🔎 Checking if order with status '" + expectedStatus + "' and amount '" + confirmationAmount + "' is on the list...");

        boolean found = checkoutPage.isOrderWithStatusAndAmountOnList(expectedStatus, confirmationAmount);
        Assert.assertTrue("Order with status '" + expectedStatus + "' and amount '" + confirmationAmount + "' not found.", found);
    }
}