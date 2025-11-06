import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.SeoTabsSettings;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import storefront.AssertsPage;
import storefront.ProductPage;
import utils.Utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

/*
Проверяем настройки из двух предыдущих тест-кейсов на теме Responsive.
*/

public class TestCase3_Responsive extends TestRunner {
    @Test
    public void checkProductTabsOnResponsive_TestCaseThree() {
        //Активируем тему "Responsive"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateTo_WebsiteThemes();
        csCartSettings.activateTheme();

        //Включаем настройку модуля - Перед вкладками товара
        SeoTabsSettings seoTabsSettings = csCartSettings.navigateTo_SeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("before_tabs");
        seoTabsSettings.button_SaveSettings.click();

        //Переходим на витрину
        ProductSettings productSettings = csCartSettings.navigateTo_ProductListPage();
        productSettings.goToEditingProductPage("X-Box");
        ProductPage productPage = productSettings.navigateTo_ProductPage(1);
        Utils.closeCookieNoticeIfExists();
        Utils.selectLanguage_RU();

        AssertsPage assertsPage = new AssertsPage();

        //Проверяем, что панель товарных вкладок от модуля присутствуют
        productPage.scrollToTab(productPage.tab_Panel);
        assertsPage.assertElementExists(assertsPage.productTabsPanel);

        //Проверяем, что панель товарных вкладок расположена перед вкладками товара
        assertsPage.assertElementExists(assertsPage.productTabsPosition_BeforeProductTabs);

        Selenide.screenshot("300 Product tabs panel - Panel before product tabs, Responsive");

        //Проверяем, что краткое название товара присутствует
        productPage.scrollToTab(productPage.tab_Tags);
        assertsPage.assertShortNameExists();
        Selenide.screenshot("310 Floating panel - Panel before product tabs, Responsive");

        //Включаем настройку модуля - После заголовка Н1
        csCartSettings.shiftBrowserTab(0);
        csCartSettings.navigateTo_SeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("after_h1");
        seoTabsSettings.button_SaveSettings.click();

        csCartSettings.shiftBrowserTab(1);
        Utils.refreshPage();
        executeJavaScript("window.scrollTo(0, 0);");

        //Проверяем, что панель товарных вкладок от модуля присутствуют
        assertsPage.assertElementExists(assertsPage.productTabsPanel);

        //Проверяем, что панель товарных вкладок расположена после заголовка Н1
        assertsPage.assertElementExists(assertsPage.productTabsPosition_AfterH1);

        sleep(2000);
        Selenide.screenshot("320 Product tabs panel - Panel after H1, Responsive");

        //Проверяем, что краткое название товара присутствует
        productPage.scrollToTab(productPage.tab_Tags);
        assertsPage.assertShortNameExists();

        Selenide.screenshot("330 Floating panel - Panel after H1, Responsive");
        System.out.println("TestCase3_Responsive has passed successfully!");
    }
}