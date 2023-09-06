import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/*
Модуль "SEO для товарных вкладок" + UniTheme2(UltRu) 4.16.2a.
Запускать через файл TestNG.xml или каждый тест-кейс по отдельности.
Скриншоты смотреть в папке: build -> reports -> tests
 */

public class TestRunner {
    public static final String BASIC_URL = "https://trs.test.abt.team/4171ultrubeta_unitheme2/admin.php?dispatch=addons.manage";

    @BeforeClass
    public void openBrowser() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false; //не закрываем браузер пока ведём разработку
        Configuration.screenshots = true; //делаем скриншоты при падении
        Configuration.browserSize = "1920x1050"; //Увеличиваем размер экрана
        open(BASIC_URL);
        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
        $(".cm-notification-close").click();
    }

    @AfterClass
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

    public void selectLanguage_RU() {
        $("a[id*='_wrap_language_']").hover().click();
        $(".ty-select-block__list-item a[data-ca-name='ru']").click();
    }
    public void closeCookieNotice(){
        $(".cookie-notice").shouldBe(Condition.interactable);
        $(".cm-btn-success").click();
    }
}