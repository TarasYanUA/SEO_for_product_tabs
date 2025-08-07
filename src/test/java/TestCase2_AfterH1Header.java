import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.SeoTabsSettings;
import adminPanel.UniThemeSettings;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import storefront.AssertsPage;
import storefront.ProductPage;
import utils.Utils;

/*
- Настройки темы Юни2:
    * Верхняя липкая панель -- Включаем и Отключаем
- Позиция навигационной панели вкладок -- После заголовка Н1
*/

public class TestCase2_AfterH1Header extends TestRunner{
    @Test
    public void checkProductTabs_TestCaseTwo(){
        //Включаем верхнюю липкую панель темы
        CsCartSettings csCartSettings = new CsCartSettings();
        UniThemeSettings uniThemeSettings = csCartSettings.navigateTo_ThemeSettings();
        Utils.setCheckboxAndSave(uniThemeSettings.setting_TopStickyPanel, true);

        //Настраиваем настройки модуля
        SeoTabsSettings seoTabsSettings = csCartSettings.navigateTo_SeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("after_h1");
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

        //Проверяем, что панель товарных вкладок расположена после заголовка Н1
        assertsPage.assertElementExists(assertsPage.productTabsPosition_AfterH1);

        Selenide.screenshot("200 Product tabs panel - Panel after H1, UniTheme2");

        //Проверяем, что краткое название товара присутствует
        productPage.scrollToTab(productPage.tab_Tags);
        assertsPage.assertShortNameExists();
        Selenide.screenshot("210 Floating panel - Panel after H1, Top sticky panel-On");

        //Отключаем верхнюю липкую панель темы
        csCartSettings.shiftBrowserTab(0);
        csCartSettings.navigateTo_ThemeSettings();
        Utils.setCheckboxAndSave(uniThemeSettings.setting_TopStickyPanel, false);
        csCartSettings.shiftBrowserTab(1);
        Utils.refreshPage();

        Selenide.screenshot("230 Floating panel - Panel after H1, Top sticky panel-Off");
        System.out.println("TestCase2_AfterH1Header has passed successfully!");
    }
}