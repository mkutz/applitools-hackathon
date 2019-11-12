import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testcontainers.containers.BrowserWebDriverContainer

baseUrl = 'https://demo.applitools.com/hackathon.html'

driver = {
    WebDriverManager.chromedriver().setup()
    return new ChromeDriver()
}

envrionments {
    chromeTestcontainers {
        BrowserWebDriverContainer container = new BrowserWebDriverContainer()
                .withCapabilities(new ChromeOptions())
        container.start()
        return container.webDriver
    }
}