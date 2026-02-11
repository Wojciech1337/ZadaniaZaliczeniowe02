package pl.coderslab.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;


// Page Object dla strony Checkout. Obejmuje wszystkie kroki: od koszyka po historię zamówień.


public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Podsumowanie zamówienia

    @FindBy(css = "a.btn.btn-primary[href*='order']")
    private WebElement proceedFromCartSummaryButton;

    // Adres

    @FindBy(css = "button[name='confirm-addresses']")
    private WebElement confirmAddressButton;


    // Dostawa

    @FindBy(name = "confirmDeliveryOption")
    private WebElement continueFromDeliveryButton;


    // Płatność

    @FindBy(id = "payment-option-1")
    private WebElement paymentOptionCheckInput;

    @FindBy(css = "label[for='payment-option-1']")
    private WebElement paymentOptionCheckLabel;

    @FindBy(css = "label.js-terms[for='conditions_to_approve[terms-and-conditions]']")
    private WebElement agreeTermsLabel;

    @FindBy(css = "button.btn.btn-primary.center-block[type='submit']")
    private WebElement confirmOrderButton;

    // Potwierdzenie zamówienia

    @FindBy(css = "h3.card-title")
    private WebElement orderConfirmationHeader;

    @FindBy(xpath = "//span[text()='Total (tax incl.)']/ancestor::tr/td[2]")
    private WebElement totalAmount;

    // Historia zamówień

    @FindBy(css = "a.account")
    private WebElement accountLink;

    @FindBy(css = "a[href*='history']")
    private WebElement orderHistoryLink;

    // Konstruktor

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Metody akcji

    public void proceedFromCartSummary() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedFromCartSummaryButton)).click();
    }

    public void confirmAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmAddressButton)).click();
    }


    public void proceedFromDelivery() {
        wait.until(ExpectedConditions.elementToBeClickable(continueFromDeliveryButton)).click();
    }


    public void choosePaymentMethod(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("Pay by Check")) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(paymentOptionCheckLabel)).click();
            } catch (ElementClickInterceptedException | TimeoutException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentOptionCheckLabel);
            }
        }

    }

    public void agreeToTerms() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(agreeTermsLabel)).click();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", agreeTermsLabel);
        }
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }


    public boolean isOrderConfirmed() {
        try {
            WebElement confirmationHeader = wait.until(ExpectedConditions.visibilityOf(orderConfirmationHeader));
            return confirmationHeader.getText().contains("YOUR ORDER IS CONFIRMED");
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getTotalAmount() {
        return totalAmount.getText().trim();
    }

    public void takeScreenshot(String filePath) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            Files.createDirectories(destination.getParentFile().toPath());
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ Screenshot zapisany do: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Nie udało się zapisać screenshota: " + e.getMessage());
        }
    }


    public void goToOrderHistory() {
        wait.until(ExpectedConditions.elementToBeClickable(accountLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(orderHistoryLink)).click();
    }

    public boolean isOrderWithStatusAndAmountOnList(String expectedStatus, String expectedAmount) {
        WebElement firstRow = driver.findElement(By.cssSelector("table tbody tr"));
        String status = firstRow.findElement(By.cssSelector("td span.label")).getText().trim();
        String price = firstRow.findElement(By.cssSelector("td.text-xs-right")).getText().trim();

        return status.equals(expectedStatus) && price.equals(expectedAmount);
    }
}