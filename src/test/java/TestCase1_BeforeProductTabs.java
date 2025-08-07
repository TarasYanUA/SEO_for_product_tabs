import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.UniThemeSettings;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import storefront.AssertsPage;
import storefront.ProductPage;
import utils.Utils;

/*
- Настройки темы Юни2:
    * Верхняя липкая панель -- Включаем и Отключаем
- Позиция навигационной панели вкладок -- Перед вкладками товара
*/

public class TestCase1_BeforeProductTabs extends TestRunner {
    @Test
    public void checkProductTabs_TestCaseOne() {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Включаем верхнюю липкую панель темы
        UniThemeSettings uniThemeSettings = csCartSettings.navigateTo_ThemeSettings();
        Utils.setCheckboxAndSave(uniThemeSettings.setting_TopStickyPanel, true);

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

        Selenide.screenshot("100 Product tabs panel - Panel before product tabs, UniTheme2");

        //Проверяем, что краткое название товара присутствует
        productPage.scrollToTab(productPage.tab_Tags);
        assertsPage.assertShortNameExists();
        Selenide.screenshot("110 Floating panel - Panel before product tabs, Top sticky panel-On");

        //Отключаем верхнюю липкую панель темы
        csCartSettings.shiftBrowserTab(0);
        csCartSettings.navigateTo_ThemeSettings();
        Utils.setCheckboxAndSave(uniThemeSettings.setting_TopStickyPanel, false);
        csCartSettings.shiftBrowserTab(1);
        Utils.refreshPage();

        Selenide.screenshot("120 Floating panel - Panel before product tabs, Top sticky panel-Off");
        System.out.println("TestCase1_BeforeProductTabs has passed successfully!");
    }
}