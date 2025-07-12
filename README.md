# Zadanie Zaliczeniowe 2 – Automatyzacja Testów

## Opis projektu

Projekt realizuje scenariusz automatyzacji testów z wykorzystaniem frameworka **Cucumber**, biblioteki **Selenium WebDriver** oraz wzorca **Page Object Model (POM)**.

Testowany scenariusz dotyczy sklepu internetowego [MyStore TestLab](https://mystore-testlab.coderslab.pl), a celem jest:

- zalogowanie się na istniejące konto użytkownika,
- wyszukanie i zakup produktu **"Hummingbird Printed Sweater"**,
- parametryzacja rozmiaru oraz ilości sztuk,
- weryfikacja zastosowanego rabatu,
- przejście przez proces zakupu (checkout),
- wybór metody dostawy i płatności,
- złożenie zamówienia,
- zapisanie zrzutu ekranu z potwierdzeniem zakupu i ceną.

## Technologie

- Java
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- Page Object Model
- JUnit
- WebDriverManager

## Struktura projektu

- `src/test/java/pl/coderslab/pages/` – klasy Page Object (np. `LoginPage`, `SearchProductPage`, `CheckoutPage`, `MyAccountPage`, `ProductDetailsPage`)
- `src/test/java/pl/coderslab/stepdefinitions/` – definicje kroków scenariuszy Cucumbera, m.in.:
- `LoginSteps.java` – logowanie do sklepu
- `PurchaseSteps.java` - 
- `CheckoutSteps.java` – finalizacja zamówienia i płatność
- `OrderHistorySteps.java` - weryfikacja historii zamówień
- `src/Cucumber/features` – pliki `.feature` z opisem scenariuszy testowych
- `screenshots/` – katalog z zapisanymi zrzutami ekranu z potwierdzeniem zamówienia


## Dane logowania testowego


- Email: `wojtek@wojtek.pl`
- Hasło: `Masakra!23`

## Autor

Projekt wykonany w ramach kursu Automatyzacji Testów – CodersLab  
Autor: [Wojciech Basista](https://github.com/Wojciech1337/ZadaniaZaliczeniowe01.git)

