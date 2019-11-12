import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver

baseUrl = 'https://demo.applitools.com/hackathon.html'

autoClearWebStorage = true

driver = {
    WebDriverManager.chromedriver().setup()
    return new ChromeDriver()
}
