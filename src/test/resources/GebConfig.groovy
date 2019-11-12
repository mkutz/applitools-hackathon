import org.openqa.selenium.chrome.ChromeOptions
import org.testcontainers.containers.BrowserWebDriverContainer

baseUrl = 'https://demo.applitools.com/hackathon.html'

driver = {
    BrowserWebDriverContainer container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
    container.start()
    return container.webDriver
}
