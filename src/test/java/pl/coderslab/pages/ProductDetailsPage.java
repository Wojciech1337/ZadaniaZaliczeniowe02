package pl.coderslab.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ProductDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "span.discount.discount-percentage")
    private WebElement discountLabel;

    @FindBy(id = "group_1")
    private WebElement sizeDropdown;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = "button.add-to-cart")
    private WebElement addToCartButton;

    // 🔹 Przycisk "Proceed to checkout" w modalu po dodaniu do koszyka
    @FindBy(css = "div#blockcart-modal a.btn.btn-primary")
    private WebElement proceedToCheckoutFromModalButton;



    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectSize(String size) {
        wait.until(ExpectedConditions.visibilityOf(sizeDropdown));
        new Select(sizeDropdown).selectByVisibleText(size);
    }

    public void setQuantity(int quantity) {
        wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
        quantityInput.click();
        quantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        quantityInput.sendKeys(Keys.BACK_SPACE);
        quantityInput.sendKeys(String.valueOf(quantity));

        try {
            wait.until(ExpectedConditions.attributeToBe(quantityInput, "value", String.valueOf(quantity)));
        } catch (TimeoutException e) {
            System.out.println("⚠️ Nie udało się ustawić ilości. Próbuję ponownie.");
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(quantity));
        }
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void proceedToCheckoutFromModal() {
        // 🟡 Upewnij się, że modal został otwarty
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockcart-modal")));
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutFromModalButton)).click();
    }
    public String getProductDiscount() {
        wait.until(ExpectedConditions.visibilityOf(discountLabel));
        return discountLabel.getText().trim();
    }
}
