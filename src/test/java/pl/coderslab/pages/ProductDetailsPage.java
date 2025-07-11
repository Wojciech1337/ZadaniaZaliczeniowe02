package pl.coderslab.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

//Page Object reprezentujący stronę szczegółów produktu.
//Zawiera metody do wyboru rozmiaru, ustawiania ilości, dodania do koszyka oraz przejścia do checkoutu.

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

    // Przycisk "Proceed to checkout" w modalu po dodaniu do koszyka
    @FindBy(css = "div#blockcart-modal a.btn.btn-primary")
    private WebElement proceedToCheckoutFromModalButton;


    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // Wybiera rozmiar z dropdowna po nazwie widocznej na stronie (np. "M").

    public void selectSize(String size) {
        wait.until(ExpectedConditions.visibilityOf(sizeDropdown));
        new Select(sizeDropdown).selectByVisibleText(size);
    }

    // Ustawia żądaną ilość produktu.

    public void setQuantity(int quantity) {
        wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
        quantityInput.click();
        quantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // zaznacz wszystko
        quantityInput.sendKeys(Keys.BACK_SPACE);                // usuń
        quantityInput.sendKeys(String.valueOf(quantity));

        try {
            wait.until(ExpectedConditions.attributeToBe(quantityInput, "value", String.valueOf(quantity)));
        } catch (TimeoutException e) {
            System.out.println("⚠️ Nie udało się ustawić ilości. Próbuję ponownie.");
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(quantity));
        }
    }

    // Kliknięcie w przycisk dodania produktu do koszyka.

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    //Kliknięcie "Proceed to checkout" z modalu po dodaniu do koszyka.

    public void proceedToCheckoutFromModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockcart-modal")));
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutFromModalButton)).click();
    }


      //Pobiera wartość procentową rabatu produktu (np. "20%"). Return tekst etykiety rabatu


    public String getProductDiscount() {
        wait.until(ExpectedConditions.visibilityOf(discountLabel));
        return discountLabel.getText().trim();
    }
}
