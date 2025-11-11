package browser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {
    private WebDriver driver;
    private final String TEST_URL = "https://www.bing.com";

    // Metoda uruchamiana PRZED KAŻDYM testem (inicjalizacja przeglądarki)
    @BeforeEach
    public void setUp() {
        System.out.println("-> Inicjalizacja przeglądarki przed testem.");
        driver = new ChromeDriver();
    }

    // Metoda oznaczona jako test
    @Test
    public void checkBingTitleTest() {
        System.out.println("-> Uruchamianie testu: Sprawdzenie tytułu Bing.");

        // AKCJA
        driver.get(TEST_URL);

        // ASERCJA (Sprawdzenie, czy tytuł zawiera słowo "Bing")
        String title = driver.getTitle();
        System.out.println("Odebrany tytuł: " + title);

        // Jeśli ten warunek nie zostanie spełniony, test ZAWIEŚĆ
        assertTrue(title.contains("Bing"), "Błąd: Tytuł strony nie zawiera oczekiwanego słowa 'Bing'.");

        System.out.println("✅ Test zaliczony!");
    }

    // Metoda uruchamiana PO KAŻDYM teście (zamykanie przeglądarki)
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("-> Przeglądarka została zamknięta po teście.");
        }
    }
}
