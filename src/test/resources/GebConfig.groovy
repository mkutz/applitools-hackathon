import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testcontainers.containers.BrowserWebDriverContainer

driver = {
    WebDriverManager.chromedriver().setup()
    return new ChromeDriver()
}

environments {
    chromeTestcontainers {
        driver = {
            BrowserWebDriverContainer container = new BrowserWebDriverContainer()
                    .withCapabilities(new ChromeOptions())
            container.start()
            return container.webDriver
        }
    }
}