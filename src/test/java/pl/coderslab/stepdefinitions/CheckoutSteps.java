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

    public CheckoutSteps() {
        this.checkoutPage = new CheckoutPage(driver);
    }

    @And("The user proceeds from cart summary")
    public void theUserProceedsFromCartSummary() {
        try {
            checkoutPage.proceedFromCartSummary();
        } catch (Exception e) {
            System.err.println("Nie udało się kliknąć przycisku 'Proceed to checkout' w modalu: " + e.getMessage());
            throw e;
        }
    }

    @And("The user confirms the address")
    public void theUserConfirmsTheDeliveryAddress() {
        checkoutPage.confirmAddress();
    }

    @And("The user chooses {string} delivery method and continues")
    public void theUserSelectsDeliveryMethod(String deliveryMethod) {
        checkoutPage.proceedFromDelivery();
    }

    @And("The user chooses payment method {string}")
    public void theUserSelectsPaymentMethod(String paymentMethod) {
        checkoutPage.choosePaymentMethod(paymentMethod);
    }

    @And("The user agrees to the terms of service")
    public void theUserAgreesToTheTermsOfService() {
        checkoutPage.agreeToTerms();
    }

    @And("The user confirms the order")
    public void theUserConfirmsTheOrder() {
        checkoutPage.confirmOrder();
    }

    @Then("The order should be confirmed")
    public void theOrderShouldBeConfirmed() {
        boolean confirmed = checkoutPage.isOrderConfirmed();
        System.out.println("Order confirmation visible? " + confirmed);
        Assert.assertTrue("Order confirmation was not displayed.", confirmed);

    }

    @And("The user sees the confirmation with amount and saves screenshot")
    public void theUserSeesConfirmationAndSavesScreenshot() {
        Assert.assertTrue("Order confirmation was not displayed.", checkoutPage.isOrderConfirmed());

        System.out.println("Confirmed amount: " + checkoutPage.getTotalAmount());

        // Dodajemy timestamp do nazwy pliku, żeby nie nadpisywać pliku przy kolejnych testach
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filePath = "screenshots/order-confirmation-" + timestamp + ".png";

        checkoutPage.takeScreenshot(filePath);
    }
}
