import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.*;
import java.net.URL;

public class SimpleWebTest {
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("CI-Emulator");
        // 直接測試內建的 Chrome 瀏覽器
        options.setBrowserName("Chrome");

        System.out.println(" 正在連線到 Docker 內的 Appium 伺服器...");
        // 連線到 GitHub 伺服器本機的 4723 port
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
    }

    @Test
    public void testBilibiliNavigation() {
        System.out.println(" 成功連線！正在前往 Bilibili...");
        driver.get("https://www.bilibili.com");

        // 核心價值：讓程式自動檢查網址是否正確跳轉
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("bilibili"), " 網址錯誤：" + currentUrl);
        System.out.println(" 測試完美通過！目前網址：" + currentUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
