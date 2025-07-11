package pl.coderslab.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pl.coderslab.pages.CheckoutPage;

public class CheckoutSteps {

    private WebDriver driver = LoginSteps.driver;
    private CheckoutPage checkoutPage;

    public CheckoutSteps() {
        this.checkoutPage = new CheckoutPage(driver);
    }

    // Podsumowania koszyka
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

    // Krok potwierdzający adres dostawy na stronie checkoutu
    @And("The user confirms the address")
    public void theUserConfirmsTheDeliveryAddress() {
        checkoutPage.confirmAddress();
        System.out.println("🏠 User confirmed the delivery address");
    }

    // Krok wyboru metody dostawy i przejścia dalej
    @And("The user chooses delivery method {string} and clicks continue")
    public void theUserSelectsDeliveryMethod(String deliveryMethod) {
        checkoutPage.proceedFromDelivery();
        System.out.println("🚚 User chose delivery method: " + deliveryMethod);
    }

    // Krok wyboru metody płatności
    @And("The user chooses payment method {string}")
    public void theUserSelectsPaymentMethod(String paymentMethod) {
        checkoutPage.choosePaymentMethod(paymentMethod);
        System.out.println("💳 User chose payment method: " + paymentMethod);
    }

    // Krok akceptujący  (terms of service)
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

    // Weryfikacja, czy potwierdzenie zamówienia jest widoczne na stronie
    @Then("The order should be confirmed")
    public void theOrderShouldBeConfirmed() {
        boolean confirmed = checkoutPage.isOrderConfirmed();
        System.out.println("✅ Order confirmation visible? " + confirmed);
        Assert.assertTrue("❌ Order confirmation was not displayed.", confirmed);
    }
}
