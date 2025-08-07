import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import storefront.CollectAssertMessages;

import static com.codeborne.selenide.Selenide.*;

/*
Модуль "SEO для товарных вкладок" + UniTheme2(UltRu) 4.16.2a.
Запускать через файл TestNG.xml или каждый тест-кейс по отдельности.
Скриншоты смотреть в папке: build -> reports -> tests
 */

public class TestRunner {
    public static final String BASIC_URL = "https://trs.test.abt.team/4184ultru/admin.php?dispatch=addons.manage";
    private SoftAssert softAssert;

    @BeforeClass
    public void openBrowser() {
        Configuration.browser = "chrome";
        Configuration.screenshots = true; //делаем скриншоты при падении
        Configuration.savePageSource = false; //не создавать html файлы при создании скриншотов
        open(BASIC_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран

        softAssert = new SoftAssert();
        CollectAssertMessages.setSoftAssertions(softAssert);

        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
        if ($(".cm-notification-close").exists())
            $(".cm-notification-close").click();
    }

    @AfterClass
    public void closeBrowser() {
        softAssert = CollectAssertMessages.getSoftAssertions();
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("\nОшибки в asserts:");
            System.out.println(e.getMessage());
        }

        sleep(2000);
        Selenide.closeWebDriver();
    }
}