Feature: Purchase Hummingbird Printed Sweater

  Scenario Outline: User purchases Hummingbird Printed Sweater
    Given The user is on the login page
    When The user logs in with email "<email>" and password "<password>"
    When The user searches for "<product>" and selects the product
    Then The product discount should be "SAVE 20%"
    When The user selects size "<size>" and quantity <quantity>
    And The user adds the product to the cart and proceeds to checkout
    And The user proceeds from cart summary
    And The user confirms the address
    And The user chooses delivery method "PrestaShop pick up in store" and clicks continue
    And The user chooses payment method "Pay by Check"
    And The user agrees to the terms of service
    And The user confirms the order
    Then The order should be confirmed
    And The user sees the confirmation with amount and saves screenshot
    When The user goes to order history
    Then The user checks if order is on the list with status "Awaiting check payment" and the same amount as two steps before

    Examples:
      | email             | password    | size | quantity | product                     |
      | wojtek@wojtek.pl  | Masakra!23  | M    | 5        | Hummingbird Printed Sweater |
