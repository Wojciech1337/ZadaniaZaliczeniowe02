# Zadanie Zaliczeniowe 2 – Automatyzacja procesu zakupowego

## Opis projektu

Projekt przedstawia automatyzację pełnego procesu zakupowego w sklepie internetowym z wykorzystaniem **Selenium WebDriver**, **Cucumber (BDD)** oraz wzorca **Page Object Model**.

Automatyczny test odwzorowuje rzeczywiste zachowanie użytkownika i obejmuje:

- logowanie do istniejącego konta,
- wyszukanie produktu **Hummingbird Printed Sweater**,
- parametryzację rozmiaru i ilości,
- weryfikację rabatu,
- przejście przez proces checkout,
- wybór metody dostawy i płatności,
- złożenie zamówienia,
- zapis zrzutu ekranu z potwierdzeniem zakupu i ceną.

Testowany system:  
👉 https://mystore-testlab.coderslab.pl

Projekt został wykonany w ramach kursu Automatyzacji Testów.

---

## Technologie

- Java
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- JUnit
- Page Object Model
- WebDriverManager

---

##  Wymagania

Przed uruchomieniem testów automatycznych należy mieć zainstalowane:

- **Java 21**
- **Maven**
- **Google Chrome**
- **Git**
- IDE (np. IntelliJ IDEA lub Eclipse)

## Struktura projektu

Projekt jest zorganizowany zgodnie z dobrymi praktykami automatyzacji testów i wykorzystuje architekturę Page Object Model.

```
ZadaniaZaliczeniowe02
pages/
 ├── CheckoutPage.java
 ├── LoginPage.java
 ├── MyAccountPage.java
 ├── ProductDetailsPage.java
 └── SearchProductPage.java

stepdefinitions/
 ├── CheckoutSteps.java
 ├── LoginSteps.java
 ├── OrderHistorySteps.java
 └── PurchaseSteps.java

features/
 └── purchasing-product.feature

screenshots/
```

- `pages/` – klasy Page Object odpowiedzialne za interakcję z UI
- `stepdefinitions/` – implementacja kroków Cucumber
- `features/` – scenariusze testowe zapisane w Gherkin
- `screenshots/` – zapisane zrzuty ekranu z wykonania testów

---

## Uruchomienie testów

1. Sklonuj repozytorium:

```
git clone https://github.com/Wojciech1337/ZadaniaZaliczeniowe02.git
cd ZadaniaZaliczeniowe01
```

2. Otwórz projekt w IntelliJ IDEA / Eclipse

3. Uruchom testy:

```
mvn test
```

lub bezpośrednio z pliku runnera Cucumber.

---

## Dane testowe

Konto używane wyłącznie do celów testowych:

- Email: `wojtek@wojtek.pl`
- Hasło: `Masakra!23`

---

## Autor

Projekt wykonany w ramach kursu Automatyzacji Testów – CodersLab

**Wojciech Basista**  
https://github.com/Wojciech1337
